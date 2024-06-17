package org.example;

import org.example.baseClasses.*;
import org.example.ordered.AppTypeValidator;
import org.example.ordered.DateValidator;
import org.example.ordered.FioValidator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test5FullFunctional {

    private FioValidator fioValidator = new FioValidator();

    private DateValidator dateValidator = new DateValidator();

    private AppTypeValidator appTypeValidator = new AppTypeValidator();

    @Autowired
    private DataValidatorComponent validatorComponent = new DataValidatorComponent(fioValidator,dateValidator,appTypeValidator);

    @Autowired
    private DatabaseWorks databaseWorks;

    @Test
    public void testFull ()    {

        FileReaderComponent fileReaderComponent =new FileReaderComponent();
        String path1 =getClass().getResource("/Path1").getPath();
        String path2 =getClass().getResource("/Path2").getPath();
        List<String>paths=List.of(path1,path2 );
        List<LineFile>  lines =fileReaderComponent.readTextFiles(paths);
        assertEquals(12,lines.size()); // 6 строки в двух файлах
        System.out.println("lines="+lines.toString());
        System.out.println("----------------выполняем валидацию: " + lines.toString());

        List<ValidRecord> validRecords = new ArrayList<>();
        List<LineFile>  errorLines = new ArrayList<>();
        validatorComponent.validatordata(lines);

        System.out.println("//прошло валидацию " + validatorComponent.validRecords.toString());
        System.out.println("//не прошло валидацию " + validatorComponent.errorLines.toString());

        databaseWorks.writeErrorToDateBase(validatorComponent.errorLines);
        databaseWorks.writeToDateBase(validatorComponent.validRecords);

        Assertions.assertEquals(validatorComponent.validRecords.size(), 8);
        Assertions.assertEquals(validatorComponent.errorLines.size(), 4);

    }

}
