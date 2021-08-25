package com.reactiveJavaProject.sec04Operators.helper;

import com.reactiveJavaProject.courseUtil.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {

    private String name;
    private int age;

    public Person() {
        this.name = Util.faker().name().firstName();
        this.age = Util.faker().random().nextInt(1,30);
    }
}
