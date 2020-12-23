package controler.quanli_nhomdon;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Applicant;
import model.GroupLetter;
import model.Letter;
import service.ApplicantService;
import service.GroupLetterService;
import service.LetterService;
import service.impl.ApplicantServiceIMPL;
import service.impl.GroupLetterServiceIMPL;
import service.impl.LetterServiceIMPL;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Show_nhomdonControler implements Initializable {
    Show_nhomdonControler(){
    }
    public TableView tableViewLetter;
    public TableColumn tableColumnSTT;
    public TableColumn tableColumnLoaiDon;
    public TableColumn tableColumnNgayVietDon;
    public TableColumn tableColumnNoiDung;
    public TableColumn tableColumnTrangThai;

    private LetterService letterService = new LetterServiceIMPL();

    private ObservableList<Letter> lettersObservableList=null;

    private ObservableList<Letter> lettersObservableListSearch;

    private List<Letter> letters = new ArrayList<>();
    private List<Letter> listLetter = new ArrayList<>();

 //
//    private ApplicantService applicantService = new ApplicantServiceIMPL();
//    private Letter letter;
//    private GroupLetter groupLetter;
//    private Applicant applicant;

    private GroupLetterService groupLetterService = new GroupLetterServiceIMPL();


//    public void setLetter(Letter letter){
//        System.out.println("call set");
//        this.letter=letter;
//        try{
//            System.out.println("id="+letter.getIdApplicant());
//            this.applicant=applicantService.findByIdentityCard(letter.getIdApplicant());
//            System.out.println("Nguoi nay la "+applicant.getName());
//        }
//        catch (SQLException e){
//
//        }
//
//    }
    public void setGroupLetter(GroupLetter groupLetter){
        try {
            listLetter = groupLetterService.detailGroup(groupLetter);
            System.out.println(listLetter);
//            System.out.println("tim duoc roi");
        }
        catch (SQLException e) {
//            System.out.println("chua tim duoc");
            e.printStackTrace();
        }

    }
//    public int statusLetter=1;
//    ObservableList<String> list = FXCollections.observableArrayList("Tố Cáo", "Khiếu Nại", "Kiến Nghị Phản Ánh");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            initTable();

//        letterID1.setText(String.valueOf(letter.getId()));
//// Vẫn chưa tìm được applicant
//        applicantID.setText(String.valueOf(applicant.getId()));
//        applicantName.setText(applicant.getName());
//        address.setText(applicant.getAddress());
//        if(applicant.getGender()==1){
//            maleRB.setSelected(true);
//        }
//        if(applicant.getGender()==0){
//            fermaleRB.setSelected(true);
//        }
//
//        String date = new SimpleDateFormat("yyyy-MM-dd").format(letter.getApplyDate());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(date , formatter);
//        applyDate.setValue(localDate);
//
//        title.setText(letter.getProblem());
//        content.setText(letter.getContent());
//        category.setValue(letter.getCategory());
//        if(letter.getStatusLetter()==1) canRB.setSelected(true);
//        if(letter.getStatusLetter()==2) cannotRB.setSelected(true);
//        if(letter.getStatusLetter()==3) waitRB.setSelected(true);
//        System.out.println("hello"+letter.toString());

    }

    private void initTable() {
        //chon duoc nhieu dong
        tableViewLetter.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // nhung truong nay bat buoc phai tuong ung thuoc tinh cua model thi moi hien thi dc
        tableColumnSTT.setCellFactory(col -> {
            TableCell<Letter, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.when(cell.emptyProperty())
                    .then("")
                    .otherwise(cell.indexProperty().asString()));
            return cell ;
        });
        tableColumnLoaiDon.setCellValueFactory(new PropertyValueFactory<Letter, String>("category"));
        tableColumnNgayVietDon.setCellValueFactory(new PropertyValueFactory<Letter, String>("applyDate"));
        tableColumnNoiDung.setCellValueFactory(new PropertyValueFactory<Letter, String>("content"));
        tableColumnTrangThai.setCellValueFactory(new PropertyValueFactory<Letter, String>("statusString"));



        try {
            refreshTable();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void refreshTable() throws SQLException {
        System.out.println("refresh table");

        if(lettersObservableListSearch!=null&&!lettersObservableListSearch.isEmpty()){
            tableViewLetter.setItems(lettersObservableListSearch);


        }else {
            letters.removeAll(letters);
            letters.addAll(listLetter);
            lettersObservableList = FXCollections.observableList(letters);
            tableViewLetter.setItems(lettersObservableList);
        }
    }


}




