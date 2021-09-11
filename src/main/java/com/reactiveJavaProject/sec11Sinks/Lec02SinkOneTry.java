package com.reactiveJavaProject.sec11Sinks;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

public class Lec02SinkOneTry {

    public static void main(String[] args) {

        Sinks.One<Object> sink1 = Sinks.one();
        Mono<Object> mono1 = sink1.asMono();
        mono1.subscribe(Util.subscriber("SinkValue"));
        sink1.tryEmitValue("any value");

        Sinks.One<Object> sink2 = Sinks.one();
        Mono<Object> mono2 = sink2.asMono();
        mono2.subscribe(Util.subscriber("SinkEmpty"));
        sink2.tryEmitEmpty();

        Sinks.One<Object> sink3 = Sinks.one();
        Mono<Object> mono3 = sink3.asMono();
        mono3.subscribe(Util.subscriber("SinkError"));
        sink3.tryEmitError(new RuntimeException("new error"));

        /* results are:
         SinkValue - Received : any value
         Completed                               we return a value and then mono.completed

         Completed                               no value, only mono.completed

         SinkError - ERROR : new error           we throw an error and then we have not mono.completed
        */
    }
}