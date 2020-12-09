package controler.quanli;

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
    private ObservableList<String> list = FXCollections.observableArrayList("Khiếu Nại", "Kiến Nghị Phản Ánh");

    private ObservableList<String> listProblem;

    private String prblemSelected;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkCMND.setVisible(false);
        newProblem.setVisible(false);
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
                    newProblem.setText("*Vấn đề mới: "+prblemSelected);
                }
            }else {
                newProblem.setVisible(false);
                prblemSelected= (String) t1;
            }
            System.out.println("Bạn đã chọn vấn đề: "+prblemSelected);
        });




        applicantID.textProperty().addListener((observable, oldVal, newVal) -> {

            try{
                  Applicant thisapplicant = applicantService.findById(Integer.valueOf(newVal));
                  if(thisapplicant!=null){
                      checkCMND.setVisible(false);
                      applicantName.setText(thisapplicant.getName());
                  }
                  else {
                      checkCMND.setVisible(true);
                      applicantName.setText("");
                  }
            }
            catch (SQLException e){
                System.out.println("lỗi tìm cmnd");
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
                    String ID=String.valueOf(letterID.getText());
                    String ct= content.getText();
                    int aID= Integer.valueOf(applicantID.getText());
                    Date date= Date.valueOf(applyDate.getValue());
                    String cate= category.getValue();

                    Letter newLetter = new Letter(ID,cate, prblemSelected, aID, ct, date, statusLetter, false);
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
