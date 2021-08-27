package com.reactiveJavaProject.sec08CombiningPublishers;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec04Zip {

    public static void main(String[] args) {

        /*it will emit same amount of values as the publisher that emits the less */
        Flux.zip(getBody(), getEngine(), getTires())
                //to get one of the 3 publishers
                .doOnNext(tuple -> System.out.println(tuple.getT1()))
                .subscribe(Util.subscriber());

    }

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "body");
    }

    private static Flux<String> getEngine() {
        return Flux.range(1, 3)
                .map(i -> "engine");
    }

    private static Flux<String> getTires() {
        return Flux.range(1, 6)
                .map(i -> "tires");
    }

}
