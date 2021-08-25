package com.reactiveJavaProject.sec02Flux.assignment;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class StockPricePublisher {

    public static Flux<Integer> getPrice(){
        AtomicInteger atomicInteger = new AtomicInteger(100);
        return  Flux.interval(Duration.ofSeconds(1))
                    .map(i-> atomicInteger.getAndAccumulate(
                        Util.faker().random().nextInt(-10,10),
                        Integer::sum
                ));
    }
}
