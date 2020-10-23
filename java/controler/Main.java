package controler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application {

    public void start(Stage primaryStage) {

        try{
            Parent root= FXMLLoader.load(getClass().getClassLoader().getResource("scene/login.fxml"));
            Scene test = new Scene(root);
            primaryStage.setScene(test);
            primaryStage.show();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
