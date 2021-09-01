package com.reactiveJavaProject.sec10RepeatRetry;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec05RetryWhenAdvanced {

    public static void main(String[] args) {

        orderService()
                .retryWhen(Retry.from(
                        flux -> flux
                                .doOnNext(retrySignal -> {
                                    System.out.println("total tries : " + retrySignal.totalRetries());
                                    System.out.println(retrySignal.failure().getMessage());
                                })
                                .handle((retrySignal, synchronousSink) -> {
                                    if (retrySignal.failure().getMessage().equals("500 Server Error"))
                                        synchronousSink.next(1); //if error is 500 it will retry
                                    else
                                        synchronousSink.error(retrySignal.failure()); // if error is 404 error will be thrown
                                })
                                .delayElements(Duration.ofSeconds(1))
                ))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }


    //order service
    private static Mono<String> orderService() {
        return Mono.fromSupplier(() -> {
            processPayment();
            return Util.faker().idNumber().valid();
        });
    }

    //payment service
    private static void processPayment() {
        int random = Util.faker().random().nextInt(1, 10);
        if (random < 8)
            throw new RuntimeException("500 Server Error"); // we make have a retry
        else if (random < 10)
            throw new RuntimeException("404 not found"); // no retry
    }

}