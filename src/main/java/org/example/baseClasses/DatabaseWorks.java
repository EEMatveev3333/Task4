package org.example.baseClasses;

import org.example.baseClasses.LineFile;
import org.example.baseClasses.ValidRecord;
//import org.example.data.entity.LogErrors;
//import org.example.data.entity.Logins;
//import org.example.data.entity.Users;
//import org.example.data.inter.DatabaseWriter;
//import org.example.data.inter.LogErrorRepository;
//import org.example.data.inter.LoginRepository;
//import org.example.data.inter.UserRepository;
import org.example.baseInterfaces.DatabaseWriter;
import org.example.model.LogErrors;
import org.example.model.Logins;
import org.example.model.Users;
import org.example.repository.LogErrorRepository;
import org.example.repository.LoginRepository;
import org.example.repository.UserRepository;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Component
@PropertySource({"classpath:application.properties"})
public class DatabaseWorks implements DatabaseWriter<LineFile, ValidRecord> {
    @Autowired
    public final LogErrorRepository logErrorRepository;
    @Autowired
    public final UserRepository userRepository;
    @Autowired
    public final LoginRepository loginRepository;
    @Autowired
    public DataSource dataSource;

    //private PGSimpleDataSource dataSource;// = new PGSimpleDataSource();


    //.. для подключения

    @Value("${spring.datasource.url}")
    String url_;
    @Value("${spring.datasource.username}")
    String username_;
    @Value("${spring.datasource.password}")
    String password_;

    public long getLogErrorCount(){
        return logErrorRepository.count();
    }

    public long getUserCount(){
        return userRepository.count();
    }

    public boolean isUserExists(String strLogin){
        Users user = new Users();
        Optional<Users> optionuser = userRepository.findbyNameUser(strLogin);
        //if   (optionuser.isEmpty() ) // нет такого логина, добавляем
        return !optionuser.isEmpty();
    }

    public long getLoginCount(){
        return loginRepository.count();
    }
    @Autowired
    public DatabaseWorks (DataSource dataSource,
                          LogErrorRepository logErrorRepository,
                          UserRepository userRepository,
                          LoginRepository loginRepository
                          ) {

        System.out.println("public DatabaseWorks dataSource = " + dataSource);
        System.out.println("public DatabaseWorks logErrorRepository = " + logErrorRepository);
        System.out.println("public DatabaseWorks userRepository = " + userRepository);
        System.out.println("public DatabaseWorks loginRepository = " + loginRepository);
        //PGSimpleDataSource
        //this.dataSource=(PGSimpleDataSource)dataSource;
        this.dataSource=dataSource;
        this.logErrorRepository=logErrorRepository;
        this.userRepository=userRepository;
        this.loginRepository=loginRepository;

        this.url_ = "jdbc:postgresql://localhost:5432/postgres";
        this.username_ = "admin";
        this.password_ = "root";

        this.setDatabaseConnection(this.url_,this.username_,this.password_);
    }

//    public DatabaseWorks() {
//    }

    //spring сам берет это подключение   @value установим по умолчанию из application.properties
    public void  setDatabaseConnection (@Value("${spring.datasource.url}") String url,
                                        @Value("${spring.datasource.username}") String username,
                                        @Value("${spring.datasource.password}") String password
                                      )
    {
        System.out.println("url = " + url);
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        System.out.println("this.url_ = " + this.url_);
        System.out.println("this.username_ = " + this.username_);
        System.out.println("this.password_ = " + this.password_);

    }
    @Override
    @Transactional///запись ошибочных записей
    public void writeErrorToDateBase(List<LineFile> errorsLines) {
        System.out.println("writeErrorToDateBase");
        //logErrorRepository.deleteAll();   // Для простоты  все чистим  так как пускаем много раз
        /*
        for (LineFile errorLine : errorsLines) {
            if (errorLine.getTextError() != null) {
                System.out.println("пишем ошибки в  базу logerrors");
                LogErrors error = new LogErrors();
                error.setLine(errorLine.getLine());
                error.setError_text(errorLine.getTextError());
                error.setName_file(errorLine.getNameFile());
                savelogerror(error);
            }
        }
        */
        errorsLines.stream().filter(line ->line.getTextError()!= null)
                .forEach(errorLine->{
                    LogErrors error = new LogErrors();
                    error.setLine(errorLine.getLine());
                    error.setError_text(errorLine.getTextError());
                    error.setName_file(errorLine.getNameFile());
                    logErrorRepository.save(error);

                });
    }
    @Override  // запись правильных записей
    @Transactional
    public void writeToDateBase(List<ValidRecord> valids) {
        //loginRepository.deleteAll(); отладка
        //userRepository.deleteAll();
        for (ValidRecord validRecord : valids) {
            Users user = new Users();
            Optional<Users> optionuser = userRepository.findbyNameUser(validRecord.getLogin());
            if   (optionuser.isEmpty() ) { // нет такого логина
                user.setNameuser(validRecord.getLogin());
                user.setFio(validRecord.getFio());
                try {
                    userRepository.save(user);
                    //System.out.println(" Сохраняем пользователя и получаем его ID" + user.getId());
                }
                catch  (Exception e) {
                    // Обрабатываем ошибку
                    e.printStackTrace(); // Выводим информацию об ошибке в консоль
                }

            }
            else {
                user = optionuser.get();
                //   System.out.println(" не сохраняем пользователя берем так как такой пользователь есть в базе"+user.getId());
            }

            Logins login = new Logins();
            login.setAccessDate(validRecord.getAccessDateTime());

            // Устанавливаем ID пользователя в поле user_id записи входа
            login.setUser(user);  //внешний ключ
            if (validRecord.getAppType().equals("web") || validRecord.getAppType().equals("mobile") )
                login.setName_app(validRecord.getAppType());
            else
                login.setName_app("other:" + validRecord.getAppType());

            try {
                // Сохраняем запись входа (login)
                loginRepository.save(login);
            } catch (Exception e) {
                e.printStackTrace(); // Выводим информацию об ошибке в консоль
            }

        }

    }

}

