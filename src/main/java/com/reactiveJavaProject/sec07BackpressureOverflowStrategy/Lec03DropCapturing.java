package com.reactiveJavaProject.sec07BackpressureOverflowStrategy;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec03DropCapturing {

    public static void main(String[] args) {

        /* instead of dropping the value, we can store the "dropped" ones in a database, for simplicity we'll use an ArrayList*/

        List<Object> droppedList = new ArrayList<>();
        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
            for (int i = 1; i < 501; i++) {
                fluxSink.next(i);
                Util.sleepMillis(1);
                System.out.println("Pushed : " + i);
            }
            fluxSink.complete();
        })
                // I add the dropped value to the list
                .onBackpressureDrop(droppedList::add)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(4);

        System.out.println(droppedList);
        System.out.println(droppedList.size() + " dropped values have been sent to the database");

    }

}