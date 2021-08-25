package com.reactiveJavaProject.sec03FluxCreateGenerate;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec06FluxGenerateAssignment {

    public static void main(String[] args) {

        //first solution
        Flux.generate(synchronousSink -> {
            System.out.println("emitting");
            synchronousSink.next(Util.faker().country().name());
        })
                .take(5)
                .takeUntil(country -> country.toString().equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());

        //second solution
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            System.out.println("emitting : " + country);
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("canada"))
                synchronousSink.complete();
        })
                .take(100)
                .subscribe(Util.subscriber());
    }
}