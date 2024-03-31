package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Start!");

        ApplicationContext context =
                new AnnotationConfigApplicationContext("org.example");

        // 1 task
        String Str = context
                .getBean("StringBean",SomeStringClass.class)
                .make();
        System.out.println("Str - " + Str);

        String Str2 = context
                .getBean("StringBean2",SomeStringClass2.class)
                .make2();
        System.out.println("Str2 - " + Str2);
        // 2 task
        Date Date_ = new java.util.Date();

        //new AnnotationConfigApplicationContext("org.example")
        //Date_ =
        SomeStringClass2 someStringClass2  = context.getBean("SomeStringClass2",SomeStringClass2.class);
                //.make();
        System.out.println("someStringClass2 - " + someStringClass2.make2());

        // 3 task
        Integer Integer_;//_ = new java.util.Date();

        Integer_ = 0;//new AnnotationConfigApplicationContext("org.example")
        //.getBean("SomePredicateInteger1",SomePredicateInteger.class)
        //.make();
        System.out.println("Integer_ - " + Integer_);

        System.out.println("Finish!");


    }
}