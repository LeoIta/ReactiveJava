package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec02Flux.helper.NameGenerator;

import java.util.List;

public class Lec07FluxVsList {

    public static void main(String[] args) {

        List<String> names = NameGenerator.getNamesList(5);
        System.out.println(names); // I have to wait 5 second to get the all list printed

        NameGenerator.getNamesFlux(5)
                .subscribe(Util.onNext()); // I'll have a name every second and all data after 5 sec
    }
}
