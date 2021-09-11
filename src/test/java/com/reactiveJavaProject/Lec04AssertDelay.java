package com.reactiveJavaProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec04AssertDelay {

    @Test
    public void assertNext() {
        Flux<Integer> integerFlux = Flux.range(1, 50);
        StepVerifier
                .create(integerFlux)
                .assertNext(i -> Assertions.assertNotNull(i))
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    @Test
    public void assertDelay() {
        Flux<Integer> integerFlux = Flux.range(1, 50)
                .delayElements(Duration.ofMillis(40));

        StepVerifier
                .create(integerFlux)
                .assertNext(Assertions::assertNotNull)
                .expectNextCount(49)
                .expectComplete()
                .verify(Duration.ofSeconds(3));
        /*if the duration here is not enough to emit all the elements the test will fail*/
    }

}
