package com.reactiveJavaProject.sec08CombiningPublishers;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec05CombineLatest {

    public static void main(String[] args) {

        /* .combineLatest will combine 2 publishers with the BI function defined as 3rd arg
          When one of the publishers emits a value, it will check the last value emitted by
          the other publisher to combine the 2 values
        */

        Flux.combineLatest(getString(), getNumber(), (s, i) -> s + i)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);

    }

    private static Flux<String> getString() {
        return Flux.just("A", "B", "C", "D")
                .delayElements(Duration.ofSeconds(1));
    }

    private static Flux<Integer> getNumber() {
        return Flux.just(1, 2, 3)
                .delayElements(Duration.ofSeconds(3));
    }
}