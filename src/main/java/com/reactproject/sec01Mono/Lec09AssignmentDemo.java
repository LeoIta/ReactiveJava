package com.reactproject.sec01Mono;

import com.reactproject.corseUtil.Util;
import com.reactproject.sec01Mono.assignment.FileService;
import reactor.core.publisher.Mono;

public class Lec09AssignmentDemo {
    public static void main(String[] args) {

        FileService.read("file01.txt")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

        System.out.println("#########################");
        FileService.write("file01.txt", "my first file")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

        System.out.println("#########################");
        FileService.write("file02.txt", "my second file")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

        System.out.println("#########################");
        FileService.delete("file02.txt")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

        System.out.println("#########################");
        FileService.delete("file02.txt")
                .subscribe(Util.onNext(),
                        Util.onError(),
                        Util.onComplete());

    }
}
