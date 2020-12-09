package controler.quanli;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Applicant;
import model.Letter;
import service.ApplicantService;
import service.GroupLetterService;
import service.impl.ApplicantServiceIMPL;
import service.impl.GroupLetterServiceIMPL;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class CollectLetterControler implements Initializable {
    @FXML
    private TextField groupName;

    @FXML
    private Button createGroup, butCancel;


    private ApplicantService applicantService = new ApplicantServiceIMPL();
    private Letter letter;
    private Applicant applicant;

    ArrayList<Letter> listLetter = new ArrayList<Letter>();
    private GroupLetterService groupLetterService= new GroupLetterServiceIMPL();
    private ArrayList<String> listId = new ArrayList<String>();


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
    public void setListLetter(ObservableList<Letter> lettersSelected){
        for(Letter letter: lettersSelected){
            this.listLetter.add(letter);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println(listLetter);
        createGroup.setOnAction(actionEvent->{
            try{
                System.out.println(groupName.getText());
                groupLetterService.addNewGroup(listLetter, groupName.getText());
                System.out.println(listId);
            }
            catch (SQLException e){

            }
        });

    }




}

