package controler.quanli;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.Applicant;
import model.Letter;
import service.ApplicantService;
import service.impl.ApplicantServiceIMPL;
import service.impl.LetterServiceIMPL;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditControler implements Initializable {
    EditControler(){
    }

    @FXML
    private Pane pan1;

    @FXML
    private TextField organizationName;

    @FXML
    private TextArea content ;

    @FXML
    private Button update;

    @FXML
    private TextField letterID1;

    @FXML
    private Button delete;

    @FXML
    private TextField applicantName;

    @FXML
    private TextField address;

    @FXML
    private ComboBox<String> category;

    @FXML
    private RadioButton fermaleRB;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private RadioButton maleRB;

    @FXML
    private TextField applicantID;

    @FXML
    private DatePicker applyDate;

    @FXML
    private TextField title;

    @FXML
    private DatePicker processedDate;

    @FXML
    private RadioButton canRB;

    @FXML
    private ToggleGroup statusGroup;

    @FXML
    private RadioButton cannotRB;

    @FXML
    private RadioButton waitRB;

 //
    private ApplicantService applicantService = new ApplicantServiceIMPL();
    private Letter letter;
    private Applicant applicant;


    public void setLetter(Letter letter){
        System.out.println("call set");
        this.letter=letter;
        try{
            System.out.println("id="+letter.getIdApplicant());
            this.applicant=applicantService.findById(letter.getIdApplicant());
            System.out.println("Nguoi nay la "+applicant.getName());
        }
        catch (SQLException e){

        }

    }
    public int statusLetter=1;
    ObservableList<String> list = FXCollections.observableArrayList("Tố Cáo", "Khiếu Nại", "Kiến Nghị Phản Ánh");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        letterID1.setText(String.valueOf(letter.getId()));
// Vẫn chưa tìm được applicant
        applicantID.setText(String.valueOf(applicant.getId()));
        applicantName.setText(applicant.getName());
        address.setText(applicant.getAddress());
        if(applicant.getGender()==1){
            maleRB.setSelected(true);
        }
        if(applicant.getGender()==0){
            fermaleRB.setSelected(true);
        }

        String date = new SimpleDateFormat("yyyy-MM-dd").format(letter.getApplyDate());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date , formatter);
        applyDate.setValue(localDate);

        title.setText(letter.getProblem());
        content.setText(letter.getContent());
        category.setValue(letter.getCategory());
//        if(letter.getStatusLetter()==1) canRB.setSelected(true);
//        if(letter.getStatusLetter()==2) cannotRB.setSelected(true);
//        if(letter.getStatusLetter()==3) waitRB.setSelected(true);
        System.out.println("hello"+letter.toString());
//        content.setText(letter.toString());
//
//        try {
////            Applicant applicant = applicantService.findById(letter.getIdApplicant());
//            System.out.println("hello" + applicant.toString());
//            content.setText(letter.toString() + "\n" + applicant.toString());
//            // chinh cac thong tin co the xem dc o day
//        } catch (SQLException e) {
//            System.out.println("can't find");
//            e.printStackTrace();
//        }

//        category.setItems(list);
//        if(canRB.isSelected()){
//        statusLetter=1;
//    }
//        if(cannotRB.isSelected()){
//        statusLetter=2;
//    }
//        if(waitRB.isSelected()){
//        statusLetter=3;
//    }
//
//        update.setOnAction(actionEvent -> {
//        try {
//            LetterServiceIMPL udateData = new LetterServiceIMPL();
//            udateData.update(getData());
//
//        }
//        catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Thiếu Thông Tin");
//            alert.setContentText("Thiếu Thông Tin!");
//            alert.showAndWait();
//
//            e.printStackTrace();
//        }
//
//    });
//
//        delete.setOnAction(actionEvent -> {
//        try {
//            LetterServiceIMPL deleteData = new LetterServiceIMPL();
//            deleteData.delete(getData().getId());
//
//
//        }
//        catch (Exception e) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Thiếu Thông Tin");
//            alert.setContentText("Thiếu Thông Tin!");
//            alert.showAndWait();
//
//            e.printStackTrace();
//        }
//
//    });

}



    private Letter getData(){
        String ID=String.valueOf(letterID1.getText());
        String tit=title.getText();
        String ct= content.getText();
        int aID= Integer.valueOf(applicantID.getText());
        Date date= Date.valueOf(applyDate.getValue());
        String cate= category.getValue();


        Letter dataFXML = new Letter(ID,cate, tit, aID, ct, date, statusLetter, false);
                    System.out.println(dataFXML.getId());
                    System.out.println(dataFXML.getApplyDate());
                    System.out.println(dataFXML.getProblem());
                    System.out.println(dataFXML.getCategory());

        return dataFXML;
    }


}
