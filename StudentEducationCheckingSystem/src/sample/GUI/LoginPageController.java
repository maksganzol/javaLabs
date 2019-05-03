package sample.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.DataBases.DataBaseHandler;
import sample.Persons.Kurator;
import sample.Persons.Secretary;
import sample.Persons.User;

public class LoginPageController {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button loginbutton;
    @FXML
    private TextField loginfield;
    @FXML
    private PasswordField passwordfield;
    @FXML
    private Button signupbutton;

    public LoginPageController() {
    }

    @FXML
    void initialize() {
        this.loginbutton.setOnAction((event) -> {
            User user = null;
            Kurator kurator = null;
            Secretary secretary = null;
            if (!this.loginfield.getText().equals("") && !this.passwordfield.getText().equals("")) {
                DataBaseHandler dbHandler = new DataBaseHandler();
                user = dbHandler.signInUser(this.loginfield.getText(), this.passwordfield.getText());
                if (user instanceof Kurator) {
                    kurator = (Kurator)user;
                } else {
                    secretary = (Secretary)user;
                }
            }

            try {
                if (user != null) {
                    if(user instanceof Kurator) {
                        this.loginbutton.getScene().getWindow().hide();
                        this.showKuratorStage(kurator);
                    }else {
                        this.loginbutton.getScene().getWindow().hide();
                        this.showSecretaryStage(secretary);
                    }
                } else {
                    System.out.println("((((Something is not correct");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        this.signupbutton.setOnAction((event) -> {
            //this.signupbutton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("SignUpPage.fxml"));

            try {
                loader.load();
            } catch (IOException var5) {
                var5.printStackTrace();
            }

            Parent root = (Parent)loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    public Stage showKuratorStage(Kurator kur) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("KuratorPage.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene((Pane)loader.load()));
        KuratorPageController controller = (KuratorPageController)loader.getController();
        controller.initData(kur);
        stage.showAndWait();
        return stage;
    }

    public Stage showSecretaryStage(Secretary sec) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SecretaryPage.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene((Pane)loader.load()));
        SecretaryPageController controller = (SecretaryPageController)loader.getController();
        controller.initData(sec);
        stage.showAndWait();
        return stage;
    }
}
