package sample.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Output.ReportHandler;
import sample.Persons.Kurator;


public class KuratorPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label welcomelabel;

    @FXML
    private Button groupbutton;

    @FXML
    private Button studentbutton;

    @FXML
    void initialize() {



    }
    void initData(Kurator kur) {
        welcomelabel.setText(welcomelabel.getText() + " " + kur.getFirstname());
        studentbutton.setOnAction(enent->{
            kur.makeReportAboutGroup("Report1.csv");
        });
    }
}
