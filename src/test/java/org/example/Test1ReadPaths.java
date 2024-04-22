package org.example;

import org.example.baseClasses.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class Test1ReadPaths {

@Test
@DisplayName("Тестируем работу ScannerReader")
public void testFileReader ()
{
    ScannerReader scannerReader = Mockito.mock(ScannerReader.class);
    String path1 =getClass().getResource("/Path1").getPath();
    String path2 =getClass().getResource("/Path2").getPath();
    String input =path1 +"\n" +path2 +"\nВСЕ\n";
    System.out.println("input="+input);
    InputStream inputStream  =new ByteArrayInputStream(input.getBytes() );
    System.setIn(inputStream);
    List<String> expectedpath =List.of(path1,path2 );
    when (scannerReader.filereader()).thenCallRealMethod(); // вызываем реальный метод
    List<String> actualpaths =scannerReader.filereader();
    assertEquals(expectedpath,actualpaths);
}

    @Test
    @DisplayName("Тестируем работу FileReaderComponent")
    public void testFileReade ()
    {
        FileReaderComponent fileReaderComponent =new FileReaderComponent();
        String path1 =getClass().getResource("/Path1").getPath();
        String path2 =getClass().getResource("/Path2").getPath();
        List<String>paths=List.of(path1,path2 );
        List<LineFile>  lines =fileReaderComponent.readTextFiles(paths);
        assertEquals(6,lines.size()); // 6 строки в двух файлах
        System.out.println("lines="+lines.toString());
    }
    @Test
    @DisplayName("Тестируем работу Порядок выполнения валидаторов ")
    public void testValidatorOrder ()
    {
        DataValidatorComponent valid1 =Mockito.mock(DataValidatorComponent.class);
        DataValidatorComponent valid2 =Mockito.mock(DataValidatorComponent.class);
        DataValidatorComponent valid3 =Mockito.mock(DataValidatorComponent.class);
        DataValidatorComponent dataValidatorComponent =new DataValidatorComponent();
        DataValidatorComponent valid1spy =Mockito.spy(DataValidatorComponent.class);
        DataValidatorComponent valid2spy =Mockito.spy(DataValidatorComponent.class);
        DataValidatorComponent valid3spy =Mockito.spy(DataValidatorComponent.class);
    }
    @Test
    @DisplayName("Контролим фио")
    public void testValidatorFio() {
        FioValidator validator =new FioValidator();
        LineFile line =new LineFile("logintest", "сидр сидор сидорович",
                "2024-03-30:12:10:10", "app", "filetest.txt",null,null );
        boolean result =validator.validate(line);
        assertEquals(true,result);
        String expectedFio ="Сидр Сидор Сидорович";
        assertEquals(expectedFio,line.getFio());
    }

    @Test
    @DisplayName("Контроль валидатора AppType")
    public void testValidatorAppType() {
        AppTypeValidator validator =new AppTypeValidator();
        LineFile line =new LineFile("logintest", "сидр сидор сидорович",
                "2024-03-30:12:10:10", "app", "filetest.txt",null,null );
        boolean result =validator.validate(line);
        assertEquals(true,result);
        String expectedFio ="other: app";
        assertEquals(expectedFio,line.getApp());
    }
    @Test
    @DisplayName("Контроль валидатора Дата")
    public void testValidatorDate() {

        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2024-03-30:12:10:10",formatter);
        Timestamp expectedStamp = Timestamp.valueOf(dateTime);

        DateValidator validator =new DateValidator();
        LineFile line =new LineFile("logintest", "сидр сидор сидорович",
                "2024-03-30:12:10:10", "app", "filetest.txt",null,null );
        boolean result =validator.validate(line);

        assertEquals(true,result);
        assertEquals(expectedStamp,line.getAccess_stamp());
    }
    @Test
    @DisplayName("Контроль ошибки на дате")

    public void testValidatorDateError() {

        DateValidator validator =new DateValidator();
        LineFile line =new LineFile("logintest", "сидр сидор сидорович",
                "2024-40-30:12:10:10", "app", "filetest.txt",null,null );
        boolean result =validator.validate(line);

        assertEquals(false,result);
        assertEquals(null,line.getAccess_stamp());
        assertNotNull(line.getTextError());
    }



}

