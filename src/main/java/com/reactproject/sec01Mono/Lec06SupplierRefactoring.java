package com.reactproject.sec01Mono;

import com.reactproject.corseUtil.Util;
import reactor.core.publisher.Mono;

public class Lec06SupplierRefactoring {

    public static void main(String[] args) {
        System.out.println("#####################");
        System.out.println("calling the getName method");
        getName();
        System.out.println("#####################");
        System.out.println("calling the getName method + .subscribe()");
        getName().subscribe();
        System.out.println("#####################");
        System.out.println("calling the getName method + .subscribe(onNext)");
        getName().subscribe(
                Util.onNext()
        );
        System.out.println("#####################");
        System.out.println("calling the getName method");
        getName();


    }

    private static Mono<String> getName(){
        System.out.println("entered getName method");
        return Mono.fromSupplier(() -> {
            System.out.println("Generating name ..");
            Util.sleepSeconds(3);
            return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }
}
