package com.reactiveJavaProject;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec05VirtualTime {

    private Flux<String> timeConsumingFlux() {
        return Flux.range(1, 4)
                .delayElements(Duration.ofSeconds(5))
                .map((i -> i + "a"));
    }

    @Test
    @Disabled
    public void slowTest() {
        /*it is disabled as for it we have to wait around 4x5= 20 seconds */
        StepVerifier.create(timeConsumingFlux())
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }


    @Test
    public void fastTest() {
        /* next 2 lines code will simulate 30 sec period */
        StepVerifier.withVirtualTime(this::timeConsumingFlux)
                .thenAwait(Duration.ofSeconds(30))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }


    @Test
    public void expectNoEventAndSubscription(){
        StepVerifier.withVirtualTime(() -> timeConsumingFlux())
                .expectSubscription() // subscription is an event
                .expectNoEvent(Duration.ofSeconds(4))
                .thenAwait(Duration.ofSeconds(20))
                .expectNext("1a", "2a", "3a", "4a")
                .verifyComplete();
    }
}
