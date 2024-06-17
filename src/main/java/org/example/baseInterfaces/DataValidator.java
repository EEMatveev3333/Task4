package org.example.baseInterfaces;

import java.util.List;

public interface DataValidator <T,R> {
    List <R>  validatordata ( List<T> lines);
}
