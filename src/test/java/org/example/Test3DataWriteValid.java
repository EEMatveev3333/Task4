package org.example;

import org.example.baseClasses.ValidRecord;
import org.example.baseClasses.DatabaseWorks;
import org.example.repository.LogErrorRepository;
import org.example.repository.LoginRepository;
import org.example.repository.UserRepository;
import org.example.model.Users;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class Test3DataWriteValid {

    //@Mock
    @Spy
    private LoginRepository loginRepository;

    //@Mock
    @Spy
    private UserRepository userRepository;

    @Spy
    private PGSimpleDataSource dataSource;

    @Spy
    private LogErrorRepository logErrorRepository;

    //@InjectMocks
    private DatabaseWorks databaseWorks = new DatabaseWorks(dataSource,logErrorRepository,userRepository,loginRepository);

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
        databaseWorks = new DatabaseWorks(dataSource,logErrorRepository,userRepository,loginRepository);
        ValidRecord validRecord1=new ValidRecord("login1","fio1",new Timestamp(System.currentTimeMillis()), "app");
        List<ValidRecord> valids =new ArrayList<>();
        valids.add(validRecord1);
        //симулируем поведение припоиске по Login1 пользователя находим  в базе
        // при поиске по Login2 пользователь нет в базе
        Optional<Users>  existUser =Optional.of(new Users());
        Mockito.when (userRepository.findbyNameUser("login1")).thenReturn(existUser);
        //databaseWorks.setDatabaseConnection();
        databaseWorks.writeToDateBase(valids);
        Mockito.verify(loginRepository,Mockito.times(1)).save(Mockito.any()); // если польхователь есть только ыфму для logins
        Mockito.verify(userRepository,Mockito.times(0)).save(Mockito.any());  // не вызывается если пользователь есть
    }
    @Test
    @DisplayName( " тест записи в базу нового логина которого нет в базе ")
    public void testWriteValidToDataBaseNewUser ()
    {
        databaseWorks = new DatabaseWorks(dataSource,logErrorRepository,userRepository,loginRepository);
        ValidRecord validRecord2=new ValidRecord("login2","fio2",new Timestamp(System.currentTimeMillis()), "web");
        List<ValidRecord> valids =new ArrayList<>();
        valids.add(validRecord2);
        Mockito.when (userRepository.findbyNameUser("login2")).thenReturn(Optional.empty());
        databaseWorks.writeToDateBase(valids);
        Mockito.verify(loginRepository,Mockito.times(1)).save(Mockito.any()); // если польхователь есть только ыфму для logins
        Mockito.verify(userRepository,Mockito.times(1)).save(Mockito.any());  // не вызывается если пользователь есть
    }

}

