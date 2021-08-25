package com.reactiveJavaProject.sec01Mono;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec05MonoFromSupplierOrFromCallable {

    public static void main(String[] args) {

       // use just only when you have already data, not other method called. Even without subscriber the method is called
//        Mono<String> mono = Mono.just(getName());
        System.out.println("#####################");
        System.out.println("using fromSupplier");

        Supplier<String> stringSupplier = () -> getName();
        Mono<String> mono =  Mono.fromSupplier(stringSupplier); //method is called only with subscriber, only when it is needed

        mono.subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        System.out.println("#####################");
        System.out.println("using fromCallable");

        Callable<String> stringCallable = () -> getName();//Callable and from Callable work same way of Supplier
        Mono.fromCallable(stringCallable)
                .subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );
    }


    private static String getName(){
        System.out.println("Generating name ..");
        return Util.faker().name().fullName();
    }
}
