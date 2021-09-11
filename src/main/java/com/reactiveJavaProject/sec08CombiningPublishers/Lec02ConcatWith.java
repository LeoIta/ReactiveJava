package com.reactiveJavaProject.sec08CombiningPublishers;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec02ConcatWith {
    public static void main(String[] args) {

        Flux<String> flux1 = Flux.just("a", "b");
        Flux<String> flux2 = Flux.error(new RuntimeException("oops"));
        Flux<String> flux3 = Flux.just("c", "d", "e");

        /*
        flux1.concatWith(flux2) allows you to concatenate one publisher for time
        Flux.concat(fluxA, flux3) allows you to concatenate more than 1 publisher for time
        */
        Flux<String> fluxA = flux1.concatWith(flux2);
        Flux<String> fluxB = Flux.concat(fluxA, flux3);

        fluxA.subscribe(Util.subscriber("flux A"));
        fluxB.subscribe(Util.subscriber("flux B"));

        /* if there is an error, it delays at the end without interrupting
         the publishing
        */

        Flux.concatDelayError(fluxA, flux3)
                .subscribe(Util.subscriber("flux C"));

    }
}
