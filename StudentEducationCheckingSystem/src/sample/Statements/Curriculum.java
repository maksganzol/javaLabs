package sample.Statements;

import sample.DataBases.Const;
import sample.DataBases.DataBaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Учебный ппан
 * Список диисциплин. В каждой дисциплине указано, в каких группах она присутствует
 */
public class Curriculum {
    private DataBaseHandler dbHandler;
    private ArrayList<Subject> subjects;

    public Curriculum(){

        dbHandler = new DataBaseHandler();
        ResultSet resSet = dbHandler.getCurriculum();
        int columnNum = 3;
        try {
            parseFromDB(resSet);
        }catch(SQLException ex){
            ex.printStackTrace();
        }

    }

    /**
     * Это метод находит группы, а которых преподаеcя каждый предмет из списка и записывает их в список групп этого предмета
     * @param resSet
     */

    public void parseFromDB(ResultSet resSet) throws SQLException {
        subjects = new ArrayList<>();
        ArrayList<String> groupSet = new ArrayList<>();
        int columnNum = 3; //columnNum - номер столбца, который представляет данный предмет
        for(int i = 0; i < resSet.getMetaData().getColumnCount() - 2; i++) { //Цикл выполняется столькот раз, сколько предметов в таблице
            while (resSet.next()) {
                if (resSet.getBoolean(columnNum)) //Если данный предмет есть у данной группы
                    groupSet.add(resSet.getString(Const.CUR_GROUP)); //Добавляем в список групп
                }
            subjects.add(new Subject(resSet.getMetaData().getColumnName(columnNum), groupSet)); //добавляем предмет
            groupSet = new ArrayList<>(); //Обнуляем список групп
            resSet.beforeFirst (); //Перемещает указатель ResultSet в начало
            columnNum++; //Увеличиваем номер столбца с предметом
        }
    }


    public void addSubject(String sub){
        subjects.add(new Subject(sub));
    }


    private class Subject{
        private String name;
        private ArrayList<String> groups;
        public Subject(String name, ArrayList<String> groups){
            this.name = name;
            this.groups = groups;
        }
        public Subject(String name){
            this.name = name;
        }
        public void addGroup(String gr){
            groups.add(gr);
        }
        public boolean hasGroup(String group){
            for(String gr: groups)
                if(gr.equals(group))
                    return true;
            return false;
        }
    }

    public String toString() {
        String str = "";
        for (Subject s : subjects) {
            str += s.name + " ";
            for (String g : s.groups)
                str += g + " ";
            str += "\n";
        }
        return str;
    }
    public  void addSubjectpToGrop(String subject, String group){
        for(Subject s: subjects){
            if(subject.equals(s.name)){
                s.addGroup(group);
                dbHandler.addSubjectToGroup(subject, group);
            }
        }
        ResultSet resSet = dbHandler.getCurriculum();
        try {
            parseFromDB(resSet);
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    public void addNewGroup(String group){
        dbHandler.addGroup(group);
    }
}
