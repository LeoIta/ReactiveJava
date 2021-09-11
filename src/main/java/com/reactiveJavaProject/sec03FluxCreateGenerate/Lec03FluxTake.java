package com.reactiveJavaProject.sec03FluxCreateGenerate;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec03FluxTake {

    public static void main(String[] args) {
        /* operators are:
        .map()       to add a function
        .filter()    to filter data
        .log()       to check the logs
        */

        //filter will print the first 2 but check all the 5 records
        Flux.range(1, 5)
                .log()
                .filter(i -> i < 3) //keeps checking all the other values
                .log()
                .subscribe(Util.subscriber());
/*
[INFO](main) | onSubscribe([Synchronous Fuseable]FluxRange.RangeSubscriptionConditional)
[INFO](main) | onSubscribe([Fuseable] FluxFilterFuseable.FilterFuseableSubscriber)
[INFO](main) | request(unbounded)
[INFO] (main) | request(unbounded)
[INFO] (main) | onNext(1)
[INFO] (main) | onNext(1)
Received: 1
[INFO] (main) | onNext(2)
[INFO] (main) | onNext(2)
Received: 2
[INFO] (main) | onNext(3)
[INFO] (main) | onNext(4)
[INFO] (main) | onNext(5)
[INFO] (main) | onComplete()
[INFO] (main) | onComplete()
Completed
*/

        //take will print the first 2 and cancel the subscription
        Flux.range(1, 5)
                .log()
                .take(2)  //take the first 3 element and cancels the subscription
                .log()
                .subscribe(Util.subscriber());

    }
}
