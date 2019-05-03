package sample.Statements;

import sample.DataBases.Const;
import sample.DataBases.DataBaseHandler;
import sample.Persons.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Statement {
    protected ArrayList<Student> wholeList;
    private DataBaseHandler dbHandler;

    public ArrayList<String> getListOfStudentsByGroup(String group) {
        ArrayList<String> names = new ArrayList<>();
        for(Student st: wholeList)
            if(st.getGroup().equals(group))
                names.add(st.getFirstName() + " " + st.getLastName());

        return names;
    }


    public ArrayList<Student> getWholeMarksList() {
        return wholeList;
    }

    public void addStudent(Student student){
        wholeList.add(student);
    }

    public Statement(){
        wholeList = new ArrayList<Student>();
        dbHandler = new DataBaseHandler();
        ResultSet resSet = dbHandler.getStudents();
        try {
            while(resSet.next()) {
                wholeList.add(new Student(resSet.getString(Const.STUDENTS_NAME), resSet.getString(Const.STUDENTS_LASTNAME),
                        resSet.getString(Const.STUDENTS_GROUP), resSet.getString(Const.STUDENTS_GROUP),
                        resSet.getString(Const.STUDENTS_ADDR), resSet.getInt(Const.STUDENTS_AGE)));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }


    }

    public Student getStudentByLastName(String lastName){
        for(Student student: wholeList){
            if(student.getLastName().equals(lastName))
                return student;
        }
        return null;
    }

}
