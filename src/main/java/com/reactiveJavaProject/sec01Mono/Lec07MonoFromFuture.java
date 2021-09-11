package com.reactiveJavaProject.sec01Mono;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec07MonoFromFuture {
    public static void main(String[] args) {

        Mono.fromFuture(getName())
                .subscribe(
                        Util.onNext()
                );

        Util.sleepSeconds(1); // without sleep will not print the name

    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(
                () -> Util.faker().name().fullName());
    }


}