package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SomePredicateInteger {


    @Autowired
    private Integer Fi;

    public SomePredicateInteger(Integer fi) {
        Fi = fi;
    }
    public SomePredicateInteger() {
        Fi = 0;
    }

    @Bean(name = "SomePredicateInteger1")
    public Integer make() {
        return Fi;
    }
}
