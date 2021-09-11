package com.reactiveJavaProject;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03StepVerifierRange {

        private final Flux<Integer> range = Flux.range(1, 50);

    @Test
    public void expectNextCount() {
        StepVerifier.create(range)
                .expectNext(1)
                .expectNextMatches(i -> i%2==0)
                .expectNextCount(48)
                .verifyComplete();
    }

    @Test
    public void consumeWhile() {
        StepVerifier.create(range)
                .expectNext(1,2)
                .thenConsumeWhile(i -> i<100) // test will pass until we have less then 100 additional next value
                .verifyComplete();
    }

}
