package com.reactiveJavaProject.sec09Batches;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Lec06Group {

    public static void main(String[] args) {

        Flux.range(1, 30)
                .delayElements(Duration.ofSeconds(1))
            .groupBy(i -> i % 2)  // keys 0, 1
            /* it will emit Flux<GroupedFlux<Integer,Integer>>
            * because it will group by the provided key and value; in this case key and value are both Integer*/
           .subscribe(gf -> process(gf, gf.key()));

        Util.sleepSeconds(60);

}

    private static void process(Flux<Integer> flux , int key){
        System.out.println("Called");
        flux.subscribe(i -> System.out.println("Key : " + key + ", Item : " + i));
    }
}
