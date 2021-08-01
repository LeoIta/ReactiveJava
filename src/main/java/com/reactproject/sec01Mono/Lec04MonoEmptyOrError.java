package com.reactproject.sec01Mono;

import com.reactproject.corseUtil.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {

    public static void main(String[] args) {

        System.out.println("#####################");
        System.out.println("User id=1");

        userRepository(1)
            .subscribe(
                Util.onNext(),
                Util.onError(),
                Util.onComplete()
        );

        System.out.println("#####################");
        System.out.println("User id=2");

        userRepository(2)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
        );

        System.out.println("#####################");
        System.out.println("User id=20");

        userRepository(20)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );



    }

    private static Mono<String> userRepository(int userId){
        //1
        if(userId == 1) {
            return Mono.just(Util.faker().name().firstName());
        }else if (userId == 2){
//            return null; // we cannot return null because then we could have nullPoint exception
            return Mono.empty(); // same of null for Mono
        }else
            return Mono.error(new RuntimeException("Not in the allowed range"));
    }
}
