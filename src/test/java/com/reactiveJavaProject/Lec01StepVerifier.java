package com.reactiveJavaProject;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec01StepVerifier {

    private final Flux<Integer> integerFlux = Flux.just(1, 2, 3);

    @Test
    public void expectComplete() {
        StepVerifier.create(integerFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(4)
                .expectComplete();
        /*this test will pass always as there is no .verify()
        we MUST use .expectComplete().verify()*/
    }

    @Test
    public void failingExpectNextOneArg() {
        StepVerifier.create(integerFlux)
                .expectNext(1)
                .expectNext(2)
                .expectNext(4)
                .expectComplete()
                .verify(); //it will fail as the third .expectNext() should have 3 and not 4 as arg
    }

    @Test
    public void expectNextMatches() {
        StepVerifier.create(integerFlux)
                .expectNext(1, 2)
                .expectNextMatches(i -> i % 2 != 0)
                .verifyComplete();
    }

    @Test
    public void expectNextMultipleArg() {
        StepVerifier.create(integerFlux)
                .expectNext(1, 2, 3)
                .verifyComplete();
    }
}
