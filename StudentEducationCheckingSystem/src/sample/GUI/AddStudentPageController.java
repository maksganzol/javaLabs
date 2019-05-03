package sample.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.Persons.Secretary;
import sample.Persons.Student;


public class AddStudentPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addbutton;

    @FXML
    private TextField addressfield;

    @FXML
    private TextField agefield;

    @FXML
    private TextField coursefield;

    @FXML
    private TextField groupfield;

    @FXML
    private TextField namefield;

    @FXML
    private TextField surnamrfield;

    @FXML
    private Label welcomelabel;


    @FXML
    void initialize() {


    }

    public void initData(Secretary sec){
        addbutton.setOnAction((event->{
            sec.addStudent(new Student(namefield.getText(), surnamrfield.getText(), groupfield.getText(),
                    coursefield.getText(), addressfield.getText(), Integer.parseInt(agefield.getText())));
            addbutton.getScene().getWindow().hide();
        }));

    }

}
