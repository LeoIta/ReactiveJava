package com.reactiveJavaProject.sec08CombiningPublishers;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec06CarPriceAssignment {

    public static void main(String[] args) {

        final int newCarPrice = 10000;
Flux.combineLatest(getMonthlyDecrease(),getDemandRate(),(m,r) -> (newCarPrice - m) * r)
        .subscribe(Util.subscriber("new price"));

        Util.sleepSeconds(14);
    }

    private static Flux<Long> getMonthlyDecrease() {
        return Flux.interval(Duration.ofSeconds(1)).map(i->i*100);
    }

    private static Flux<Double> getDemandRate() {
        return Flux.interval(Duration.ofSeconds(3))
                .map(i -> 0.8 + 0.4 * Util.faker().random().nextDouble())
                .startWith(1.0);
    }
}
