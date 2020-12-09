package controler.quanli;

import javafx.fxml.Initializable;
import model.Applicant;
import model.Letter;
import service.ApplicantService;
import service.impl.ApplicantServiceIMPL;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CollectLetterControler implements Initializable {



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

