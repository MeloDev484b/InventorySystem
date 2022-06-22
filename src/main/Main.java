package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/** The Main class is used to load and display the Inventory GUI.

 Javadocs are located in the javadoc folder at /QKM2_InventorySystem/javadocs

"FUTURE ENHANCEMENT"
If this application were to be updated, a future enhancement that could extend the functionality of the application
would be the ability to save data to a database or local hard drive.
*/
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Inventory.fxml"));
        primaryStage.setScene(new Scene(root, 960, 480));
        primaryStage.show();
    }

/** Entry point for the program.
*/
    public static void main(String[] args) {
        launch(args);
    }
}
