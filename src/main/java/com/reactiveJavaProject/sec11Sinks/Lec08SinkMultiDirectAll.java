package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

public class Lec08SinkMultiDirectAll {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();
        // with .directBestEffort() sam performance are not affected by mike performance. Sam will emit from 0 to 99, Mike from 0 to 31

//        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();
        // with .directAllOrNothing() sam performance are affected by mike performance and will emit only from 0 to 31

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(200)).subscribe(Util.subscriber("mike"));

        for (int i = 0; i < 100; i++) {
            sink.tryEmitNext(i);
        }

        Util.sleepSeconds(10);
    }
}