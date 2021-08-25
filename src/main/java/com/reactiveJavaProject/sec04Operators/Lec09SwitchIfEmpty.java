package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec09SwitchIfEmpty {

    public static void main(String[] args) {


        //if no value passes the filter, we have empty object then the .switchIfEmpty will call the fallback method
        getOrderNumber()
                .filter(i -> i > 10)
                .switchIfEmpty(fallback())
                .subscribe(Util.subscriber());

    }

    /* if redis doesn't contain the requested info, thn I switch to the the db to get the info*/
    // redis cache
    private static Flux<Integer> getOrderNumber() {
        return Flux.range(1, 10);
    }

    // db
    private static Flux<Integer> fallback() {
        return Flux.range(20, 22);
    }
}
