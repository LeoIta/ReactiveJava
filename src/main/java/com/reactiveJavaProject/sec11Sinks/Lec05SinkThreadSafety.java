package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lec05SinkThreadSafety {

    public static void main(String[] args) {

        /*sink in general are threads safe*/

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        List<Object> list = new ArrayList<>();

        flux.subscribe(list::add);

        /*
        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(j);
            });
        }

        this loop is not threads safe as we have different results every run. we should use the sink.emitNext with EmitFailureHandler
        */

        for (int i = 0; i < 1000; i++) {
            final int j = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(j, (signalType, emitResult) -> true); //it will retry every time there is an error and result will be always the same 1000
            });
        }

        Util.sleepSeconds(3);

        System.out.println(list.size());

    }

}
