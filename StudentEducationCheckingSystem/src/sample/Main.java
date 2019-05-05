package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DataBases.DataBaseHandler;
import sample.Persons.Secretary;
import sample.Persons.Student;
import sample.Statements.Curriculum;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/LoginPage.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

//TODO доавить в отчет куратору оценки
    public static void main(String[] args) {
        launch(args);
    }
}
