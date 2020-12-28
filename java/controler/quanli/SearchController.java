package controler.quanli;

import constance.AppConfig;
import dao.impl.LetterDaoIMPL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Letter;
import model.Problem;
import service.LetterService;
import service.impl.LetterServiceIMPL;
import service.impl.ProblemServiceIMPL;

import java.io.IOException;
import java.net.URL;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    public TextField applicantName;
    public TextField letterID;
    public ComboBox leterCategory;
    public ComboBox leterProblem;
    public AnchorPane anchorPane;
    public DatePicker date1, date2;


    @FXML
    private Button find;

    private List<Letter> lettersList=new ArrayList<>();
    public List<Letter> getList(){
        return lettersList;
    }



    private ObservableList<String> listProblem;

    private ObservableList<String> list = FXCollections.observableArrayList(AppConfig.CATE_LIST);// "" dung khi nguoi dung muon huy

    public SearchController(){

    };

    public void initialize(URL url, ResourceBundle resourceBundle) {

        // lay ra list problem

        try {
            List<String> tmp=new ArrayList<>();
            ProblemServiceIMPL problemService = new ProblemServiceIMPL();
            for (Problem iter : problemService.findAll()){
                tmp.add(iter.getName());
            }
            tmp.add("");
            listProblem=  FXCollections.observableArrayList(tmp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        leterProblem.setItems(listProblem);
        leterProblem.setValue("");

        list.add("");
        leterCategory.setItems(list);
        leterCategory.setValue("");

        letterID.setText("");

        find.setOnAction(actionEvent -> {


            ObservableList<String> list = FXCollections.observableArrayList(AppConfig.CATE_LIST);
            leterCategory.setItems(list);

            String cate = (String) leterCategory.getValue();
            String probl = (String) leterProblem.getValue();
            String idFind = (String) letterID.getText();
            System.out.println(date1.getValue());
            Date date_1 =null;
            Date date_2 =null;

            if (date1.getValue() == null){
                date_1 = null;}
            else{
                date_1 = Date.valueOf(date1.getValue());}
            if (date2.getValue() == null){
                date_2 = null;}
            else{
                date_2 = Date.valueOf(date2.getValue());}


//
//            String app_name = (String) applicantName.getText();
//            try {
//                List<Letter> a = letterService.findByApplyDate(date_1, date_2);
//                System.out.println(a);
//            }
//            catch (SQLException e){
//
//            }

//            System.out.println(("nguoi dung yeu cau tim "+cate+probl+idFind));




            lettersList.removeAll(lettersList);
            try {
//                List<Letter> lettersResByID = new ArrayList<>();
//                List<Letter> lettersResByCate = new ArrayList<>();
//                List<Letter> lettersResByProblem = new ArrayList<>();
//                List<Letter> lettersResByDate = new ArrayList<>();
//                List<Letter> lettersResByName = new ArrayList<>();
//                Letter a=null;
//                List<Letter> b=null,c=null;
//                List<Letter> d=null, e=null;
//                if(!idFind.equals("")) {
//                    System.out.println("co tim id");
//                    a = letterService.findById(idFind);
//                }
//                if(!cate.equals("")) {
//                    System.out.println("co tim cate");
//                    b = letterService.findByCategory(cate);
//                }
//                if(!probl.equals("")) {
//                    System.out.println("co tim problem");
//                    c = letterService.findByProblem(probl);
//                }
//                if(!date_1.toString().equals("") && !date_2.toString().equals("") ) {
//                    System.out.println("co tim problem");
//                    d = letterService.findByApplyDate(date_1, date_2);
//                }
//                if(!app_name.equals("")) {
//                    System.out.println("co tim name");
//                    e = letterService.findByApplicantName(app_name);
//                }
//
//
//                List<Letter> lettersTmp = new ArrayList<>();
//                if (a!= null) {
//                    System.out.println("co tim thay id"+a);
//                    lettersResByID.add(a);
//                    lettersTmp.addAll(lettersResByID);
//                }
//                if (b!= null) {
//                    System.out.println("co tim thay cate"+b);
//                    lettersResByCate.addAll(b);
//                    lettersTmp.addAll(lettersResByCate);
//                }
//                if (c!= null) {
//                    System.out.println("co tim thay pro"+c);
//                    lettersResByProblem.addAll(c);
//                    lettersTmp.addAll(lettersResByProblem);
//                }
//                if (d!= null) {
//                    System.out.println("co tim thay date"+d);
//                    lettersResByDate.addAll(d);
//                    lettersTmp.addAll(lettersResByDate);
//                }
//                if (e!= null) {
//                    System.out.println("co tim thay ten"+e);
//                    lettersResByName.addAll(e);
//                    lettersTmp.addAll(lettersResByName);
//                }
//
//
//                System.out.println("hop cua tat ca ket qua "+lettersTmp);
//                for( Letter iter : lettersTmp){
//                    boolean canAddIter=true;
//                    if (!lettersResByID.isEmpty()&&!lettersResByID.contains(iter)){
//                        System.out.println("set false o id");
//                        canAddIter=false;
//                    };
//                    if(!lettersResByCate.isEmpty()&&!lettersResByCate.contains(iter)){
//                        System.out.println("set false o cate"+lettersResByCate.isEmpty()+lettersResByCate.contains(iter));
//                        canAddIter=false;
//                    }
//                    if(!lettersResByProblem.isEmpty()&&!lettersResByProblem.contains(iter)){
//                        System.out.println("set false o pro");
//                        canAddIter=false;
//                    }
//
//
//                    if(canAddIter){
//                        lettersRes.add(iter);
//                    }
//                }
//
//                if(lettersRes.isEmpty())
//                    lettersRes.add(null);
                LetterServiceIMPL letterServiceIMPL=new LetterServiceIMPL();
                System.out.println(letterServiceIMPL.searchBy(idFind,cate,probl,-1,null,null,date_1,date_2,-1));

                lettersList.addAll(letterServiceIMPL.searchBy(idFind,cate,probl,-1,null,null,date_1, date_2,-1));
                System.out.println("list truoc khi tra ve "+lettersList);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        });


    }
}
