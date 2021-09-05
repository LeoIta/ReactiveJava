package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec07SinkMulticastDirectAllOrNothing {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("I am");
        sink.tryEmitNext("the first");
        sink.tryEmitNext("submitter");
        flux.subscribe(Util.subscriber("sam"));
        /* multicast().directAllOrNothing() is not caching any item emitted before the first subscriber, so here even sam as first subscriber
         * if he subscribe after 3 items , he will not get the first 3 items*/
        flux.subscribe(Util.subscriber("bob"));
        sink.tryEmitNext("hi");
        flux.subscribe(Util.subscriber("john"));
        sink.tryEmitNext("how are you");
        flux.subscribe(Util.subscriber("jerry"));
        sink.tryEmitNext("?");
        flux.subscribe(Util.subscriber("tom"));


        /*with .directAllOrNothing all submitters will emit the same items based on when the 1st submission happened.
         they will emit:
         "Hi how are you?"
         */

    }
}
