package com.reactiveJavaProject.sec12Context;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02CtxUpdate {

    public static void main(String[] args) {

        getWelcomeMessage()
                .contextWrite(ctx-> ctx.put("user", ctx.get("user").toString().toUpperCase()))
                .contextWrite(Context.of("user","sam"))
                .subscribe(Util.subscriber());
        // in line 12 we update the context on line 12. We take the value and change it to uppercase
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
