package controler;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import service.impl.LetterServiceIMPL;

import java.sql.SQLException;

public class ShowControler {
    @FXML
    private TextField text;

    public void findById() throws SQLException {
        String str=text.getText();
        int id= Integer.parseInt(str);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        System.out.println(new LetterServiceIMPL().findById(id));
        alert.setContentText(new LetterServiceIMPL().findById(id).toString());
        alert.show();
    }
}
