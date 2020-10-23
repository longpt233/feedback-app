package controler;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        but1.setOnAction(actionEvent -> {
            pan1.toFront();
        });
        but2.setOnAction(actionEvent -> {
            pan2.toFront();
        });
        but3.setOnAction(actionEvent -> {
            pan3.toFront();
        });
    }

    @FXML
    private Button but1, but2, but3;

    @FXML
    private Pane pan1 ,pan2, pan3;



//    private void butAc(ActionEvent e){
//        if(e.getSource() == but1){
//            pan1.toFront();
//
//        }
//        else if(e.getSource() == but2){
//            pan2.toFront();
//
//        }
//        else if(e.getSource() == but3){
//            pan3.toFront();
//
//        }
//    }

}
