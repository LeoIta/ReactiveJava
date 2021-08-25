package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec10MonoFromFlux {
    public static void main(String[] args) {

        System.out.println("#####################################");
        //it can be used if I need just the first value from Flux
        Mono<Integer> mono = Mono.from(Flux.range(1,10));
        mono
            .subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete());
        System.out.println("#####################################");
        //it will return 1, as it is the first value
        Flux.range(1,10)
                .next() // will return only the first value
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
        System.out.println("#####################################");
        //it will return 5, as it is the first value bigger than 4
        Flux.range(1,10)
                .filter(i -> i > 4)
                .next() // will return only the first value
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
        System.out.println("#####################################");
        //it will select the 1st value 1, then the it will be filtered out because it is not bigger then 4, so we''l have a Flux.empty()
        //nothing will be returned
        Flux.range(1,10)
                .next() // will return only the first value
                .filter(i -> i > 4)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
    }
}
