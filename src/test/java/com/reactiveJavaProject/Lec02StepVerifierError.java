package com.reactiveJavaProject;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec02StepVerifierError {

        private final Flux<Integer> integerFlux = Flux.just(1, 2, 3).concatWith(Flux.error(new RuntimeException("ops")));

        /*.expectError() MUST be follow by .verify() or replace with .verifyError() if we want a failing test in
        case of wrong actual value*/
    @Test
    public void errorGeneric() {
        StepVerifier.create(integerFlux)
                .expectNext(1,2,3)
                .expectError()
                .verify();
    }

    @Test
    public void errorSpecific() {
        StepVerifier.create(integerFlux)
                .expectNext(1,2,3)
                .verifyError(RuntimeException.class);
    }

    @Test
    public void errorSpecificMessage() {
        StepVerifier.create(integerFlux)
                .expectNext(1,2,3)
                .verifyErrorMessage("ops");
    }

    @Test
    public void errorMatches() {
        StepVerifier.create(integerFlux)
                .expectNext(1,2,3)
                .verifyErrorMatches(e -> e instanceof RuntimeException && e.getMessage().equals("ops"));
    }
}
