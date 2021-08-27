package com.reactiveJavaProject.sec07BackpressureOverflowStrategy;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec04Latest {

    public static void main(String[] args) {

 /* instead of dropping the values before the 75% of the buffer, we keep the last one
Pushed : 68
Received : 12
Pushed : 69
with latest() after 16 we'll have 68+1 and not 69+1 as we had with drop
 */

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
            for (int i = 1; i < 201; i++) {
                fluxSink.next(i);
                Util.sleepMillis(1);
                System.out.println("Pushed : " + i);
            }
            fluxSink.complete();
        })
                .onBackpressureLatest()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);

    }


}
