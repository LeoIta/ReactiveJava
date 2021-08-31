package com.reactiveJavaProject.sec09Batches;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec03OverlapAndDropBuffer {

    public static void main(String[] args) {

        /* maxSize tells how many item to collect together, skip is telling how many element will be skipped in the next list
         if skip<maxSize then the next list will contain the last (maxSize-skip) items from the previous one
         if skip>maxSize then the next list will drop (skip-maxSize) items before add items to the next list
         default value of skip is te same of the maxSize, and must be skip>0*/
        eventStream()
                .buffer(3, 1)
                .subscribe(Util.subscriber("maxSize>skip"));

        eventStream()
                .buffer(3, 5)
                .subscribe(Util.subscriber("skip>maxSize"));

        Util.sleepSeconds(4);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(300))
                .map(i -> "event" + i);
    }

}
