package com.reactiveJavaProject.sec05ColdHotPublisher;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec04HotPublishResubscribe {

    public static void main(String[] args) {

        //share = publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(1);
    /*if the flux reaches the completed status before another user join,
        even using refCount we'll have behaviour as a cold publish*/

        //user Sam watching movie in the Cinema
        movieStream
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(10);

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
