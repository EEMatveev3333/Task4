package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SomeStringClass2 {
    @Autowired String F1;

    public SomeStringClass2(String f1) {
        F1 = "Hellow world";
    }

    public SomeStringClass2() {
        F1 = "Hellow world";
    }


    @Bean(name = "StringBean2")
    public String make2() {
        return F1;
    }
}
