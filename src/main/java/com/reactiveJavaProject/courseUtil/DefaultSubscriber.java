package com.reactiveJavaProject.courseUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber implements Subscriber<Object> {

    private String name = "";

    public DefaultSubscriber() {
    }

    public DefaultSubscriber(String name) {
        this.name = name + " - ";
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE); // I ask to give me all the publisher has
    }

    @Override
    public void onNext(Object o) {
        System.out.println(name + "Received : " + o);
    }

    @Override
    public void onError(Throwable err) {
        System.out.println(name +  "ERROR : " + err.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
}
