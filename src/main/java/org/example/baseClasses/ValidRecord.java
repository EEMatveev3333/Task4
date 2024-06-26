package org.example.baseClasses;


import org.example.subtask2.LogTransformation;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@LogTransformation(LogFile = "ValidRecord.log")
public class ValidRecord {
    private String login;
    private String fio;
    private Timestamp accessDateTime;
    private String appType;

    public ValidRecord(String login, String fio, Timestamp accessDateTime, String appType) {
        this.login = login;
        this.fio = fio;
        this.accessDateTime = accessDateTime;
        this.appType = appType;
    }

    // Геттеры и сеттеры для полей

    public String getLogin() {
        return login;
    }



    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Timestamp getAccessDateTime() {
        return accessDateTime;
    }


    public String getAppType() {
        return appType;
    }

    @Override
    public String toString() {
        return "ValidRecord {" +
                "login='" + login + '\'' +
                ", fio='" + fio + '\'' +
                ", accessDateTime=" + accessDateTime +
                ", appType='" + appType + '\'' +
                '}';
    }
}
