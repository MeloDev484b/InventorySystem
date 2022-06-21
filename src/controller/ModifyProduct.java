package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

// TODO validate input

public class ModifyProduct implements Initializable {
    public TextField nameField;
    public TextField invField;
    public TextField priceCostField;
    public TextField maxField;
    public TextField minField;
    public TableView inventoryPartsTableView;
    public TableView associatedPartTableView;
    public Button addAssociatedPartButton;
    public Button removeAssociatedPartButton;
    public Button saveProductButton;
    public Button cancelButton;
    public TextField inventoryPartsSearchTextField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Modify product active");
    }
    /*
    Retrieves text from TextFields and temporarily stores it in variables. These variables are used to create a Product.
    After the Product is added to allProducts, Inventory.productId is incremented and the window is closed.
    */
    public void onSaveButton(ActionEvent actionEvent) {
        int id = Inventory.productId;
        String name = nameField.getText();
        double price = Double.parseDouble(priceCostField.getText());
        int stock = Integer.parseInt(invField.getText());
        int min = Integer.parseInt(minField.getText());
        int max = Integer.parseInt(maxField.getText());
        Inventory.addProduct(new Product(id, name, price, stock, min, max));
        Inventory.incrementId(0);
        closeWindow();
    }

    /*
    Calls closeWindow() when the user clicks the cancel button.
    */
    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    /*
    Uses Stage.close() to close the AddProduct window.
    */
    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
