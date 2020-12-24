package controler.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    public TextField username;
    public PasswordField pass;
    public Button submit;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        submit.setOnAction(actionEvent -> {
            String name = username.getText();
            String passin = pass.getText();

            // check username and password
            if (!name.equals("admin") || !passin.equals("admin")) {
                Alert alert = new Alert(AlertType.WARNING, "Bạn nhap sai mat khau ", ButtonType.OK);
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {


//             tat man hinh hien tai de hien thi man hinh tao don
                try {
                    Parent blad = FXMLLoader.load(getClass().getClassLoader().getResource("view/home/home.fxml"));
                    Scene scene = new Scene(blad);
                    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    appStage.setScene(scene);
                    appStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
