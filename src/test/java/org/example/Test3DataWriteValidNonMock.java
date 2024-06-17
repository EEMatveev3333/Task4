package org.example;

import org.example.baseClasses.DatabaseWorks;
import org.example.baseClasses.ValidRecord;
import org.example.model.Users;
import org.example.repository.LogErrorRepository;
import org.example.repository.LoginRepository;
import org.example.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test3DataWriteValidNonMock {

    @Autowired
    private DatabaseWorks databaseWorks;

    @BeforeAll
    public static void init(){
        System.out.println("Before All init() method called");
     }

    @AfterAll
    public static void cleanUp(){
        System.out.println("After All cleanUp() method called");
    }

    @Test
    @DisplayName( " тест записи в базу логина который есть в базе ")
    public void testWriteValidToDataBaseExistUser ()
    {
        ValidRecord validRecord1=new ValidRecord("login1","fio1",new Timestamp(System.currentTimeMillis()), "mobile");
        List<ValidRecord> valids =new ArrayList<>();
        valids.add(validRecord1);

        Optional<Users>  existUser =Optional.of(new Users());
        long oldRowCnt = databaseWorks.getUserCount();
        databaseWorks.writeToDateBase(valids);
        long newRowCnt = databaseWorks.getUserCount();
        Assertions.assertEquals(oldRowCnt, newRowCnt);
    }
    @Test
    @DisplayName( " тест записи в базу нового логина которого нет в базе ")
    public void testWriteValidToDataBaseNewUser ()
    {

        ValidRecord validRecord2=new ValidRecord("login2","fio2",new Timestamp(System.currentTimeMillis()), "web");
        ValidRecord validRecord3=new ValidRecord("login3","fio3",new Timestamp(System.currentTimeMillis()), "unnamed");
        ValidRecord validRecord4=new ValidRecord("login4","fio4",new Timestamp(System.currentTimeMillis()), "mobile");

        List<ValidRecord> valids =new ArrayList<>();
        valids.add(validRecord2); //.add(validRecord2,validRecord3);
        valids.add(validRecord3);
        valids.add(validRecord4);

        System.out.println(validRecord2.getLogin());
        System.out.println(validRecord3.getLogin());
        System.out.println(validRecord4.getLogin());

        int addCnt = 0;
        //Users user = new Users();
        //Optional<Users>

        if   (!databaseWorks.isUserExists(validRecord2.getLogin())) addCnt++; // нет такого логина, добавляем
        if   (!databaseWorks.isUserExists(validRecord3.getLogin())) addCnt++; // нет такого логина, добавляем
        if   (!databaseWorks.isUserExists(validRecord4.getLogin())) addCnt++; // нет такого логина, добавляем


        long oldRowCnt = databaseWorks.getUserCount();
        databaseWorks.writeToDateBase(valids);
        long newRowCnt = databaseWorks.getUserCount();
        Assertions.assertEquals(oldRowCnt + addCnt, newRowCnt);
    }

    @Test
    @DisplayName( " тест записи в базу нового логина с пустой датой ")
    public void testWriteNonValidToDateBaseNewUser ()
    {

        ValidRecord validRecord2=new ValidRecord("login2","fio2",null, "web");
        ValidRecord validRecord3=new ValidRecord("login3","fio3",null, "unnamed");
        ValidRecord validRecord4=new ValidRecord("login4","fio4",null, "mobile");

        List<ValidRecord> valids =new ArrayList<>();
        valids.add(validRecord2); //.add(validRecord2,validRecord3);
        valids.add(validRecord3);
        valids.add(validRecord4);

        long oldRowCnt = databaseWorks.getUserCount();
        databaseWorks.writeToDateBase(valids);
        long newRowCnt = databaseWorks.getUserCount();
        Assertions.assertEquals(oldRowCnt, newRowCnt);
    }

}

