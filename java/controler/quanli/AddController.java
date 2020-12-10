package controler.quanli;

import constance.AppConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Applicant;
import model.Letter;
import model.Problem;
import service.ApplicantService;
import service.ProblemService;
import service.impl.ApplicantServiceIMPL;
import service.impl.LetterServiceIMPL;
import service.impl.ProblemServiceIMPL;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    public ComboBox problem;
    public Button addLetter;
    public Button cancel;
    public Label checkCMND;
    public Label newProblem;
    @FXML
    private TextField organizationName;

    @FXML
    private TextField letterID, applicantID, applicantName;

    @FXML
    private TextArea content;

    @FXML
    private DatePicker applyDate;

    @FXML
    private ComboBox<String> category;

    private ApplicantService applicantService = new ApplicantServiceIMPL();
    private ProblemService problemService = new ProblemServiceIMPL();
    // cai status letter nay khi moi tao don luon luon bang 1 (chua xu li )
    private int statusLetter=1;
    private ObservableList<String> list = FXCollections.observableArrayList(AppConfig.CATE_LIST);

    private ObservableList<String> listProblem;

    private String prblemSelected;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkCMND.setVisible(false);
        newProblem.setVisible(false);

        // lay ra list problem
        try {
            List<String> tmp=new ArrayList<>();
            for (Problem iter : problemService.findAll()){
                tmp.add(iter.getName());
            };
            tmp.add("Khác");
            listProblem=  FXCollections.observableArrayList(tmp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        problem.setItems(listProblem);

        problem.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {
            if (t1.equals("Khác")){
                Dialog<String> dialog=new TextInputDialog();
                dialog.setTitle("Thêm Vấn Đề Mới");
                dialog.setHeaderText("Nhập Vấn Đề Mới");
                Optional<String> input=dialog.showAndWait();
                // neu co nhap vao
                if (input.isPresent()){
                    prblemSelected=input.get();
                    newProblem.setVisible(true);
                    newProblem.setText("*ban da chon vấn đề mới: "+prblemSelected);
                }
            }else {
                newProblem.setVisible(false);
                prblemSelected= (String) t1;
            }
        });
        // init su kien cho viec check nguoi
        applicantID.textProperty().addListener((observable, oldVal, newVal) -> {
            try{
                Applicant thisapplicant = applicantService.findByIdentityCard(String.valueOf(newVal));
                // neu tim thay nguoi nay
                if(thisapplicant!=null){
                    applicantName.setText(thisapplicant.getName());
                    checkCMND.setVisible(true);
                    checkCMND.setText("CCCD hop le");
                }
                // neu khong thay
                else {
                    checkCMND.setVisible(true);
                    checkCMND.setText("khong co id nay trong db");
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        });


        category.setItems(list);
        addLetter.setOnAction(actionEvent -> {
            try {


                    String ID=String.valueOf(letterID.getText());
                    String ct= content.getText();
                    String aID= String.valueOf(applicantID.getText());
                    Date date= Date.valueOf(applyDate.getValue());
                    String cate= category.getValue();
                    String organi=String.valueOf(organizationName.getText());

                    Letter newLetter = new Letter(ID,cate, prblemSelected, aID, ct,organi, date, statusLetter, false);
                    LetterServiceIMPL letterServiceIMPL=new LetterServiceIMPL();
                    // neu them thanh cong
                    if(letterServiceIMPL.insert(newLetter)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("infor");
                        alert.setContentText("them don thanh cong");
                        alert.showAndWait();
                    }
                Stage thisStage = (Stage) addLetter.getScene().getWindow();
                thisStage.close();

            }
            catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("error");
                alert.setContentText("Thiếu Thông Tin");
                alert.showAndWait();
                e.printStackTrace();
            }

        });
        cancel.setOnAction(actionEvent -> {
            try {
                Stage thisStage = (Stage) cancel.getScene().getWindow();
                thisStage.close();
            }
            catch (Exception e) {
            }
        });
    }
}
