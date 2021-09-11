package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec07Timeout {

    public static void main(String[] args) {

        getOrderNumbers()
                .log()
//                .timeout(Duration.ofSeconds(2))
                /* after 2 sec if no item is emitted it will return error:
            ERROR : Did not observe any item or terminal signal within 2000ms in 'concatMap' (and no fallback has been configured)
                */
                .timeout(Duration.ofSeconds(2), fallback())
                /* after 2 sec if no item is emitted it will call the fallback */
                .subscribe(Util.subscriber());

        Util.sleepSeconds(10);
    }


    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(5));
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 10)
                .delayElements(Duration.ofSeconds(1));
    }
}
