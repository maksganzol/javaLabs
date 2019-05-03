package sample.DataBases;

import sample.Persons.Kurator;
import sample.Persons.Secretary;
import sample.Persons.Student;
import sample.Persons.User;
import sample.Statements.Curriculum;
import sample.Statements.Statement;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DataBaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, user, password);

        return dbConnection;
    }

    public boolean signUpUser(User user){

        String insert = "INSERT INTO " + Const.USERS_TABLE + "("
                + Const.USERS_NAME + "," + Const.USERS_LASTNAME + ","
                + Const.USERS_LOGIN + "," + Const.USERS_PASSWORD + "," + Const.USER_STATUS +") VALUES(?,?,?,?,?)";

        if(isLoginExist(user.getLogin()))
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, user.getFirstname());
            prSt.setString(2, user.getLastName());
            prSt.setString(3, user.getLogin());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getStatus());
            prSt.executeUpdate();

            System.out.println("User successfully added!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        else System.out.println("Login is already exist.");
        return false;

    }

    /**
     * Проверяет есть ли login в базе данных
     * @param login
     * @return true, если нет
     */
    private boolean isLoginExist(String login){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_LOGIN + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, login);
            resSet = prSt.executeQuery();
            return !resSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean signUpStudent(Student student){
        String insert = "INSERT INTO " + Const.STUDENTS_TABLE + "("
                + Const.STUDENTS_NAME + "," + Const.STUDENTS_LASTNAME + ","
                + Const.STUDENTS_GROUP + "," + Const.STUDENTS_COURSE + "," + Const.STUDENTS_ADDR + "," + Const.STUDENTS_AGE +") VALUES(?,?,?,?,?,?)";

            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(insert);

                prSt.setString(1, student.getFirstName());
                prSt.setString(2, student.getLastName());
                prSt.setString(3, student.getGroup());
                prSt.setString(4, student.getCourse());
                prSt.setString(5, student.getAddress());
                prSt.setInt(6, student.getAge());

                prSt.executeUpdate();

                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return false;
    }

    public boolean addGroup(String group){
        String insert = "INSERT INTO " + Const.CUR_TABLE + "(" + Const.CUR_GROUP + ")" + "VALUES(?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, group);
            prSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addSubjectToGroup(String subject, String group) {
        String insert = "UPDATE " + Const.CUR_TABLE + " SET " + subject + "=" + "1 WHERE " + Const.CUR_GROUP + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, group);
            prSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    public User signInUser(String login, String password) {
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_LOGIN + "=? AND " + Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, login);
            prSt.setString(2, password);

            resSet = prSt.executeQuery();
            if(resSet.next())
            if(resSet.getString(Const.USER_STATUS).equals("kurator"))
                return new Kurator(resSet.getString(Const.USERS_LOGIN),
                        resSet.getString(Const.USERS_PASSWORD),
                        resSet.getString(Const.USERS_NAME),
                        resSet.getString(Const.USERS_LASTNAME));
            else return new Secretary(resSet.getString(Const.USERS_LOGIN),
                    resSet.getString(Const.USERS_PASSWORD),
                    resSet.getString(Const.USERS_NAME),
                    resSet.getString(Const.USERS_LASTNAME));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }

    public ResultSet getStudents(){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.STUDENTS_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            resSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public ResultSet getCurriculum(){
        ResultSet resSet = null;
        String select = "SELECT * FROM " + Const.CUR_TABLE;

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            resSet = prSt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

    public void createGroupTable(String gr){

        String group = gr.replaceAll("-", "");
        String create = "CREATE TABLE IF NOT EXISTS " + group + " (id INT(5) NOT NULL AUTO_INCREMENT," + Const.GROUP_NAME + " VARCHAR(50) NOT NULL";
        ArrayList<String> subjects = Curriculum.getSubjectsForGroup(gr);
        for(String subject: subjects)
            create += ", " + subject + " INT(5)";
        create += ", PRIMARY KEY (`id`))";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(create);
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String insert = "INSERT INTO " + group + "(" + Const.GROUP_NAME + ") VALUES (?)";
        ArrayList<String> stNames = new Statement().getListOfStudentsByGroup(gr);
        for(String name: stNames){
            PreparedStatement prSt = null;
            try {
                if(!isStudentInGrouptable(getDbConnection(), group, name)) {
                    prSt = getDbConnection().prepareStatement(insert);
                    prSt.setString(1, name);
                    prSt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public boolean isStudentInGrouptable(Connection con, String tableName, String name){
        try {
            PreparedStatement prSt = con.prepareStatement("SELECT * FROM " + tableName);
            ResultSet resSet = prSt.executeQuery();
            while(resSet.next()){
                if(resSet.getString(Const.GROUP_NAME).equals(name))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
