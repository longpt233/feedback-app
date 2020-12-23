package controler.quanli;

import constance.AppConfig;
import dao.impl.LetterDaoIMPL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Applicant;
import model.Letter;
import model.Problem;
import service.ApplicantService;
import service.impl.ApplicantServiceIMPL;
import service.impl.LetterServiceIMPL;
import service.impl.ProblemServiceIMPL;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditControler implements Initializable {
    EditControler(){
    }

    @FXML
    private Pane pan1;

    @FXML
    private TextField organizationName1;

    @FXML
    private TextArea content ;

    @FXML
    private Button update, butCancel;

    @FXML
    private TextField letterID1;


    @FXML
    private TextField applicantName;


    @FXML
    private ComboBox<String> category;
    @FXML
    private ComboBox<String> problem;


    @FXML
    private ToggleGroup genderGroup;


    @FXML
    private TextField applicantID;

    @FXML
    private DatePicker applyDate;

    @FXML
    private TextField problemAddNew;

    @FXML
    public Label checkCMND;

    @FXML
    private DatePicker processedDate;

    @FXML
    private ToggleGroup statusGroup;


    private ApplicantService applicantService = new ApplicantServiceIMPL();
    private Letter letter;
    private Applicant applicant;


    public void setLetter(Letter letter){
        this.letter=letter;

        try{
            this.applicant=applicantService.findByIdentityCard(letter.getIdApplicant());
//            System.out.println("applicant trong edit"+applicant.toString());
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
    public int statusLetter=1;
    ObservableList<String> list = FXCollections.observableArrayList(AppConfig.CATE_LIST);

    private ObservableList<String> listProblem;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        checkCMND.setVisible(false);

        // lay ra list problem
        try {
            List<String> tmp=new ArrayList<>();
            ProblemServiceIMPL problemService=new ProblemServiceIMPL();
            for (Problem iter : problemService.findAll()){
                tmp.add(iter.getName());
            };
            tmp.add("Khác");
            listProblem=  FXCollections.observableArrayList(tmp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        problem.setItems(listProblem);
        problem.setValue(String.valueOf(letter.getProblem()));


        // init su kien cho viec check nguoi
        applicantID.setText(applicant.getIdentityCard());
        applicantID.textProperty().addListener((observable, oldVal, newVal) -> {
            try{
                Applicant thisapplicant = applicantService.findByIdentityCard(String.valueOf(newVal));
                // neu tim thay nguoi nay
                if(thisapplicant!=null){
                    applicantName.setText(thisapplicant.getName());
                    checkCMND.setVisible(true);
                    checkCMND.setText("CCCD hợp lệ ");
                }
                // neu khong thay
                else {
                    checkCMND.setVisible(true);
                    checkCMND.setText("CCCD không tồn tại ");
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        });
        category.setItems(list);
        category.setValue(String.valueOf(letter.getCategory()));

        letterID1.setText(String.valueOf(letter.getId()));
        //        khong cho sua id vi phuong thuc update khon gho tro
        letterID1.setEditable(false);
//        applicantID.setText(String.valueOf(applicant.getId()));
        applicantName.setText(applicant.getName());
        organizationName1.setText(letter.getOrganization());

        String date = new SimpleDateFormat("yyyy-MM-dd").format(letter.getApplyDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date , formatter);
        applyDate.setValue(localDate);

        content.setText(letter.getContent());

        update.setOnAction(actionEvent -> {
        try {
            LetterServiceIMPL letterServiceIMPL = new LetterServiceIMPL();
            if(letterServiceIMPL.update(getLetter())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("infor");
                alert.setContentText("sửa thành công ");
                alert.show();
            }
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thiếu Thông Tin");
            alert.setContentText("Thiếu Thông Tin!");
            alert.showAndWait();

            e.printStackTrace();
        }

    });


        butCancel.setOnAction(actionEvent -> {
        try {
            Stage thisStage = (Stage) butCancel.getScene().getWindow();
            thisStage.close();
        }
        catch (Exception e) {
        }

    });

}



    private Letter getLetter(){

        String ID=String.valueOf(letterID1.getText());

        String tit=problem.getValue();
        if(problemAddNew.getText()!=null){
            tit=problemAddNew.getText();
        };
        String ct= content.getText();
        String aID= String.valueOf(applicantID.getText());
        Date date= Date.valueOf(applyDate.getValue());
        String cate= category.getValue();
        String orga=String.valueOf(organizationName1.getText());


        Letter dataFXML = new Letter(ID,cate, tit, aID, ct,orga, date, statusLetter, false);
                    System.out.println(dataFXML.getId());
                    System.out.println(dataFXML.getApplyDate());
                    System.out.println(dataFXML.getProblem());
                    System.out.println(dataFXML.getCategory());

        return dataFXML;
    }


}
