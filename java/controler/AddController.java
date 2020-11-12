package controler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Letter;
import service.impl.LetterServiceIMPL;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    @FXML
    private TextField organizationName;

    @FXML
    private Button addLetter;

    @FXML
    private TextField letterID, applicantID, applicantName, address, title;

    @FXML
    private TextArea content;

    @FXML
    private DatePicker applyDate;

    @FXML
    private ComboBox<String> category;


    private int statusLetter=0;
    ObservableList<String> list = FXCollections.observableArrayList("the loai1", "Đơn loại 2", "Đơn loại 3");


    public void initialize(URL url, ResourceBundle resourceBundle) {

        category.setItems(list);
        addLetter.setOnAction(actionEvent -> {
            try {
                if (letterID.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Thiếu mã đơn!");
                    alert.showAndWait();
                } else{
                    int ID=Integer.valueOf(letterID.getText());
                    String tit=title.getText();
                    String ct= content.getText();
                    int aID= Integer.valueOf(applicantID.getText());
                    Date date= Date.valueOf(applyDate.getValue());
                    String cate= category.getValue();


                    Letter newLetter = new Letter(ID, tit,ct, aID, date, false, cate, statusLetter);
                    LetterServiceIMPL letterServiceIMPL=new LetterServiceIMPL();
                    System.out.println("them thanh cong? "+letterServiceIMPL.insert(newLetter));

                }

            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Thiếu Thông Tin");
                alert.setContentText("Thiếu Thông Tin");
                alert.showAndWait();

                e.printStackTrace();
            }

        });


    }




}
