package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

public class Lec04SinkUnicast {

    public static void main(String[] args) {

        // handle through which we would push items
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();


        /*
        We have 6 possible methods:
        1. emitComplete(EmitFailureHandler emitFailureHandler)                      void
        2. emitError(Throwable throwable, EmitFailureHandler emitFailureHandler)    void
        3. emitNext(Object t, EmitFailureHandler emitFailureHandler)                void
        4. tryEmitComplete()                                                  EmitResult
        5. tryEmitError(Throwable throwable)                                  EmitResult
        6. tryEmitValue(Object t)                                             EmitResult
        the first three act like the last ones but they also handles the errors
        */

        // handle through which subscribers will receive items
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        sink.tryEmitNext("hi");
        sink.tryEmitNext("how are you");
        sink.tryEmitNext("?");

        flux.subscribe(Util.subscriber("bob"));
        //bob - ERROR : UnicastProcessor allows only a single Subscriber

    }
}
