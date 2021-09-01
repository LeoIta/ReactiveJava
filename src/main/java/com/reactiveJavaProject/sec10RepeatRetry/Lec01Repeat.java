package com.reactiveJavaProject.sec10RepeatRetry;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec01Repeat {

    public static void main(String[] args) {

        getNumbers()
                .repeat(2) //2 means it will emit another 2 times, so 3 in total; just .repeat() it will create an infinite loop
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 4)
                .doOnSubscribe(i -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("flux Completed"));
    }
}