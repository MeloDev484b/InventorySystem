package controller;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
/*
The AddProduct class is used to build Product objects and add them to allProducts in the Inventory class.
*/
public class AddProduct implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add product active");
    }

    /*
    Retrieves text from TextFields and temporarily stores it in variables.
    These variables are used to create a Product object which is then added to the ProductTableView in Inventory.
    */

    /*
    Calls closeWindow() when the user clicks the cancel button.
    */

    /*
    Uses Stage.close() to close the AddProduct window.
    */
}
