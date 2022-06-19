package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
The Main class is used to load and display the Inventory GUI.
*/
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Inventory.fxml"));
        primaryStage.setScene(new Scene(root, 960, 480));
        primaryStage.show();
    }

/*
Entry point for the program.
*/
    public static void main(String[] args) {
        launch(args);
    }
}
