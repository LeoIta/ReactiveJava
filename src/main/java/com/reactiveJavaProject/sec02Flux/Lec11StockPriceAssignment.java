package com.reactiveJavaProject.sec02Flux;

import com.reactiveJavaProject.sec02Flux.assignment.StockPricePublisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

public class Lec11StockPriceAssignment {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);
        //latch is similar to sleep but we use it when we don't know how long the sleep will be.
        // it will start sleeping until we count it down latch.countDown()

        StockPricePublisher.getPrice()
                .subscribeWith(new Subscriber<Integer>() {

                    private Subscription subscription;

                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        subscription.request(Long.MAX_VALUE);
                        //We make a lot of requests until when we do no reach the set threshold
                    }

                    @Override
                    public void onNext(Integer price) {
                        System.out.println(LocalDateTime.now() + " : Price : " + price);
                        if (price > 130 || price < 70) { // set the thresholds
                            this.subscription.cancel();
                            latch.countDown(); // stop the sleeping and in this case ends the subscriber
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        latch.countDown();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });

        latch.await();
    }
}




