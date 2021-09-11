package com.reactiveJavaProject.sec06ThreadingAndSchedulers;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class Lec08ParallelWithList {

    public static void main(String[] args) {

        /*
        N.B. ArrayList is not threads safe
        the returned size will be often different from 10, as publisher is not responsible
        for multi threads safety
        */
        List<Integer> list = new ArrayList<>();

        Flux.range(1, 10)
                .parallel()
                .runOn(Schedulers.parallel())
                .subscribe(list::add);

        Util.sleepSeconds(5);
        System.out.println(list.size());
    }
}
