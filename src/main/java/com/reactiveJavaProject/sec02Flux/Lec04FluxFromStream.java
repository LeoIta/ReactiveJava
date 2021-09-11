package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Stream;

public class Lec04FluxFromStream {

    public static void main(String[] args) {

        //from Stream
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();

        //stream can be used only once
//        stream.forEach(System.out::println); //stream closed
//        stream.forEach(System.out::println);

//      Flux<Integer> integerFlux = Flux.fromStream(() -> stream);
//      Flux<Integer> integerFlux = Flux.fromStream(stream);
// you can use only once and the 2nd subscribe will give error

        Flux<Integer> integerFlux = Flux.fromStream(list::stream);
        //here we create every time a new stream
        integerFlux
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

        integerFlux
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
    }
}
