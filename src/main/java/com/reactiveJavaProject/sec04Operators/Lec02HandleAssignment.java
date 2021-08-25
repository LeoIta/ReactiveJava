package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec02HandleAssignment {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> synchronousSink.next(Util.faker().country().name()))
                .map(Object::toString)
                .handle((country, generation) -> {
                    generation.next(country);
                    if (country.equalsIgnoreCase("canada"))
                        generation.complete();
                })
                .subscribe(Util.subscriber());
    }
}
