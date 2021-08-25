package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec08DefaultIfEmpty {

    public static void main(String[] args) {

        //if no value passes the filter, we have empty object then the .defaultIfEmpty will provide a constant value
        get10OrderNumber()
                .filter(i -> i > 10)
                .defaultIfEmpty(-100)
                .subscribe(Util.subscriber());

        System.out.println("##############");

        //if any value passes the filter, we have not empty object, then the .defaultIfEmpty will not be called
        get12OrderNumber()
                .filter(i -> i > 10)
                .defaultIfEmpty(-100)
                .subscribe(Util.subscriber());

    }

    private static Flux<Integer> get10OrderNumber() {
        return Flux.range(1, 10);
    }

    private static Flux<Integer> get12OrderNumber() {
        return Flux.range(1, 12);
    }

}
