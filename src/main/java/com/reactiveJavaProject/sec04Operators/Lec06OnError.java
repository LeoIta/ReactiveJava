package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Lec06OnError {
    public static void main(String[] args) {


        Flux.range(1, 10)
                .log()
                .map(i -> 10 / (5 - i))
//      onErrorReturn(value) in case of error it cancel the subscription and it returns the indicated value
//                 .onErrorReturn(-1)
//      .onErrorResume(e -> method()) in case of error it cancels the subscription and it calls another method
//                .onErrorResume(e -> fallback()
//      .onErrorContinue((err, obj) in case of error doesn't cancel the subscription and execute the block before the next
                .onErrorContinue((err, obj) ->
                    System.out.println("error : \n" + err + "\nvalue that create error : \n" + obj.toString())
                )
                .subscribe(Util.subscriber());

    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }

}
