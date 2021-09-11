package com.reactiveJavaProject.sec09Batches;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

public class Lec05Window {

    public static void main(String[] args) {

        eventStream()
                .window(3, 4)
                /* it will emit Flux<Flux<String>>*/
                .flatMap(flux -> saveEvents(flux))
                .subscribe(Util.subscriber("windowMaxSize"));

        eventStream()
                .window(Duration.ofSeconds(5))
                .flatMap(flux -> saveEvents(flux))
                .subscribe(Util.subscriber("windowDuration"));

        Util.sleepSeconds(10);
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event" + i);
    }

    private static Mono<Void> saveEvents(Flux<String> flux) {
        return flux.
                doOnNext(s -> System.out.println("Received : " + s))
                .then();
    }

}