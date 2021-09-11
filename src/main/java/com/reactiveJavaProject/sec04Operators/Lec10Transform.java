package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec04Operators.helper.Person;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec10Transform {

    public static void main(String[] args) {

        getPerson()
                .log()
                //.transform accept a Function<T,R> it allows to use same step multiple times in different place
                .transform(applyFilterMap())
                .subscribe(Util.subscriber());
    }

    private static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }

    public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux
                .filter(p -> p.getAge() > 18)
                .doOnNext(p -> p.setName((p.getName().toUpperCase())))
                .doOnDiscard(Person.class, p -> System.out.println("Not allowing : " + p));
    }
}
