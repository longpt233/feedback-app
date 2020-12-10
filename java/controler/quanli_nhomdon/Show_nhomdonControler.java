package controler.quanli_nhomdon;

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

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Show_nhomdonControler implements Initializable {
    Show_nhomdonControler(){
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
            this.applicant=applicantService.findByIdentityCard(letter.getIdApplicant());
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
        if(letter.getStatusLetter()==1) canRB.setSelected(true);
        if(letter.getStatusLetter()==2) cannotRB.setSelected(true);
        if(letter.getStatusLetter()==3) waitRB.setSelected(true);
        System.out.println("hello"+letter.toString());




    }




}
