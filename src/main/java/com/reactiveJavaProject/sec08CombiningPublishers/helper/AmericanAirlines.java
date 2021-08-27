package com.reactiveJavaProject.sec08CombiningPublishers.helper;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class AmericanAirlines {

    public static Flux<String> getFlights() {
        return Flux.range(1, Util.faker().random().nextInt(1, 15))
                .delayElements(Duration.ofSeconds(1))
                .map(i -> "American Lines" + Util.faker().random().nextInt(100, 999))
                .filter(i -> Util.faker().random().nextBoolean());
    }
}
