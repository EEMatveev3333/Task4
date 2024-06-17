package org.example.baseClasses;

//import org.example.Interface.DataValidationComponent;
//import org.example.Interface.DataValidator;
import org.example.baseClasses.LineFile;
import org.example.baseClasses.ValidRecord;
import org.example.baseInterfaces.DataValidationComponent;
import org.example.baseInterfaces.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//@Component
@Configuration
public class DataValidatorComponent  implements DataValidator<LineFile, ValidRecord>
 {
    @Autowired
    private List <DataValidationComponent<LineFile>> validators = new ArrayList<>();

    public List<ValidRecord> validRecords  = new ArrayList<>();

    public List<LineFile> errorLines = new ArrayList<>();

    public DataValidatorComponent(DataValidationComponent<LineFile>... validatorsArr) {
        this.validators = new ArrayList<>(Arrays.asList(validatorsArr));
    }

     public DataValidatorComponent() {
     }


     //private List<DataValidationComponent<LineFile>> validators;
    @Override
    public List<ValidRecord> validatordata  (List<LineFile> lines)  {
        List<ValidRecord> validRecords = new ArrayList<>();
        List<LineFile> errorLines = new ArrayList<>();

        System.out.println ("ВАЛИДАЦИЯ");
        for (LineFile line : lines)
        {
            boolean isvalid=true;
            for (DataValidationComponent <LineFile> validator : validators )
            {
                if (!validator.validate(line)) {
                    System.out.println("ЗАПИСЬ НЕ ВАЛИДНА для "+ line.getFio());
                    errorLines.add(line);
                    isvalid=false;
                    break;
                }
            }
            if (isvalid) {
                validRecords.add(new ValidRecord(line.getLogin(),line.getFio(), line.getAccess_stamp(),line.getApp()));
                     System.out.println("ЗАПИСЬ ВАЛИДНА для "+ line.getFio());
            }
        }
        this.validRecords = new ArrayList<>(validRecords);
        this.errorLines = new ArrayList<>(errorLines);
        return validRecords;
    }

     @Override
     public String toString() {
         return "DataValidatorComponent{" +
                 "validatorsCount=" + validators.size() +
                 "validators=" + validators +
                 '}';
     }


     public int validatorsSize() {
         return validators.size();
     }

 }

