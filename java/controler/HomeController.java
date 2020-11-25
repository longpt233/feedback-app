package controler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public Button bntQuanli;
    public BorderPane borderPane;
    public Button bntThongke;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Pane login = FXMLLoader.load(getClass().getResource("/view/home/hello.fxml"));
            borderPane.setCenter(login);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bntQuanli.setOnAction(actionEvent -> {
            try {
                Pane quanLiPane = FXMLLoader.load(getClass().getResource("/view/home/quanli/quanli.fxml"));
                borderPane.setCenter(quanLiPane);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
