package view.user;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Testfx extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("testjavafx.fxml"));
        stage.setTitle("test");
        stage.setScene(new Scene(root,300,300));
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
