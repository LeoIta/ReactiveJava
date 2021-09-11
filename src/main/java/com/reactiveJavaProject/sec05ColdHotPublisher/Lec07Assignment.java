package com.reactiveJavaProject.sec05ColdHotPublisher;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec05ColdHotPublisher.assignment.InventoryService;
import com.reactiveJavaProject.sec05ColdHotPublisher.assignment.OrderService;
import com.reactiveJavaProject.sec05ColdHotPublisher.assignment.RevenueService;

import java.time.Duration;

public class Lec07Assignment {
    public static void main(String[] args) {


        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        // revenue and inventory observe the order stream
        orderService
                .orderStream()
                .log()
                .subscribe(revenueService.subscribeOrderStream());

        orderService
                .orderStream()
                .subscribe(inventoryService.subscribeOrderStream());

        inventoryService
                .inventoryStream()
                .log()
                .subscribe(Util.subscriber("inventory"));

        revenueService
                .revenueStream()
                .log()
                .subscribe(Util.subscriber("revenue"));

        Util.sleepSeconds(10);
    }
}