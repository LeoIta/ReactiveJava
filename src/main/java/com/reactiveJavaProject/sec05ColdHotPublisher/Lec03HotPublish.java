package com.reactiveJavaProject.sec05ColdHotPublisher;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

public class Lec03HotPublish {
    //You'll need an HOT PUBLISHER for BROADCASTING MESSAGES
    public static void main(String[] args) {

        //share = publish().refCount(1)
        Flux<String> movieStream = Flux.fromStream(() -> getMovie())
                .delayElements(Duration.ofSeconds(2))
                .publish()
                .refCount(2); //min subscriber means the min people needed before starting emitting content

        //user Sam is watching a movie in the Cinema
        movieStream
                .subscribe(Util.subscriber("sam"));

        Util.sleepSeconds(5);

        //user Mike is watching a movie in the Cinema
        movieStream
                .subscribe(Util.subscriber("mike"));

        Util.sleepSeconds(60);


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