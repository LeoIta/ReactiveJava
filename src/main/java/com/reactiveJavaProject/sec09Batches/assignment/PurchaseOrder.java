package com.reactiveJavaProject.sec09Batches.assignment;

import com.reactiveJavaProject.courseUtil.Util;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PurchaseOrder {

    private String itemName;
    private double price;
    private String category;

    /*it will generate a random item when called*/
    public PurchaseOrder() {
        this.itemName = Util.faker().commerce().productName();
        this.price = Double.parseDouble(Util.faker().commerce().price()
                .replace(",", "."));
        this.category = Util.faker().commerce().department();
    }

}