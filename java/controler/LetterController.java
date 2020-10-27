package controler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Applicant;
import model.Letter;
import service.LetterService;
import service.impl.LetterServiceIMPL;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LetterController implements Initializable {

    public TableView tableViewLetter;
    public TableColumn tableColumnSTT;
    public TableColumn tableColumnLoaiDon;
    public TableColumn tableColumnNgayVietDon;
    public TableColumn tableColumnNoiDung;
    public TableColumn tableColumnTrangThai;


    private LetterService letterService=new LetterServiceIMPL();;

    private ObservableList<Letter> lettersObservableList;

    private List<Letter> letters=new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableColumnSTT.setCellValueFactory(new PropertyValueFactory<Letter,String>("id"));
        tableColumnLoaiDon.setCellValueFactory(new PropertyValueFactory<Letter,String>("category"));
        tableColumnNgayVietDon.setCellValueFactory(new PropertyValueFactory<Letter,String>("applyDate"));
        tableColumnNoiDung.setCellValueFactory(new PropertyValueFactory<Letter,String>("content"));
        tableColumnTrangThai.setCellValueFactory(new PropertyValueFactory<Letter,String>("id"));

        try {
            refreshTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void showAll() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        List<Letter> listLetter = letterService.findAll();
        for (Letter letter: listLetter) {
            alert.setContentText(letter.toString());
        }
        alert.show();
    }

//    public void searchById() throws SQLException {
//        String str = text.getText();
//        int id = Integer.parseInt(str);
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        LetterModel letter =  letterService.findById(id);
//        alert.setContentText(letter.toString());
//        alert.show();
//    }

//    public void add() throws SQLException, ClassNotFoundException {
//        // lấy data từ các thông tin trên màn hình
//        String title = "abc";
//        String content = "ádfds";
//        String category = " sdfs";
////        Date applyDate =
//
//        LetterModel letter = new LetterModel();
//        letter.setTitle(title);
//        letter.setContent(content);
//        letter.setCategory(category);
//        letter.setApplyDate(null);
//        letter.setDeleted(false);
//
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        if(letterService.insert(letter)) alert.setContentText("thêm thành công");
//        else alert.setContentText("thất bại");
//        alert.show();
//    }

    public void update(){

    }

    public void delete(){

    }


    public void refreshTable() throws SQLException {
        letters.removeAll(letters);
        letters.addAll(letterService.findAll());
        lettersObservableList= FXCollections.observableList(letters);
        tableViewLetter.setItems(lettersObservableList);
    }


}
