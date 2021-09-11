package com.reactiveJavaProject.sec01Mono;

import java.util.stream.Stream;

public class Lec01Stream {
    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i * 2;
                });

        System.out.println(stream); // lazy stream will not print 1 as the stream content but the object ----->java.util.stream.ReferencePipeline$Head@36d64342
        stream.forEach(System.out::println);// we have to specify that has to print the content -----> 2
    }

}