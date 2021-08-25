package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec01Handle {
    public static void main(String[] args) {

        //handle = filter + map
        Flux.range(2, 10)
                .handle((integer, synchronousSink) -> {
                    if (integer % 2 == 0)
                        synchronousSink.next(integer); //filter
                    else
                        synchronousSink.next(integer + "a"); //map
                })
                .subscribe(Util.subscriber());

        System.out.println("############################");

        //handle = filter + map
        Flux.range(1, 10)
                .handle((integer, synchronousSink) -> {
                    if (integer == 7)
                        synchronousSink.complete();
                    else
                        synchronousSink.next(integer);
                })
                .subscribe(Util.subscriber());


    }


}
