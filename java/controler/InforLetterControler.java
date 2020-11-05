package controler;

import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.Applicant;
import model.Letter;
import service.ApplicantService;
import service.impl.ApplicantServiceIMPL;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InforLetterControler implements Initializable {

    public TextArea textFieldToString;
    private ApplicantService applicantService = new ApplicantServiceIMPL();
    private Letter letter;

    public void setLetter(Letter letter){
        System.out.println("call set");
        this.letter=letter;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hello"+letter.toString());
        textFieldToString.setText(letter.toString());

        try {
            Applicant applicant = applicantService.findById(letter.getIdApplicant());
            System.out.println("hello" + applicant.toString());
            textFieldToString.setText(letter.toString() + "\n" + applicant.toString());
            // chinh cac thong tin co the xem dc o day
        } catch (SQLException e) {
            System.out.println("can't find");
            e.printStackTrace();
        }
    }
}
