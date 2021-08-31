package com.reactiveJavaProject.sec09Batches;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec02BufferWithTimeout {

    public static void main(String[] args) {

        /*PROBLEM 1:
         Batching With Buffer value - When Publisher is too slow
         to get the first 10 events and 1st result we need to wait 8 sec but should be better to have faster result */

//        eventStreamSlow()
//                .buffer(10)
//                .subscribe(Util.subscriber("eventStreamSlow"));

        /*PROBLEM 2:
         Batching With Buffer Duration - When Publisher is too fast
         the first result will be visible in short time but will contain to many events (100) */

//        eventStreamFast()
//                .buffer(Duration.ofSeconds(1))
//                .subscribe(Util.subscriber("eventStreamFast"));

        /*SOLUTION to be sure to not have any of the previous problems, we can use the hybrid solution .bufferTimeout() */
        eventStreamSlow()
                .bufferTimeout(10,Duration.ofSeconds(1))
                .subscribe(Util.subscriber("bufferTimeoutSlow"));

        eventStreamFast()
                .bufferTimeout(10,Duration.ofSeconds(1))
                .subscribe(Util.subscriber("bufferTimeoutFast"));


        Util.sleepSeconds(10);

    }

    private static Flux<String> eventStreamSlow() {
        return Flux.interval(Duration.ofMillis(800))
                .map(i -> "event" + i);
    }

    private static Flux<String> eventStreamFast() {
        return Flux.interval(Duration.ofMillis(10))
                .map(i -> "event" + i);
    }

}
