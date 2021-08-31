package com.reactiveJavaProject.sec09Batches;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec01Buffer {

    public static void main(String[] args) {

        //Batching With Buffer
        eventStream()
                .buffer(10) //it groups 10 values in a list and consume them together
                .subscribe(Util.subscriber("eventStream"));


        //Batching With Buffer - When Complete Signal Is Emitted
        eventStreamEmit3()
                .buffer(10)
    /*it groups 10 values in a list and consume them together, if the producer emits less than 10,
                it will take the emitted ones, in this case just 3*/
                .subscribe(Util.subscriber("eventStreamEmit3"));

        Util.sleepSeconds(10);

    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(100))
                .map(i -> "event" + i);
    }

    private static Flux<String> eventStreamEmit3() {
        return Flux.interval(Duration.ofMillis(100))
                .take(3)
                .map(i -> "event" + i);
    }

}
