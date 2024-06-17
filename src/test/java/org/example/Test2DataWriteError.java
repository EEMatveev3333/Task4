package org.example;

import org.example.baseClasses.LineFile;
import org.example.baseClasses.DatabaseWorks;
import org.example.repository.LogErrorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
public class Test2DataWriteError {

    @Spy
    private LogErrorRepository logErrorRepository;
    @InjectMocks
    private DatabaseWorks databaseWorks;

    @Test
    public void testWriteErrorToDataBase ()
    {
        LineFile errorline1 = Mockito.spy(LineFile.class);
        errorline1.setTextError("Error1");
        Mockito.when(errorline1.getTextError()).thenReturn("Error1");

        LineFile errorline2 = Mockito.spy(LineFile.class);
        errorline2.setTextError("Error2");
        Mockito.when(errorline2.getTextError()).thenReturn("Error2");

        List <LineFile>  errorLines = new ArrayList<>();
        errorLines.add(errorline1);
        errorLines.add(errorline2);
        databaseWorks.writeErrorToDateBase(errorLines);
        // проверяем что метод save вызван в LogErrorRepository для каждой ошибочной записи
        Mockito.verify(logErrorRepository,Mockito.times(errorLines.size())).save(Mockito.any());
    }

}
