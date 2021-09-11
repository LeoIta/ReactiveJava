package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec09SinkReplay {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().replay().all();
//        Sinks.Many<Object> sink = Sinks.many().replay().all(2);
        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");
        sink.tryEmitNext("new msg");

        flux.subscribe(Util.subscriber("mike"));
        flux.subscribe(Util.subscriber("jake"));

        /* using .replay().all() every subscriber will get all the items of the 1st subscriber because the history is replayed
         * using .replay().all(2) every subscriber will get 2 items of the 1st subscriber*/
    }

}