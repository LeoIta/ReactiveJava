package com.reactiveJavaProject;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec06Scenario {

    private final Flux<Integer> integerFlux = Flux.just(1, 2, 3);

    @Test
    public void scenarioName() {
        StepVerifierOptions scenarioName = StepVerifierOptions.create().scenarioName("123 test");
        StepVerifier.create(integerFlux, scenarioName)
                .expectError()
                .verify();
    }

    @Test
    public void scenarioAs() {

        Flux<String> flux = Flux.just("a", "b1", "c");

        StepVerifier.create(flux)
                .expectNext("a")
                .as("a-test")
                .expectNext("b")
                .as("b-test")
                .expectNext("c")
                .as("c-test")
                .verifyComplete();
    }
    /* test2 will fail with the following error
    java.lang.AssertionError: expectation "b-test" failed (expected value: b; actual value: b1)
    */
}
