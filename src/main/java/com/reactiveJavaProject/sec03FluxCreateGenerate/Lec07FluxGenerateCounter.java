package com.reactiveJavaProject.sec03FluxCreateGenerate;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

public class Lec07FluxGenerateCounter {

    public static void main(String[] args) {

        // generate country name until name is canada or after 10 countries
        Flux.generate(
                () -> 1, //initial value of the counter
                (counter, sink) -> {
                    String country = Util.faker().country().name();
                    System.out.println("emitting : " + country);
                    sink.next(country);
                    if (country.equalsIgnoreCase("canada") || counter >= 10)
                        sink.complete();
                    return counter + 1;
                }
        )
                .subscribe(Util.subscriber());
    }
}
