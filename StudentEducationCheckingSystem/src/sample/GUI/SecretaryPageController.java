package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Persons.Kurator;
import sample.Persons.Secretary;


public class SecretaryPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addbutton;

    @FXML
    private Button setmarkbutton;

    @FXML
    private Label welcomelabel;


    void initData(Secretary sec) {
        welcomelabel.setText(sec.getFirstname() + " " + sec.getLastName());
        addbutton.setOnAction(event->{
            try {
                showAddStudentStage(sec);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        setmarkbutton.setOnAction(event->{
            try {
                showSetMarkStage(sec);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public Stage showAddStudentStage(Secretary sec) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddStudentPage.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene((Pane)loader.load()));
        AddStudentPageController controller = (AddStudentPageController)loader.getController();
        controller.initData(sec);
        stage.showAndWait();
        return stage;
    }

    public Stage showSetMarkStage(Secretary sec) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SetMarkPage.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene((Pane)loader.load()));
        SetMarkPageController controller = (SetMarkPageController)loader.getController();
        controller.initData(sec);
        stage.showAndWait();
        return stage;
    }


}
