package controler.quanli_nhomdon;

import controler.quanli.SearchController;
import javafx.beans.binding.Bindings;
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

    private ObservableList<GroupLetter> groupLettersObservableList= null;
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
            groupLettersObservableListSearch=null;
            initTable();
        });
        butXoaNhom.setOnAction(actionEvent -> {
            ObservableList<GroupLetter> groupLettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            try{

                if (groupLettersSelected.get(0) != null) {
                    try {
                        groupLetterService.delete(groupLettersSelected.get(0));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
                initTable();
            }catch (IndexOutOfBoundsException e){
                System.out.println("bat duoc loi khong chon 1 don ");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Infor");
                alert.setContentText("hãy chọn một nhóm đơn!");
                alert.showAndWait();
            }

        });



        butXemChiTietNhom.setOnAction(actionEvent -> {  //chưa sửa sang group
            ObservableList<GroupLetter> groupLettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            try{
                if (groupLettersSelected.get(0) != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/home/quanli_nhomdon/show_nhomdon.fxml"));

                        // set quyen conntroler cho cai stage (<=> tuong duong viec fx:conntroller trong fxml)
                        Show_nhomdonControler controllerChiTietNhomDon =new Show_nhomdonControler();
                        //-------------------------
                        // neu dao vi tri 2 dong nay cho nhau se bi loi
                        controllerChiTietNhomDon.setGroupLetter(groupLettersSelected.get(0));
//                    controllerChiTietDon =loader.getController();
                        loader.setController(controllerChiTietNhomDon);
                        //------------------------

                        // phai load cai parent nay sau khi new controler neu khong no se goi ham init ma khong co du lieu
                        Parent parent=loader.load();
                        Scene scene = new Scene(parent);
                        Stage stageChinhSua = new Stage();
                        stageChinhSua.setTitle("Chi tiet nhom don ");
                        stageChinhSua.setScene(scene);
                        stageChinhSua.initModality(Modality.WINDOW_MODAL);
                        stageChinhSua.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                        stageChinhSua.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("bat duoc loi khong chon 1 don ");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Infor");
                alert.setContentText("hãy chọn một nhóm đơn!");
                alert.showAndWait();
            }

        });


        butCapnhatTrangThaiNhom.setOnAction(actionEvent -> {   //chưa sửa sang group
            ObservableList<GroupLetter> groupLettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            try{
                if (groupLettersSelected.get(0) != null) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/home/quanli_nhomdon/response_nhomdon.fxml"));
                        Response_nhomdonControler controller =new Response_nhomdonControler();
                        controller.setGroupLetter(groupLettersSelected.get(0));
                        loader.setController(controller);
                        Parent parent=loader.load();
                        Scene scene = new Scene(parent);
                        Stage stageChinhSua = new Stage();
                        stageChinhSua.setTitle("cập nhật trạng thái cho cả nhóm đơn");
                        stageChinhSua.setScene(scene);
                        stageChinhSua.initModality(Modality.WINDOW_MODAL);
                        stageChinhSua.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                        stageChinhSua.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }catch (IndexOutOfBoundsException e){
                System.out.println("bat duoc loi khong chon 1 don ");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Infor");
                alert.setContentText("hãy chọn một nhóm đơn!");
                alert.showAndWait();
            }

        });
    }

    private void initTable() {

        tableColumnSTT.setCellFactory(col -> {
            TableCell<Letter, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.when(cell.emptyProperty())
                    .then("")
                    .otherwise(cell.indexProperty().asString()));
            return cell ;
        });
        tableColumnTenNhom.setCellValueFactory(new PropertyValueFactory<GroupLetter, String>("name"));
        tableColumnSoLuong.setCellValueFactory(new PropertyValueFactory<GroupLetter, String>("quantity"));
        tableColumnTrangThai.setCellValueFactory(new PropertyValueFactory<GroupLetter, String>("statusString"));

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
            if (groupLetterService.findAll()!=null) {
                groupLetters.addAll(groupLetterService.findAll());
                groupLettersObservableList = FXCollections.observableList(groupLetters);
                tableViewLetter.setItems(groupLettersObservableList);
            }
        }
    }


}
