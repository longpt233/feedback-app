package controler.quanli;

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
import service.LetterService;
import service.impl.LetterServiceIMPL;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable {

    public TextField applicantName;
    public ComboBox leterCategory;
    public AnchorPane anchorPane;
    @FXML
    private TextField letterID;

    @FXML
    private Button find;

    private LetterService letterService = new LetterServiceIMPL();
    private ObservableList<Letter> lettersObservableList=null;
    public ObservableList<Letter> getList(){
        return lettersObservableList;
    }

    private List<Letter> letters = new ArrayList<>();

    public SearchController(){

    };

    public void initialize(URL url, ResourceBundle resourceBundle) {

        find.setOnAction(actionEvent -> {

            ObservableList<String> list = FXCollections.observableArrayList("Tố Cáo", "Khiếu Nại", "Kiến Nghị Phản Ánh");
            leterCategory.setItems(list);

            String cate = (String) leterCategory.getValue();
            int ID = Integer.valueOf(letterID.getText());


            List<Letter> letters = new ArrayList<>();

            letters.removeAll(letters);
            try {

                letters.add(letterService.findById("HN15612"));
                lettersObservableList = FXCollections.observableList(letters);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        });


    }
}
