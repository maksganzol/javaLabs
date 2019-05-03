package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DataBases.DataBaseHandler;
import sample.Persons.Kurator;
import sample.Persons.Secretary;


public class SignUpPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginfield;

    @FXML
    private TextField namefield;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Button submitbutton;

    @FXML
    private TextField surnamefield;

    @FXML
    private Label welcomelabel;

    @FXML
    private RadioButton secretarybutton;

    @FXML
    private RadioButton kuratorbutton;

    private DataBaseHandler dbHandler;

    @FXML
    void initialize() {

        submitbutton.setOnAction(event->{
            dbHandler = new DataBaseHandler();
            String status;
            if(kuratorbutton.isSelected())
                dbHandler.signUpUser(new Kurator(loginfield.getText(), passwordfield.getText(),
                        namefield.getText(), surnamefield.getText()));
            else dbHandler.signUpUser(new Secretary(loginfield.getText(), passwordfield.getText(),
                        namefield.getText(), surnamefield.getText()));
            //Закрываем окно регистрации
            submitbutton.getScene().getWindow().hide();

        });


    }

}
