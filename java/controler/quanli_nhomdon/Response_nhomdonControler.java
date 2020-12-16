package controler.quanli_nhomdon;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import model.Applicant;
import model.GroupLetter;
import model.Letter;
import service.ApplicantService;
import service.impl.ApplicantServiceIMPL;
import service.impl.GroupLetterServiceIMPL;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Response_nhomdonControler implements Initializable {

    @FXML
    private RadioButton canRB;
    @FXML
    private RadioButton waitRB;
    @FXML
    private RadioButton cannotRB;

    @FXML
    private Button update;

    @FXML
    private Button huy;




    GroupLetter groupLetter;

    public void setGroupLetter(GroupLetter groupLetter){
            this.groupLetter=groupLetter;
            System.out.println(groupLetter);


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        if(groupLetter.getStatus()==1) canRB.setSelected(true);
        if(groupLetter.getStatus()==2) cannotRB.setSelected(true);
        if(groupLetter.getStatus()==3) waitRB.setSelected(true);

        update.setOnAction(actionEvent -> {
            int status=-1;
            if(canRB.isSelected()) status=1;
            if(cannotRB.isSelected()) status=2;
            if(waitRB.isSelected()) status=3;
            System.out.println("chon nut"+status);
            if(status !=-1){
                try {
                    new GroupLetterServiceIMPL().updateStatusGroup(groupLetter,status);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


    }




}

