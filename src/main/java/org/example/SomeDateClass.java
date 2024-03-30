package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SomeDateClass {
    @Autowired private Date F1;

    public SomeDateClass(Date f1) {
        F1 =  new java.util.Date();//"Hellow world";
    }

    public SomeDateClass() {
        F1 =  new java.util.Date();//"Hellow world";
    }


    @Bean(name = "SomeDateClassBean1")
    public Date make() {
        return F1;
    }
}
