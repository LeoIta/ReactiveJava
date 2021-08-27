package com.reactiveJavaProject.sec07BackpressureOverflowStrategy;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec06BufferWithSize {

    public static void main(String[] args) {

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
            for (int i = 1; i < 201 && !fluxSink.isCancelled(); i++) {
                fluxSink.next(i);
                Util.sleepMillis(1);
                System.out.println("Pushed : " + i);
            }
            fluxSink.complete();
        })
                /* adding a value in  .onBackpressureBuffer(70) will increase the number of kept values*/
//                .onBackpressureBuffer(70)

                /* using consumer argument we can print with .onBackpressureBuffer(maxSize, Consumer)
                 the dropped value that is generate even if the !fluxSink.isCancelled() should not allow
                 it will throw an error eventually*/

                .onBackpressureBuffer(70, o -> System.out.println("Dropped : " + o))
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);

    }

}
