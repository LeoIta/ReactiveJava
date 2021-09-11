package com.reactiveJavaProject.sec09Batches.assignment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

public class OrderProcessor {

    /*in both cases Function will take a Flux<PurchaseOrder> and will release a new one modified*/

    /*dedicated for automotive, it will increase the price of 10% */
    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> automotiveProcessing() {
        return flux -> flux
                .doOnNext(p -> p.setPrice(1.1 * p.getPrice()))
                .doOnNext(p -> p.setItemName("{{ " + p.getItemName() + " }}"));
    }

    /*dedicated for kids, it will apply a 50% discount and give an additional free item */
    public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsProcessing() {
        return flux -> flux
                .doOnNext(p -> p.setPrice(0.5 * p.getPrice()))
                .flatMap(p -> Flux.concat(Mono.just(p), getFreeKidsOrder()));
    }

    private static Mono<PurchaseOrder> getFreeKidsOrder() {
        return Mono.fromSupplier(() -> {
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setItemName("FREE - " + purchaseOrder.getItemName());
            purchaseOrder.setPrice(0);
            purchaseOrder.setCategory("Kids");
            return purchaseOrder;
        });
    }

    /*    simply way without Mono

 public static Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>> kidsProcessing() {
        return flux -> flux
                .doOnNext(p -> p.setPrice(0.5 * p.getPrice()))
                .flatMap(p -> Flux.just(p, getFreeKidsOrder()));
    }

    private static PurchaseOrder getFreeKidsOrder(){
            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setItemName("FREE - " + purchaseOrder.getItemName());
            purchaseOrder.setPrice(0);
            purchaseOrder.setCategory("Kids");
            return purchaseOrder;
        }
        */
}
