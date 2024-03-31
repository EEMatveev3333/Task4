package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Start!");

        // 1 task
        String Str = new AnnotationConfigApplicationContext("org.example")
                .getBean("StringBean",SomeStringClass.class)
                .make();
        System.out.println("Str - " + Str);

        // 2 task
        Date Date_ = new java.util.Date();

        //new AnnotationConfigApplicationContext("org.example")
                //.getBean("SomeDateClassBean1",SomeDateClass.class)
                //.make();
        System.out.println("Date_ - " + Date_);

        // 3 task
        Integer Integer_;//_ = new java.util.Date();

        Integer_ = new AnnotationConfigApplicationContext("org.example")
        .getBean("SomePredicateInteger1",SomePredicateInteger.class)
        .make();
        System.out.println("Integer_ - " + Integer_);

        System.out.println("Finish!");


    }
}