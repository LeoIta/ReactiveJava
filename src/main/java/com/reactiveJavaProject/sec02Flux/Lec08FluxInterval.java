package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec08FluxInterval {

    public static void main(String[] args) {
//this interval is useful when you want to send an update at regular interval
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(Util.onNext());

// in order to see the result of the Flux.interval that release a vlaue every second we should add a delay in the code

        Util.sleepSeconds(6); //in this way we should have 6 output

    }
}
