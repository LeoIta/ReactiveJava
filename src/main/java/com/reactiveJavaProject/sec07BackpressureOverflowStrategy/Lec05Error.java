package com.reactiveJavaProject.sec07BackpressureOverflowStrategy;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec05Error {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        /*
        with .onBackpressureError() if we receive a value after all the items are already emitted, we'll get error:
        ERROR : The receiver is overrun by more signals than expected (bounded queue...)
        */

        Flux.create(fluxSink -> {
            /*
            I can add a condition that check if the subscription has been already cancelled
              !fluxSink.isCancelled()
             in this way only 1 value is publish more than the consumed ones.
            */
            for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
                fluxSink.next(i);
                Util.sleepMillis(1);
                System.out.println("Pushed : " + i);
            }
            fluxSink.complete();
        })
                .onBackpressureError()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);

    }

}