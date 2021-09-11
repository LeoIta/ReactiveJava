package com.reactiveJavaProject.sec10RepeatRetry;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;


public class Lec02RepeatWithCondition {

    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        getNumbers()
                .repeat(() -> atomicInteger.get() < 13) // repeat is checked everytime we reach the completed status
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 4)
                .doOnSubscribe(i -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("flux Completed"))
                .map(i -> atomicInteger.getAndIncrement());
        /* thanks to lines 6, 11 and 25 it will emit number incrementing them every time everytime */
    }
}
