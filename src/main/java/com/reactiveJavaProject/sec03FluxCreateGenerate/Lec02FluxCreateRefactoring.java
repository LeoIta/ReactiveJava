package com.reactiveJavaProject.sec03FluxCreateGenerate;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec03FluxCreateGenerate.helper.NameProducer;
import reactor.core.publisher.Flux;

public class Lec02FluxCreateRefactoring {

    public static void main(String[] args) {

        NameProducer nameProducer = new NameProducer();

        Flux.create(nameProducer)
                .subscribe(Util.subscriber());

        Runnable runnable = nameProducer::produce;

        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(2);
    }

}
