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

public class ResponseControler implements Initializable {



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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




}

