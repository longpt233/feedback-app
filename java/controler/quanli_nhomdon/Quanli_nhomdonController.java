package controler.quanli_nhomdon;

import controler.quanli.SearchController;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GroupLetter;
import model.Letter;
import service.GroupLetterService;
import service.LetterService;
import service.impl.GroupLetterServiceIMPL;
import service.impl.LetterServiceIMPL;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Quanli_nhomdonController implements Initializable {

    public TableView tableViewLetter;
    public TableColumn tableColumnSTT;
    public TableColumn tableColumnTenNhom;
    public TableColumn tableColumnSoLuong;
    public TableColumn tableColumnTrangThai;
    public Button butCapnhatTrangthai, butXoa;


    @FXML
    private Button butXemChiTietNhom,butCapnhatTrangThaiNhom, butXoaNhom, btnResetNhom;

    private LetterService letterService = new LetterServiceIMPL();

    private GroupLetterService groupLetterService = new GroupLetterServiceIMPL();


//    private ObservableList<Letter> lettersObservableList;
//
//    private ObservableList<Letter> lettersObservableListSearch;

    private ObservableList<Letter> groupLettersObservableList;
    private ObservableList<Letter> groupLettersObservableListSearch;

//    private List<Letter> letters = new ArrayList<>();

    private List<GroupLetter> groupLetters = new ArrayList<GroupLetter>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initBut();

    }

    private void initBut() {

        btnResetNhom.setOnAction(actionEvent -> {
            // cai nay chua test
            groupLettersObservableListSearch=null;
            initTable();
        });


        butXemChiTietNhom.setOnAction(actionEvent -> {  //chưa sửa sang group
            ObservableList<Letter> lettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            if (lettersSelected.get(0) != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/home/quanli/show.fxml"));

                    // set quyen conntroler cho cai stage (<=> tuong duong viec fx:conntroller trong fxml)
                    Show_nhomdonControler controllerChiTietDon =new Show_nhomdonControler();
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


        butCapnhatTrangThaiNhom.setOnAction(actionEvent -> {   //chưa sửa sang group
            ObservableList<Letter> lettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            if (lettersSelected.get(0) != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/home/quanli/collect.fxml"));
                    Response_nhomdonControler controller =new Response_nhomdonControler();
                    controller.setLetter(lettersSelected.get(0));
                    Parent parent=loader.load();
                    Scene scene = new Scene(parent);
                    Stage stageChinhSua = new Stage();
                    stageChinhSua.setTitle("cap nhat trong thai don  ");
                    stageChinhSua.setScene(scene);
                    stageChinhSua.initModality(Modality.WINDOW_MODAL);
                    stageChinhSua.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                    stageChinhSua.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        butXoaNhom.setOnAction(actionEvent -> {  // chưa sửa sang group
            ObservableList<Letter> lettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            if (lettersSelected.get(0) != null) {
                // del xong hien thi thon bao cho nguoi duing
            }
        });
    }

    private void initTable() {

        tableColumnSTT.setCellValueFactory(new PropertyValueFactory<GroupLetter, String>("id"));
        tableColumnTenNhom.setCellValueFactory(new PropertyValueFactory<GroupLetter, String>(""));
        tableColumnSoLuong.setCellValueFactory(new PropertyValueFactory<GroupLetter, String>(""));
        tableColumnTrangThai.setCellValueFactory(new PropertyValueFactory<GroupLetter, String>("status"));

        try {
            refreshTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void refreshTable() throws SQLException {
        System.out.println("refresh table");

        if(groupLettersObservableListSearch!=null&&!groupLettersObservableListSearch.isEmpty()){
            tableViewLetter.setItems(groupLettersObservableListSearch);

        }else {
            groupLetters.removeAll(groupLetters);
//            groupLetters.addAll(groupLetterService.findAll());//find all chua co
//            groupLettersObservableList = FXCollections.observableList(groupLetters);
            tableViewLetter.setItems(groupLettersObservableList);
        }
    }


}
