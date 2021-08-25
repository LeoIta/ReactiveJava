package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

public class Lec03FluxFromArrayOrList {

    public static void main(String[] args) {

        //from list
        List<String> strings = Arrays.asList("a","b","c");
        Flux.fromIterable(strings)
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());
        System.out.println("##############");
        //from Array
        Integer[] arr = {2,3,4,5};
        Flux.fromArray(arr)
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

    }
}
