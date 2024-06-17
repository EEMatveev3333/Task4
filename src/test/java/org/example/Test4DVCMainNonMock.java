package org.example;

import org.example.baseClasses.DataValidatorComponent;
import org.example.baseClasses.LineFile;
import org.example.baseClasses.ValidRecord;
import org.example.ordered.AppTypeValidator;
import org.example.ordered.DateValidator;
import org.example.ordered.FioValidator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = Test4DataValidatorComponent.class)
public class Test4DVCMainNonMock {

    private FioValidator fioValidator = new FioValidator();

    private DateValidator dateValidator = new DateValidator();

    private AppTypeValidator appTypeValidator = new AppTypeValidator();

    @Autowired
    private DataValidatorComponent validatorComponent = new DataValidatorComponent(fioValidator,dateValidator,appTypeValidator); ;

    @Test
    public void testOrders ()    {
        Assertions.assertEquals(dateValidator.getOrder(), 1);
        Assertions.assertEquals(appTypeValidator.getOrder(), 2);
        Assertions.assertEquals(fioValidator.getOrder(), 3);
    }

    @Test
    public void testValidatorComponentSize ()    {
        System.out.println(validatorComponent.toString());
        Assertions.assertEquals(validatorComponent.validatorsSize(), 3);
    }

    @Test
    public void testDataValidator ()    {

        //Записи без ошибок
       LineFile line1 =new LineFile("logintest", "иванов иван иванович",
               "2024-03-30:12:10:10", "web", "filetest.txt",null,null );

       LineFile line2 =new LineFile("logintest", "петров петр петрович",
                "2024-03-30:12:10:10", "mobile", "filetest.txt",null,null );

        LineFile line3 =new LineFile("logintest", "сидоров сидр сидорович",
                "2024-03-30:12:10:10", "app", "filetest.txt",null,null );

        //Запись с ошибкой в дате
        LineFile line4 =new LineFile("logintest", "ошибка ошибк ошибковна дата",
                "2024-03-45:12:10:10", "app", "filetest.txt",null,null );

        LineFile line5 =new LineFile("logintest", "ошибка2 ошибк2 ошибковна2 нул дата",
                "", "app", "filetest.txt",null,null );

       List<LineFile> lines = Arrays.asList(line1, line2, line3, line4, line5);

       //Добавляем три корректных и три некорректных записи

        System.out.println("----------------выполняем валидацию: " + lines.toString());
        List<ValidRecord> validRecords =validatorComponent.validatordata(lines);
        System.out.println("//проверяем факт выполнения валидации " + validRecords.toString());

        Assertions.assertEquals(validRecords.size(), 3);

    }

}
