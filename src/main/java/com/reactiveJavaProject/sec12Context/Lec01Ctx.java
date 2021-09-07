package com.reactiveJavaProject.sec12Context;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Ctx {

    public static void main(String[] args) {
        getWelcomeMessage()
                .subscribe(Util.subscriber());
        // ERROR : unauthenticated because no context has been set
        System.out.println("****************");

        getWelcomeMessage()
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber());
        System.out.println("****************");

        getWelcomeMessage()
                .contextWrite(Context.of("user","bob"))
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber());
        // the context is going from the end to the top, the last valid value will be bob
        System.out.println("****************");


        getWelcomeMessage()
                .contextWrite(Context.of("users","bob"))
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber());
        // the context is looking for the key user, then we'll have sam
        System.out.println("****************");
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome " + ctx.get("user"));
            } else {
                return Mono.error(new RuntimeException("unauthenticated"));
            }
        });
    }
}
