package sample.Persons;

import sample.DataBases.DataBaseHandler;
import sample.Output.ReportHandler;
import sample.Statements.*;

import java.util.ArrayList;

public class Secretary extends User{

    private Statement statement;
    private DataBaseHandler dbHandler;
    private Curriculum curriculum;

    public Secretary(String login, String password, String firstname, String lastName) {
        super(login, password, firstname, lastName);
        status = "secretary";
        dbHandler = new DataBaseHandler();
        curriculum = new Curriculum();
        statement = new Statement();
    }

    public Secretary(){

        status = "secretary";
        dbHandler = new DataBaseHandler();
        curriculum = new Curriculum();
        statement = new Statement();

    }
    @Override
    public String getStatus() {
        return status;
    }

    public void addStudent(Student student){
        statement.addStudent(student);
        student.pushToDataBase();
    }

    private ArrayList<Student> getStatementForGroup(String group){
        ArrayList<Student> students = new ArrayList<>();
        for(Student st: statement.getWholeMarksList())
            if(st.getGroup().equals(group)){
                students.add(st);
            }

        return students;
    }

    public void setMarkByGroupAndLastName(String group, String lastName, String subject, int mark){
        for(Student st: getStatementForGroup(group))
            if(st.getLastName().equals(lastName)) {
                st.setMark(subject, mark);
                dbHandler.updateMarkInGroupTable(st);
                break;
            }



    }

    public void makeStatementForGroup(String group, String fileName){
        ArrayList<Student> groupList = getStatementForGroup(group);
        ReportHandler reportHandler;
        String[] row = new String[6];
        for(Student st: groupList){
            reportHandler = new ReportHandler(fileName);
            row[0] = st.getFirstName();
            row[1] = st.getLastName();
            row[2] = st.getGroup();
            row[3] = st.getCourse();
            row[4] = st.getAddress();
            row[5] = st.getAge()+"";
            reportHandler.writeRow(row);
        }


    }
}
