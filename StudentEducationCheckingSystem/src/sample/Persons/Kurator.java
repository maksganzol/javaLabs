package sample.Persons;

import java.util.ArrayList;

public class Kurator extends User{
    public Kurator(String login, String password, String firstName, String lastName) {
        super(login, password, firstName, lastName);
        status = "kurator";
    }

    @Override
    public String getStatus() {
        return status;
    }

    public void  makeReportAboutGroup(String fileName){
        new Secretary().makeStatementForGroup("AS-181", fileName);
    }
}
