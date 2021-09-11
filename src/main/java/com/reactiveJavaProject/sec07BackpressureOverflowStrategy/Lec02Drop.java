package com.reactiveJavaProject.sec07BackpressureOverflowStrategy;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class Lec02Drop {

    public static void main(String[] args) {

        /*
         import reactor.util.concurrent.Queues;
         FROM CLASS CALLED QUEUES WE CAN SEE WHY THE DEFAULT VALUE IS 256, THE MIN VALUE IS 16
         public final class Queues {
             public static final int CAPACITY_UNSURE = -2147483648;
             public static final int XS_BUFFER_SIZE = Math.max(8, Integer.parseInt(System.getProperty("reactor.bufferSize.x", "32")));
             public static final int SMALL_BUFFER_SIZE = Math.max(16, Integer.parseInt(System.getProperty("reactor.bufferSize.small", "256")));
        */

        /*
         if I want to change that value I have to set the system property
         System.setProperty("reactor.bufferSize.small", "16");
         you cannot set a value less than 16 or it will change automatically to the default value
        */

        /* using the .onBackpressureDrop(), we drop some items to avoid the 'out of memory' error */

        System.setProperty("reactor.bufferSize.small", "16");

        Flux.create(fluxSink -> {
            for (int i = 1; i < 501; i++) {
                fluxSink.next(i);
                Util.sleepMillis(1);
                System.out.println("Pushed : " + i);
            }
            fluxSink.complete();
        })
                .onBackpressureDrop()
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(i -> Util.sleepMillis(10))
                .subscribe(Util.subscriber());

    /* we set 16 but it will drain the 75% that is 12, where we have the 74 pushed
     Received : 12
     Pushed : 74
     it means that after 16 it will received the 74+1=75 and than 12 values until 74+12=86 values, from 86 it will move to the value
     publish when we reached 74 + (buffer/2) = 74+8=82 and so on.
     It will keep drain only 75% = 12 values for times then will free the memory and start again from the next available value
    */

        /*
          1-16
    Received : 74 + 8 = 82
        75-86
    Pushed : 141
        142-153
    Received : 141 + 8 = 149
    Pushed : 229
        230-241
    Received : 229 + 8 = 237
    Pushed : 301
        302-313
    Received : 301 + 8 = 309
    Pushed : 374
        375-386
    Received : 374 + 8 = 382
    Pushed : 445
        446-457
    Received : 445 + 8 = 453
    publisher already finished publishing
    Pushed : --
    then will finish at 457
    */

        Util.sleepSeconds(60);

    }

}