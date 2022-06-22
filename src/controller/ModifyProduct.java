package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

// TODO validate input

/*
The AddProduct class is used to build Product objects and add them to allProducts in the Inventory class.
*/
public class ModifyProduct implements Initializable {
    Stage confirmRemovalStage = new Stage();
    Scene confirmRemovalScene;
    public TextField idField, nameField, invField, priceCostField, maxField, minField;
    public TableView inventoryPartsTableView;
    public TableView<Part> associatedPartsTableView;
    public Button addAssociatedPartButton, removeAssociatedPartButton, saveProductButton, cancelButton;
    public TextField inventoryPartsSearchTextField;
    // Inventory Part columns
    public TableColumn partIdColumn, partNameColumn, inventoryLevelColumn, pricePerUnitColumn;
    // Associated Part columns
    public TableColumn aPartIdColumn, aPartNameColumn, aInventoryLevelColumn, aPricePerUnitColumn;
    private Part selectedPart = null;
    private Part selectedAssociatedPart = null;
    private Product temp;
    private int savedIndex;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        savedIndex = Inventory.getIndex(Inventory.selectedProduct);
        temp = Inventory.selectedProduct;
        productIntake();
        inventoryPartsTableView.setItems(Inventory.getAllParts());
        associatedPartsTableView.setItems(temp.getAllAssociatedParts());

        // Set Inventory Part table columns
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Set associated Part table columns
        aPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        aPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        aInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        aPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void productIntake() {
        if (temp != null) {
            idField.setText(String.valueOf(temp.getId()));
            nameField.setText(String.valueOf(temp.getName()));
            invField.setText(String.valueOf(temp.getStock()));
            priceCostField.setText(String.valueOf(temp.getPrice()));
            maxField.setText(String.valueOf(temp.getMax()));
            minField.setText(String.valueOf(temp.getMin()));
        }
    }

    public void setSelectedPart() throws NullPointerException {
        if (inventoryPartsTableView != null) {
            selectedPart = (Part) inventoryPartsTableView.getSelectionModel().getSelectedItem();
        }
    }

    public void setSelectedAssociatedPart() throws NullPointerException {
        if (associatedPartsTableView != null) {
            selectedAssociatedPart = associatedPartsTableView.getSelectionModel().getSelectedItem();
        }
    }

    /*
Uses Stage.close() to close the AddProduct window.
*/
    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /*
    Calls closeWindow() when the user clicks the cancel button.
    */
    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    public void onAddAssociatedPartButton(ActionEvent actionEvent) {
        if (selectedPart != null) {
            temp.addAssociatedPart(selectedPart);
            associatedPartsTableView.setItems(temp.getAllAssociatedParts());
        }
    }

    public void onRemoveAssociatedPartButton(ActionEvent actionEvent) {
        if (selectedAssociatedPart != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Please confirm deletion of " + selectedAssociatedPart.getName() + ".");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(!result.isPresent()) {
                selectedAssociatedPart = null;
            }
            else if(result.get() == ButtonType.OK) {
                temp.deleteAssociatedPart(selectedAssociatedPart);
                associatedPartsTableView.setItems(temp.getAllAssociatedParts());
                selectedAssociatedPart = null;
            }
            else if(result.get() == ButtonType.CANCEL) {
                selectedAssociatedPart = null;
            }
        }
    }

    /*
    Retrieves text from TextFields and temporarily stores it in variables. These variables are used to create a Product.
    After the Product is added to allProducts, Inventory.productId is incremented and the window is closed.
    */
    public void onSaveProductButton(ActionEvent actionEvent) {
        temp.setName(nameField.getText());
        temp.setPrice(Double.parseDouble(priceCostField.getText()));
        temp.setStock(Integer.parseInt(invField.getText()));
        temp.setMin(Integer.parseInt(minField.getText()));
        temp.setMax(Integer.parseInt(maxField.getText()));
        Inventory.updateProduct(savedIndex, temp);
        closeWindow();
    }

    public void onInventoryPartsSearchTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (Inventory.intCheck(inventoryPartsSearchTextField.getText())) {
                int partInt = Integer.parseInt(inventoryPartsSearchTextField.getText());
                inventoryPartsTableView.setItems(Inventory.partById(Inventory.lookupPart(partInt)));
            }
            else {
                inventoryPartsTableView.setItems(Inventory.lookupPart(inventoryPartsSearchTextField.getText()));
            }
        }
    }

    public void onInventoryPartsTableViewClicked(MouseEvent mouseEvent) {
        setSelectedPart();
    }

    public void onAssociatedPartTableViewClicked(MouseEvent mouseEvent) {
        setSelectedAssociatedPart();
    }

}