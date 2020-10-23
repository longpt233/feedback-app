package controler;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.LetterModel;
import service.LetterService;
import service.impl.LetterServiceIMPL;

import javax.swing.text.TabableView;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class LetterController {
    // có check các điều kiện khi lấy thông tin ra
    private LetterService letterService = new LetterServiceIMPL();

    @FXML
    private TextField text;

    @FXML
    private TabableView tabableView;

    public void showAll() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        List<LetterModel> listLetter = letterService.findAll();
        for (LetterModel letter: listLetter) {
            alert.setContentText(letter.toString());
        }
        alert.show();
    }

    public void searchById() throws SQLException {
        String str = text.getText();
        int id = Integer.parseInt(str);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        LetterModel letter =  letterService.findById(id);
        alert.setContentText(letter.toString());
        alert.show();
    }

    public void add() throws SQLException, ClassNotFoundException {
        // lấy data từ các thông tin trên màn hình
        String title = "abc";
        String content = "ádfds";
        String category = " sdfs";
//        Date applyDate =

        LetterModel letter = new LetterModel();
        letter.setTitle(title);
        letter.setContent(content);
        letter.setCategory(category);
        letter.setApplyDate(null);
        letter.setDeleted(false);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(letterService.insert(letter)) alert.setContentText("thêm thành công");
        else alert.setContentText("thất bại");
        alert.show();
    }

    public void update(){

    }

    public void delete(){

    }
}
