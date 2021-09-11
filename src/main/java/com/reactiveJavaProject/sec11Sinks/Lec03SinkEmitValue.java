package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec03SinkEmitValue {

    public static void main(String[] args) {

        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber("BOB"));

        sink.emitValue("hi", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        }); // it will emit 'hi' and it will not check the EmitFailureHandler

        sink.emitValue("hello", (signalType, emitResult) -> {
            System.out.println(signalType.name());
            System.out.println(emitResult.name());
            return false;
        }); //as it is a Mono/Sink.one we cannot emit more than 1 value, then this try will fail and go to the EmitFailureHandler

        /*
        Console results:
        BOB - Received : hi
        Completed
        ON_NEXT
        FAIL_TERMINATED
        */

    }
}
