package com.reactiveJavaProject.sec06ThreadingAndSchedulers;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class Lec09FluxInterval {

    public static void main(String[] args) {

        /*
        flux interval has for default parallel Schedules
         and you cannot change with subscribeOn()
        */

        Flux.interval(Duration.ofSeconds(1))
                .subscribeOn(Schedulers.boundedElastic())
                .log()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }
}
