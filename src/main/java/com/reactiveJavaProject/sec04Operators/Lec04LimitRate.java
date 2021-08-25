package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec04LimitRate {

    public static void main(String[] args) {

        Flux.range(1, 30)
                .log()
//                .limitRate(10) // 75% requests 10 but consumes 8, then requests 8
//                .limitRate(10,10) // 75% requests 10 but consumes 8, then requests 8
//                .limitRate(10,9) // 90% request 10 but consumes 9, then requests 9
                .limitRate(10, 0) // 100% requests and consumes 10, then requests 10
                .subscribe(Util.subscriber());
    }
}
