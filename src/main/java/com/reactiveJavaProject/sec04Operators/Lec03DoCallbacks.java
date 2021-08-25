package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec03DoCallbacks {

    public static void main(String[] args) {

        Flux.create(fluxSink -> {
            System.out.println("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
            System.out.println("--completed");
        })
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doFirst(() -> System.out.println("doFirst 1"))
                .doOnNext(o -> System.out.println("doOnNext : " + o))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe 1" + s))
                .doOnRequest(l -> System.out.println("doOnRequest : " + l))
                .doFirst(() -> System.out.println("doFirst 2"))
                .doOnError(err -> System.out.println("doOnError : " + err.getMessage()))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doFinally(signal -> System.out.println("doFinally 1 : " + signal))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe 2" + s))
                .doFirst(() -> System.out.println("doFirst 3"))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o))
                .take(2)
                .doFinally(signal -> System.out.println("doFinally 2 : " + signal))
                .subscribe(Util.subscriber());
    }
}
