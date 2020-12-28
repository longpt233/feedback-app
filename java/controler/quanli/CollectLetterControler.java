package controler.quanli;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Applicant;
import model.GroupLetter;
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
    private List<GroupLetter> groupLetterList = new ArrayList<>();
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
            this.applicant=applicantService.findByIdentityCard(letter.getIdApplicant());
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
                try{
                    groupLetterList=groupLetterService.findAll();
                    for(GroupLetter g : groupLetterList){
                        if(g.getName().toLowerCase().equals(groupName.getText().toLowerCase())){

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Infor");
                            alert.setContentText("Nhóm đã tồn tại");
                            alert.showAndWait();
                            throw new IndexOutOfBoundsException("Nhóm đã tồn tại");

                        }
                    }
                }
                catch (SQLException e){

                }

                System.out.println(listId);
                if(groupLetterService.addNewGroup(listLetter, groupName.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Infor");
                    alert.setContentText("Thành công");
                    alert.showAndWait();
                }
                Stage thisStage = (Stage) createGroup.getScene().getWindow();
                thisStage.close();

            }
            catch (IndexOutOfBoundsException e2){
                System.out.println(e2.toString());
            }
            catch (SQLException e){
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Infor");
                alert.setContentText("Có lỗi khi gộp đơn");
                alert.showAndWait();
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




}

