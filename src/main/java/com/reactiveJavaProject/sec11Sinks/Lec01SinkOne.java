package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec01SinkOne {

    public static void main(String[] args) {

        /*
        We have 6 possible methods:
        1. emitEmpty(EmitFailureHandler emitFailureHandler)                         void
        2. emitError (Throwable throwable, EmitFailureHandler emitFailureHandler)   void
        3. emitValue (Object t, EmitFailureHandler emitFailureHandler)              void
        4. tryEmitEmpty()                                                     EmitResult
        5. tryEmitError(Throwable throwable)                                  EmitResult
        6. tryEmitValue(Object t)                                             EmitResult
        the first three act like the last ones but they also handles the errors
        */

        // mono1 value / empty / error
        Sinks.One<Object> sinkOne = Sinks.one(); // it is like Mono, it return only a value

        Mono<Object> mono = sinkOne.asMono();
        // with sinkOne we drop the value, with mono we'll subscribe

        mono.subscribe(Util.subscriber("bob"));
        //until now no value has been dropped then emitted

        sinkOne.tryEmitValue("hi");

        //we can have also multiple subscriber that will emit the same values
        mono.subscribe(Util.subscriber("john"));

    }
}
