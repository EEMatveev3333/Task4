package org.example.baseInterfaces;

import org.springframework.stereotype.Component;

@Component
public interface DataValidationComponent <T>
{
       boolean validate (T data);
}
