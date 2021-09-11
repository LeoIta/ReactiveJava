package com.reactiveJavaProject.sec01Mono;


import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String[] args) {

        //publisher
        Mono<Integer> mono = Mono.just(1);

        System.out.println(mono); //it will print only the kind of object nothing more, we need to subscribe it ------> MonoJust

        System.out.println(mono.subscribe());  // still not what I want ------->    reactor.core.publisher.LambdaMonoSubscriber@3159c4b8

        //subscribe(Consumer<? super T> consumer)
        mono.subscribe(i -> System.out.println("Received: " + i)); // we use the simplest method ---> 1
    }
}
