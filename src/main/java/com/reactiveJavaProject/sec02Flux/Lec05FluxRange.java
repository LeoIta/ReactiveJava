package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxRange {

    public static void main(String[] args) {

//10 integer starting from 3
        Flux.range(3, 10)
                .log()
                .subscribe(
                        Util.onNext()
                );
// 10 values
// with map I told Java I want a kind of for loop 10 times
        Flux.range(3, 10)
                .log()
                .map(i -> Util.faker().name().fullName())
                .log()
                .subscribe(
                        Util.onNext()
                );
        //using .log() I can check what happens

    }
}
