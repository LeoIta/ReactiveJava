package com.reactiveJavaProject.sec05ColdHotPublisher;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec05HotPublishAutoConnect {

    public static void main(String[] args) {

        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .autoConnect(0);
    /* we don't need any subscriber to start
    if the flux reaches the completed status before another user join,
        using .autoConnect() the publisher will not publish data anymore*/

        Util.sleepSeconds(3);
        System.out.println("Sam is about to join");

        //Sam will watch the movie from scene 4
        //user Sam watching movie in the Cinema
        movieStream
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(10);

        System.out.println("Mike is about to join");

        // mike is too late and will not be able to watch any scene
        //user Mike watching movie in the Cinema
        movieStream
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(30);


    }

    // movie in the Cinema
    private static Stream<String> getMovie() {
        System.out.println("Got the movie streaming req");
        return Stream.of(
                "Scene 1",
                "Scene 2",
                "Scene 3",
                "Scene 4",
                "Scene 5",
                "Scene 6",
                "Scene 7"
        );
    }

}
