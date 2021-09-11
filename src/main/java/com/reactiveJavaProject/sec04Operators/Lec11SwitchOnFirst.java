package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec04Operators.helper.Person;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class Lec11SwitchOnFirst {

    public static void main(String[] args) {

        getPerson()
                .log()
                //.switchOnFirst accepts a BiFunction. based on the first element it eventually changes to the applyFilterMap
                .switchOnFirst((signal, personFlux) -> signal.isOnNext() && signal.get().getAge() > 18 ?
                        personFlux : applyFilterMap().apply(personFlux))
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
