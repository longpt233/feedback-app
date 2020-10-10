package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;



public class TestController {
    @FXML
    private TextField input;

    public void show(ActionEvent event){
        String user=input.getText();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(user);
        alert.show();
    }
}
