package controler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button submit;

    public void initialize(URL location, ResourceBundle resources) {
        submit.setOnAction(actionEvent -> {
            try{
                Stage preStage = (Stage) submit.getScene().getWindow();
                preStage.close();

                Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("scene/app.fxml"));
                Scene app = new Scene(root);
                Stage stage = new Stage();
                //code lấy tt đăng nhập set cho trang mới
                stage.setTitle("Quản Lí Đơn");
                stage.setScene(app);
                stage.show();

                preStage.close();
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
