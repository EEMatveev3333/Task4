package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("SomeDateClassBean")
public class SomeDateClass {


    //@Autowired
    private Date F1;

    public Date getF1() {
        if (F1 == null) F1 = new java.util.Date();
        return F1;
    }

    public void setF1(Date f1) {
        if (F1 == null) f1 = new java.util.Date();
    }

    public SomeDateClass(Date f1) {
        F1 = null;//"Hellow world";
    }

    public SomeDateClass() {
        F1 = null;//"Hellow world";
    }



//    @Bean(name = "SomeDateClassBean1")
//    public Date make() {
//        return F1;
//    }
}
