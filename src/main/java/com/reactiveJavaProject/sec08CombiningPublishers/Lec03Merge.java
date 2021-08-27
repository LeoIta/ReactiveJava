package com.reactiveJavaProject.sec08CombiningPublishers;

import com.reactiveJavaProject.courseUtil.Util;
import com.reactiveJavaProject.sec08CombiningPublishers.helper.AmericanAirlines;
import com.reactiveJavaProject.sec08CombiningPublishers.helper.EmiratesFlights;
import com.reactiveJavaProject.sec08CombiningPublishers.helper.QatarAirways;
import reactor.core.publisher.Flux;

public class Lec03Merge {

    public static void main(String[] args) {

        /*with merge we can simulate the use of different Airlines booking a flight*/
        Flux
                .merge(QatarAirways.getFlights(), EmiratesFlights.getFlights(), AmericanAirlines.getFlights())
                .subscribe(Util.subscriber());

        Util.sleepSeconds(3);

    }
}
