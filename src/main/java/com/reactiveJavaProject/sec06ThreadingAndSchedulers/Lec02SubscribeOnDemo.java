package com.reactiveJavaProject.sec06ThreadingAndSchedulers;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/*Reactor comes with several default Scheduler implementations, each with its own specificity about how it manages Workers.
 They can be instantiated via the Schedulers factory methods. Here are rule of thumbs for their typical usage:
- Schedulers.immediate()
  can be used as a null object for when an API requires a Scheduler but you don’t want to change threads
- Schedulers.single()
  is for one-off tasks that can be run on a unique ExecutorService
- Schedulers.parallel()
  it has only 4 threads is good for CPU-intensive but short-lived tasks. It can execute N such tasks in parallel (by default N == number of CPUs)
- Schedulers.elastic() and Schedulers.boundedElastic()
  boundedElastic() has a lot more threads then parallel, if you are in doubt, choose it
  are good for more long-lived tasks (eg. blocking IO tasks).
  The elastic one spawns threads on-demand without a limit while the recently introduced boundedElastic does the same with
  a ceiling on the number of created threads.*/

public class Lec02SubscribeOnDemo {
    public static void main(String[] args) {

        Flux<Object> flux = Flux.create(fluxSink -> {
            printThreadName("create");
            fluxSink.next(1);
        })
                .doOnNext(i -> printThreadName("next " + i));

        Runnable runnable = () -> flux
                .doFirst(() -> printThreadName("first2"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> printThreadName("first1"))
                .subscribe(v -> printThreadName("sub " + v));

//main Thread used until we reach the subscribeOn, then system uses boundedElastic pool
        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        Util.sleepSeconds(5);

    }

    private static void printThreadName(String msg) {
        System.out.println(msg + "\t\t: Thread : " + Thread.currentThread().getName());
    }
}
