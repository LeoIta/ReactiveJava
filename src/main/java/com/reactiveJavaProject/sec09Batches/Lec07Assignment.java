package com.reactiveJavaProject.sec09Batches;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec09Batches.assignment.OrderProcessor;
import com.reactiveJavaProject.sec09Batches.assignment.OrderService;
import com.reactiveJavaProject.sec09Batches.assignment.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Lec07Assignment {

    public static void main(String[] args) {

        Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> categoryMap = Map.of(
                "Kids", OrderProcessor.kidsProcessing(), //what to do in case the category is kids
                "Automotive", OrderProcessor.automotiveProcessing() //what to do in case the category is automotive
        );

        Set<String> set = categoryMap.keySet();

        OrderService.getOrderStream() //start emitting random item to purchase
                .filter(p -> set.contains(p.getCategory())) // filter only the categories defined in the categoryMap
                .groupBy(PurchaseOrder::getCategory)  // group by 2 keys to get a groupedFlux
                // Flux<GroupedFlux<String,PurchaseOrder>>
                .flatMap(gf -> categoryMap.get(gf.key()).apply(gf)) /* get the key and provide the value
                that in this case will be the flux method to apply that will be Flux<PurchaseOrder>>*/
                /*N.B we use the flatMap to go inside the flux, with only map I'll get
                Received : FluxPeekFuseable
                Received : FluxFlatMap
               */
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);

    }
}
