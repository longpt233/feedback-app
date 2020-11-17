package controler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    public TableView tableViewLetter;
    public TableColumn columnSTT;
    public TableColumn columnLoaiDon;
    public TableColumn columnNgayVietDon;
    public TableColumn columnNoiDung;
    public TableColumn columnTrangThai;


    @FXML
    private TextField letterID;

    @FXML
    private TextField applicantName;

    @FXML
    private Button find;

    @FXML
    private Button view;

    LetterServiceIMPL findLetter = new LetterServiceIMPL();

    private LetterService letterService = new LetterServiceIMPL();


    private ObservableList<Letter> lettersObservableList;

    private List<Letter> letters = new ArrayList<>();

    public SearchController(){

    };

    public void initialize(URL url, ResourceBundle resourceBundle) {

        find.setOnAction(actionEvent -> {
            initTable();

        });

        view.setOnAction(actionEvent -> {
            ObservableList<Letter> lettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            if (lettersSelected.get(0) != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/main/show" +
                            ".fxml"));

                    // set quyen conntroler cho cai stage (<=> tuong duong viec fx:conntroller trong fxml)
                    ShowControler controllerChiTietDon =new ShowControler();
                    //-------------------------
                    // neu dao vi tri 2 dong nay cho nhau se bi loi
                    controllerChiTietDon.setLetter(lettersSelected.get(0));
//                    controllerChiTietDon =loader.getController();
                    loader.setController(controllerChiTietDon);
                    //------------------------

                    // phai load cai parent nay sau khi new controler neu khong no se goi ham init ma khong co du lieu
                    Parent parent=loader.load();
                    Scene scene = new Scene(parent);
                    Stage stageChinhSua = new Stage();
                    stageChinhSua.setTitle("Chi tiet don ");
                    stageChinhSua.setScene(scene);
                    stageChinhSua.initModality(Modality.WINDOW_MODAL);
                    stageChinhSua.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                    stageChinhSua.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void initTable() {

        columnSTT.setCellValueFactory(new PropertyValueFactory<Letter, String>("id"));
        columnLoaiDon.setCellValueFactory(new PropertyValueFactory<Letter, String>("category"));
        columnNgayVietDon.setCellValueFactory(new PropertyValueFactory<Letter, String>("applyDate"));
        columnNoiDung.setCellValueFactory(new PropertyValueFactory<Letter, String>("content"));
        columnTrangThai.setCellValueFactory(new PropertyValueFactory<Letter, String>("id"));

        try {
            refreshTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void refreshTable() throws SQLException {
        System.out.println("init table");
        letters.removeAll(letters);
        letters.add(letterService.findById(Integer.valueOf(letterID.getText())));
        lettersObservableList = FXCollections.observableList(letters);
        tableViewLetter.setItems(lettersObservableList);
    }

//    public void findLT(){
//        Letter resultByID = null;
//        int ID = Integer.valueOf(letterID.getText());
//
//        try {
//            resultByID  = findLetter.findById(ID);
//            System.out.println("tim ra ID là "+resultByID.getId());
//
//        }
//        catch (SQLException e){
//            System.out.print(e.getMessage());
//        }
//        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main/show.fxml"));
//            InforLetterControler controller = loader.getController();
//            controller.setLetter(resultByID);
//
//            System.out.println("thong tin la ");
//            Scene inf = new Scene(loader.load());
//
//            Stage stage = new Stage();
//            stage.setTitle("Thong Tin");
//            stage.setScene(inf);
//            stage.show();
//
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }

}
