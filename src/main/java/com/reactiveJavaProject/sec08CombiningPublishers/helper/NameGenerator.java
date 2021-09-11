package com.reactiveJavaProject.sec08CombiningPublishers.helper;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    //cache
    private List<String> list = new ArrayList<>();

    public Flux<String> generateNames() {
        return Flux.generate(stringSynchronousSink -> {
            System.out.println("Generated fresh ");
            Util.sleepSeconds(1);
            String name = Util.faker().name().firstName();
            list.add(name);
            stringSynchronousSink.next(name);
        })
                .cast(String.class)
                .startWith(getFromCache());
        //it will first check if there are values in the cache that met the requirements
    }

    private Flux<String> getFromCache() {
        System.out.println("from cache");
        return Flux.fromIterable(list);
    }
}
