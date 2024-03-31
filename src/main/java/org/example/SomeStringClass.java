package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SomeStringClass {
    @Autowired String F1 = "Hellow world";

    public SomeStringClass(String f1) {
        F1 = "Hellow world";
    }

    public SomeStringClass() {
        F1 = "Hellow world";
    }


    @Bean(name = "StringBean")
    public String make() {
        return F1;
    }
}
