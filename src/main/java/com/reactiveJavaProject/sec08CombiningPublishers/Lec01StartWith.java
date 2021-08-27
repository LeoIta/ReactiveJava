package com.reactiveJavaProject.sec08CombiningPublishers;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec08CombiningPublishers.helper.NameGenerator;

public class Lec01StartWith {

    public static void main(String[] args) {

        /* it will generate a new name only if there no one that match the
         requirement in the cache db*/
        NameGenerator generator = new NameGenerator();
        generator.generateNames()
                .take(2)
                .subscribe(Util.subscriber("Bob"));

        generator.generateNames()
                .take(3)
                .subscribe(Util.subscriber("Tom"));

        generator.generateNames()
                .log()
                .filter(o -> o.startsWith("L"))
                .take(3)
                .subscribe(Util.subscriber("Mark"));
    }
}
