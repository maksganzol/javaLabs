package sample.Persons;

import sample.DataBases.Const;
import sample.DataBases.DataBaseHandler;
import sample.Statements.Curriculum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Student{
    private DataBaseHandler dbHandler;
    private ResultSet resSet;
    private String firstName, lastName, group, course, address;
    private int age;
    private SubjectsCard subjectCard;
    public Student(String firstName, String lastName, String group, String course, String address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.course = course;
        this.address = address;
        this.age = age;
        subjectCard = new SubjectsCard();
        dbHandler = new DataBaseHandler();
        parseFromDB();
    }

    public Student() {
        subjectCard = new SubjectsCard();
        dbHandler = new DataBaseHandler();
        parseFromDB();
    }

    //public void addSubject
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public void parseFromDB(){
        ArrayList<String> subjects = Curriculum.getSubjectsForGroup(group);
        resSet = dbHandler.getGroup(group);
        try {
            while (resSet.next())
                if(resSet.getString(Const.GROUP_NAME).equals(firstName + " " + lastName)) {
                    for (String sub : subjects )
                        setMark(sub, resSet.getInt(subjects.indexOf(sub)+3));
                }

        }catch(SQLException ex){
            ex.printStackTrace();
        }


    }
    public String getCourse() {
        return course;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private class SubjectsCard{
        private HashMap<String, Integer> card;
        private ArrayList<String> subjectList;
        public SubjectsCard(){
            card = new HashMap<>();
            subjectList = Curriculum.getSubjectsForGroup(group);
            for(String sub: subjectList)
                card.put(sub, null);
        }

    }

    public void setMark(String sub, int mark){
        subjectCard.card.put(sub, new Integer(mark));
    }

    public HashMap<String, Integer> getSubjectsCard(){
        return subjectCard.card;
    }

    public void pushToDataBase(){
        dbHandler.signUpStudent(this);
    }


}
