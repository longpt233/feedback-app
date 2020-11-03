package controler;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Letter;

import java.net.URL;
import java.util.ResourceBundle;

public class InforLetterControler implements Initializable {

    public TextArea textFieldToString;
    private Letter letter;

    public void setLetter(Letter letter){
        System.out.println("call set");
        this.letter=letter;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hello"+letter.toString());
        textFieldToString.setText(letter.toString());
    }
}
