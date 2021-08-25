package com.reactiveJavaProject.sec04Operators;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec04Operators.helper.OrderService;
import com.reactiveJavaProject.sec04Operators.helper.UserService;

public class Lec12FlatMap {

    public static void main(String[] args) {

        UserService.getUsers()
//                .log()
//                .map(user -> OrderService.getOrders(user.getUserId()))  //flux
                .flatMap(user -> OrderService.getOrders(user.getUserId()))  // mono / flux
//                .concatMap(user -> OrderService.getOrders(user.getUserId()))  // mono / flux
// .concatMap works similar to flatMap but is waiting tht the 1st publisher ends publishing before start the 2nd
//                .log()
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

}
