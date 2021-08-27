package com.reactiveJavaProject.sec07BackpressureOverflowStrategy;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec01DefaultBuffer {

    public static void main(String[] args) {

        /* if the publisher is faster than the consumer and it produces a huge amount of info,
         the system for default (BUFFER strategy) has to keep in the memory what was produced
        until the subscriber will consume it. It is not good to keep too much data in the memory */

        Flux.create(fluxSink -> {
            for (int i = 1; i < 501; i++) {
                fluxSink.next(i);
                System.out.println("Pushed : " + i);
            }
            fluxSink.complete();
        })
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }
}
