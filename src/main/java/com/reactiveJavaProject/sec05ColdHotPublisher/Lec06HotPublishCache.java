package com.reactiveJavaProject.sec05ColdHotPublisher;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec06HotPublishCache {

    public static void main(String[] args) {

        //cache() = publish().replay() int.max
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
//                .cache();
                .cache(2); // will keep last too records for the subscriber that are late
//                .publish().replay();

        System.out.println("Sam is about to join");
        Util.sleepSeconds(2);
        //user Sam is watching a movie in the Cinema
        movieStream
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(6);

        System.out.println("Mike is about to join");

        // mike is too late and will not be able to watch all scenes but just the remaining ones plus the last 2
        //user Mike is watching movie in the Cinema
        movieStream
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(15);


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