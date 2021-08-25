package com.reactiveJavaProject.sec03FluxCreateGenerate;

import com.reactiveJavaProject.sec03FluxCreateGenerate.assignment.FileReaderService;

public class Lec09AssignmentDemo {

    public static void main(String[] args) {

        FileReaderService readerService = new FileReaderService();

        readerService.readLines("Lorem Ipsum", 4);

    }
}
