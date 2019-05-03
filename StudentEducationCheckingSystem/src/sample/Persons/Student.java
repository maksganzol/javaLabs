package sample.Persons;

import sample.DataBases.DataBaseHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class Student{
    private DataBaseHandler dbHandler;
    private String firstName, lastName, group, course, address;
    private int age;
    private SubjectsCard subjectCard;
    //private HashMap<String, Integer[]> subjectsList;
    public Student(String firstName, String lastName, String group, String course, String address, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
        this.course = course;
        this.address = address;
        this.age = age;
        subjectCard = new SubjectsCard();

        dbHandler = new DataBaseHandler();
    }

    public Student() {
        subjectCard = new SubjectsCard();
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
        public SubjectsCard(){
            card = new HashMap<>();
            card.put("Math", null);
            card.put("OOP", null);
            card.put("English", null);
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
