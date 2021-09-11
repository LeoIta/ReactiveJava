package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec01FluxJust {

    public static void main(String[] args) {

        //1 element
        Flux<Integer> flux1 = Flux.just(1);

        flux1.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        // many elements
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);

        flux.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        // 0 elements
        Flux<Integer> flux0 = Flux.empty();

        flux0.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        // 0 elements + onError
        Flux<Integer> fluxErr = Flux.error(new RuntimeException("ERROR!"));

        fluxErr.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }
}
