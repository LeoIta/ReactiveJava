package com.reactiveJavaProject.sec01Mono;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    public static void main(String[] args) {

        //publisher
        Mono<String> mono = Mono.just("ball");
        System.out.println("#####################");
        System.out.println("mono:");
        //1 .subscribe()
        mono.subscribe(); // it does nothing as it is only telling that he needs info

        //2 subscribe(@Nullable Consumer<? super T> consumer, @Nullable Consumer<? super Throwable> errorConsumer, @Nullable Runnable completeConsumer) {
        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

//###########################################################################################
        //publisher
        Mono<String> monoErr = Mono.just("ball")
                .map(String::length)
                .map(l->l.toString().substring(3));

        //3 .subscribe()
//        monoErr.subscribe(); // it will return an error
        System.out.println("#####################");
        System.out.println("monoErr:");
        //2 subscribe(@Nullable Consumer<? super T> consumer, @Nullable Consumer<? super Throwable> errorConsumer, @Nullable Runnable completeConsumer) {
        monoErr.subscribe(
                item -> System.out.println("monoErr: " + item),
                err-> System.out.println("monoErr: " + err.getMessage()),
                () -> System.out.println("monoErr completed")
        ); //is not returning an error but only the error message

        System.out.println("#####################");
        System.out.println("monoErr:");
        monoErr.subscribe(
            Util.onNext(),
            Util.onError(),
            Util.onComplete()
        );
    }
}
