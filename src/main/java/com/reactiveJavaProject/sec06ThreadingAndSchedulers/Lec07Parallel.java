package com.reactiveJavaProject.sec06ThreadingAndSchedulers;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec07Parallel {

    public static void main(String[] args) {

        /*even if we have a single publisher and a single subscriber
        we use different threads*/
        Flux.range(1, 10)
                .parallel(10)
//                .parallel()
        //  without any value let the system decide how many threads
                .runOn(Schedulers.parallel())
                .doOnNext(i -> printThreadName("next " + i))
                .sequential()
                // using sequential() we switch back to the asynchronous threads
                .subscribe(v -> printThreadName("sub " + v));

        Util.sleepSeconds(5);

    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
