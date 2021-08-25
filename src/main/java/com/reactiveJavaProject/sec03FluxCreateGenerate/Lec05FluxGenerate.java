package com.reactiveJavaProject.sec03FluxCreateGenerate;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec05FluxGenerate {

    public static void main(String[] args) {

        Flux.generate(synchronousSink -> {
            System.out.println("emitting");
            synchronousSink.next(Util.faker().country().name()); //1
// synchronousSink.next(Util.faker().country().name());
// cannot be called twice as this generate a new object with a single output
        })
                .take(5)
                .subscribe(Util.subscriber());
    }
}
