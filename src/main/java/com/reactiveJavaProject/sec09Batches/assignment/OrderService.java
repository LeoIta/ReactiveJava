package com.reactiveJavaProject.sec09Batches.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class OrderService {

    /*every 100 millisecond generate a new random item using PurchaseOrder*/
    public static Flux<PurchaseOrder> getOrderStream() {

        return Flux.interval(Duration.ofMillis(100))
                .map(i -> new PurchaseOrder());
    }
}
