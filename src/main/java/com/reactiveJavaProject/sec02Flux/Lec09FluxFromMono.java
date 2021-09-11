package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec09FluxFromMono {

    public static void main(String[] args) {

        Mono<String> mono = Mono.just("a");
        //change Mono to Flux if I have a function that accepts Flux instead of Mono, like doSomething()
        Flux<String> flux = Flux.from(mono);
        // it will emit only 1 item
        flux.subscribe(Util.onNext());

    }

    private static void doSomething(Flux<String> flux) {
        //do something
    }
}