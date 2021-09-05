package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec06SinkMulticastBuffer {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("I am");
        sink.tryEmitNext("the first");
        sink.tryEmitNext("submitter.");
        flux.subscribe(Util.subscriber("sam"));
        /* multicast().onBackpressureBuffer() is caching all the items until the first subscriber, so here sam as first subscriber even
         * if he subscribe after 3 items , he will get also the first 3 items*/
        flux.subscribe(Util.subscriber("bob"));
        sink.tryEmitNext("hi");
        flux.subscribe(Util.subscriber("john"));
        sink.tryEmitNext("how are you");
        flux.subscribe(Util.subscriber("jerry"));
        sink.tryEmitNext("?");
        flux.subscribe(Util.subscriber("tom"));


        /*other submitters will emit items based on when the submission happened as sam.
         MulticastProcessor allows multiple Subscribers
         they will emit all the items produced after their subscription
         sam: "I am the first submitter. Hi how are you?"
         bob: "Hi how are you?"
         john: "how are you?"
         jerry: "?"
         tom: ""
         */

    }
}
