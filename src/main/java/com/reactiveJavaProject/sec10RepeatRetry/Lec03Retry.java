package com.reactiveJavaProject.sec10RepeatRetry;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec03Retry {

    public static void main(String[] args) {

        getNumbers()
                .retry(2)
                /*2 means it will emit another 2 times, so 3 in total; just .repeat() it will create an infinite loop*/
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 4)
                .doOnSubscribe(i -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("flux Completed"))
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(e -> System.out.println("ERROR"));
    }
}