package org.example;

//import org.example.DataValidatorComponent;
import org.example.baseClasses.DataValidatorComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class Test4DataValidatorComponent {
    public DataValidatorComponent dataValidatorComponent () {
        return new DataValidatorComponent();
    }
}
