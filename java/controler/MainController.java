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

public class MainController implements Initializable {

    public TableView tableViewLetter;
    public TableColumn tableColumnSTT;
    public TableColumn tableColumnLoaiDon;
    public TableColumn tableColumnNgayVietDon;
    public TableColumn tableColumnNoiDung;
    public TableColumn tableColumnTrangThai;


    @FXML
    private Button butTaoDon, butThongKe, butTimKiem, butXemChiTiet;

    private LetterService letterService = new LetterServiceIMPL();


    private ObservableList<Letter> lettersObservableList;

    private List<Letter> letters = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        initBut();

    }

    private void initBut() {
        // tat man hinh hien tai de hien thi man hinh tao don
        butTaoDon.setOnAction(actionEvent -> {
//            try {
//                Parent blad = FXMLLoader.load(getClass().getResource("/view/main/add.fxml"));
//                Scene scene = new Scene(blad);
//                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                appStage.setTitle("Tao Don");
//                appStage.setScene(scene);
//                appStage.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/main/add.fxml"));
                Parent parent=loader.load();
                Scene scene = new Scene(parent);
                Stage stageAdd = new Stage();
                stageAdd.setTitle("Add new letter");
                stageAdd.setScene(scene);
                stageAdd.initModality(Modality.WINDOW_MODAL);
                stageAdd.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());

                stageAdd.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        butThongKe.setOnAction(actionEvent -> {

        });
        butTimKiem.setOnAction(actionEvent -> {
            try {
                Parent blad = FXMLLoader.load(getClass().getResource("/view/main/search.fxml"));
                Scene scene = new Scene(blad);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setTitle("Tim Kiem");
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // khong tat man hinh hien tai
        // hien thi ca 2 cung luc
        // khong the thao tac voi cua so cha
        butXemChiTiet.setOnAction(actionEvent -> {
            ObservableList<Letter> lettersSelected = tableViewLetter.getSelectionModel().getSelectedItems();
            if (lettersSelected.get(0) != null) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/main/show.fxml"));

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

        tableColumnSTT.setCellValueFactory(new PropertyValueFactory<Letter, String>("id"));
        tableColumnLoaiDon.setCellValueFactory(new PropertyValueFactory<Letter, String>("category"));
        tableColumnNgayVietDon.setCellValueFactory(new PropertyValueFactory<Letter, String>("applyDate"));
        tableColumnNoiDung.setCellValueFactory(new PropertyValueFactory<Letter, String>("content"));
        tableColumnTrangThai.setCellValueFactory(new PropertyValueFactory<Letter, String>("statusLetter"));

        try {
            refreshTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void refreshTable() throws SQLException {
        System.out.println("init table");
        letters.removeAll(letters);
        letters.addAll(letterService.findAll());
        lettersObservableList = FXCollections.observableList(letters);
        tableViewLetter.setItems(lettersObservableList);
    }


}
