package com.reactiveJavaProject.sec10RepeatRetry;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec04RetryWhen {

    public static void main(String[] args) {

        getNumbers()
                .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
                /*it will retry twice with 3 sec interval*/
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }

    private static Flux<Integer> getNumbers() {
        return Flux.range(1, 4)
                .doOnSubscribe(i -> System.out.println("Subscribed"))
                .doOnComplete(() -> System.out.println("flux Completed"))
                .map(i -> i / (Util.faker().random().nextInt(1, 5) > 3 ? 0 : 1))
                .doOnError(e -> System.out.println("ERROR"));
    }
}