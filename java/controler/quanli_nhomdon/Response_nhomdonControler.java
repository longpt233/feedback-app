package controler.quanli_nhomdon;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
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


        if(groupLetter.getStatus()==0) canRB.setSelected(true);
        if(groupLetter.getStatus()==1) waitRB.setSelected(true);
        if(groupLetter.getStatus()==2) cannotRB.setSelected(true);


        update.setOnAction(actionEvent -> {
            int status=-1;
            if(canRB.isSelected()) status=0;

            if(waitRB.isSelected()) status=1;
            if(cannotRB.isSelected()) status=2;
            System.out.println("chon nut"+status);
            if(status !=-1){
                try {
                    if(new GroupLetterServiceIMPL().updateStatusGroup(groupLetter,status)){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Infor");
                        alert.setContentText("Thành công");
                        alert.showAndWait();
                    }
                    Stage thisStage = (Stage)update.getScene().getWindow();
                    thisStage.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


    }




}

