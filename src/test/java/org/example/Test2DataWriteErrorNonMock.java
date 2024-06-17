package org.example;

import org.example.baseClasses.DatabaseWorks;
import org.example.baseClasses.LineFile;
import org.example.repository.LogErrorRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2DataWriteErrorNonMock {

    @Autowired
    private DatabaseWorks databaseWorks;

    @Test
    public void contextLoads(){

    }
    @Test
    public void testWriteErrorToDataBase ()
    {

        LineFile errorline1 = new LineFile();
        errorline1.setTextError("Error3");
        errorline1.setNamefile("Error3.txt");
        errorline1.setLine("Error3 line");

        LineFile errorline2 = new LineFile();
        errorline2.setTextError("Error4");
        errorline2.setNamefile("Error4.txt");
        errorline2.setLine("Error4 line");

        List <LineFile>  errorLines = new ArrayList<>();

        errorLines.add(errorline1);
        errorLines.add(errorline2);
        System.out.println("databaseWorks.getLogErrorCount() " + databaseWorks.getLogErrorCount());
        long oldRowCnt = databaseWorks.getLogErrorCount();
        databaseWorks.writeErrorToDateBase(errorLines);
        long newRowCnt = databaseWorks.getLogErrorCount();
        System.out.println("databaseWorks.getLogErrorCount() " + databaseWorks.getLogErrorCount());
        Assertions.assertEquals(oldRowCnt + 2, newRowCnt);

    }

}
