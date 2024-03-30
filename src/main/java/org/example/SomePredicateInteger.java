package org.example;

import org.springframework.context.annotation.Bean;

import java.util.Date;

//@Component
public class SomePredicateInteger {


    Integer Fi;

    public SomePredicateInteger(Integer fi) {
        Fi = fi;
    }

    @Bean(name = "SomePredicateInteger1")
    public Date make() {
        return Fi;
    }
}
