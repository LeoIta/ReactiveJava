package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import reactor.core.publisher.Flux;


import java.time.Duration;

public class Lec05Delay {
    public static void main(String[] args) {



/*  import reactor.util.concurrent.Queues;
    FROM CLASS CALLED QUEUES WE CAN SEE WHY THE DEFAULT VALUE IS 32, THE MIN VALUE IS 8
    public final class Queues {
        public static final int CAPACITY_UNSURE = -2147483648;
        public static final int XS_BUFFER_SIZE = Math.max(8, Integer.parseInt(System.getProperty("reactor.bufferSize.x", "32")));
*/

        Flux.range(1, 100)  // 32
                .log()
                .delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber());
// if I want to change that value I have to set the system property
//        System.setProperty("reactor.bufferSize.x", "9");
// you cannot set a value less than 8 or it will change automatically to the default value
        System.setProperty("reactor.bufferSize.x", "5");


        Util.sleepSeconds(60); //without the sleep it will produce 32 but not consume anyone
    }
}
