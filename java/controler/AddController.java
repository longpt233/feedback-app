package controler;

import com.sun.source.tree.WhileLoopTree;
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
import model.Applicant;
import model.Letter;
import service.ApplicantService;
import service.impl.ApplicantServiceIMPL;
import service.impl.LetterServiceIMPL;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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

    private ApplicantService applicantService = new ApplicantServiceIMPL();
    private int statusLetter=0;
    ObservableList<String> list = FXCollections.observableArrayList("Tố Cáo", "Khiếu Nại", "Kiến Nghị Phản Ánh");


    public void initialize(URL url, ResourceBundle resourceBundle) {

        applicantID.textProperty().addListener((observable, oldVal, newVal) -> {

            try{
                Applicant thisapplicant = applicantService.findById(Integer.valueOf(newVal));
                address.setText(thisapplicant.getAddress());
                applicantName.setText(thisapplicant.getName());
            }
            catch (SQLException e){

            }
        });
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
