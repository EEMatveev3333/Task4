package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
//@EnableTransactionManagement(proxyTargetClass = true)
//@EnableAutoConfiguration
@Configuration
//@EnableTransactionManagement
public class Main {

    public static void main(String[] args) throws java.lang.InterruptedException {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println("Normal start!");

        ApplicationContext context =
                new AnnotationConfigApplicationContext("org.example");

        System.out.println("Normal finish!");
    }
}