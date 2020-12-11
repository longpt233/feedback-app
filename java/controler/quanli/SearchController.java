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

    @FXML
    private Button find;

    private LetterService letterService = new LetterServiceIMPL();
    private ObservableList<Letter> lettersObservableList=null;
    public ObservableList<Letter> getList(){
        return lettersObservableList;
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

            System.out.println(("nguoi dung yeu cau tim "+cate+probl+idFind));


            List<Letter> lettersRes = new ArrayList<>();

            lettersRes.removeAll(lettersRes);
            try {
                List<Letter> lettersResByID = new ArrayList<>();
                List<Letter> lettersResByCate = new ArrayList<>();
                List<Letter> lettersResByProblem = new ArrayList<>();
                Letter a=null;
                List<Letter> b=null,c=null;
                if(!idFind.equals("")) {
                    System.out.println("co tim id");
                    a = letterService.findById(idFind);
                }
                if(!cate.equals("")) {
                    System.out.println("co tim cate");
                    b = letterService.findByCategory(cate);
                }
                if(!probl.equals("")) {
                    System.out.println("co tim problem");
                    c = letterService.findByProblem(probl);
                }
                List<Letter> lettersTmp = new ArrayList<>();
                if (a!= null) {
                    System.out.println("co tim thay id"+a);
                    lettersResByID.add(a);
                    lettersTmp.addAll(lettersResByID);
                }
                if (b!= null) {
                    System.out.println("co tim thay cate"+b);
                    lettersResByCate.addAll(b);
                    lettersTmp.addAll(lettersResByCate);
                }
                if (c!= null) {
                    System.out.println("co tim thay pro"+c);
                    lettersResByProblem.addAll(c);
                    lettersTmp.addAll(lettersResByProblem);
                }


                System.out.println("hop cua tat ca ket qua "+lettersTmp);
                for( Letter iter : lettersTmp){
                    boolean canAddIter=true;
                    if (!lettersResByID.isEmpty()&&!lettersResByID.contains(iter)){
                        System.out.println("set false o id");
                        canAddIter=false;
                    };
                    if(!lettersResByCate.isEmpty()&&!lettersResByCate.contains(iter)){
                        System.out.println("set false o cate"+lettersResByCate.isEmpty()+lettersResByCate.contains(iter));
                        canAddIter=false;
                    }
                    if(!lettersResByProblem.isEmpty()&&!lettersResByProblem.contains(iter)){
                        System.out.println("set false o pro");
                        canAddIter=false;
                    }
                    if(canAddIter){
                        lettersRes.add(iter);
                    }
                }

                if(lettersRes.isEmpty())
                    lettersRes.add(null);
                System.out.println("list truoc khi tra ve"+lettersRes);
                lettersObservableList = FXCollections.observableList(lettersRes);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
//            System.out.println("tim duoc "+lettersObservableList.get(0).toString());
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        });


    }
}
