package org.example.ordered;

//import org.example.Interface.DataValidationComponent;
//import org.example.LogTransformation.LogTransformation;
import org.example.baseClasses.LineFile;
import org.example.baseInterfaces.DataValidationComponent;
import org.example.subtask2.LogTransformation;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
@LogTransformation(LogFile = "ValidatorAPP.log")
@Component
public class AppTypeValidator implements Ordered, DataValidationComponent<LineFile> {
    @Override
    public int getOrder() {
        return 2;
    }
    @Override
    public boolean validate(LineFile line) {
        String apptype = line.getApp();
        if (apptype ==null) {
            line.setTextError("Тип приложения не задан");
            return false;
        }
        apptype=apptype.trim().toLowerCase();
        if ((apptype.equals("web")) || (apptype.equals("mobile"))) {
            line.setApp(apptype);
        }
        else {
            line.setApp("other: " + apptype);
        }
        return true;
    }
}
