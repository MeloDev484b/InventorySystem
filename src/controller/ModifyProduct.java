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
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private Part selectedPart = null;
    private Part selectedAssociatedPart = null;
    private Product temp;
    private int savedIndex;
    /*
    Initializes ModifyProduct by saving the index of the selectedProduct, saves the selectedProduct in a temp
    Product variable (to ensure the original product is not edited before confirmation), productIntake() is called
    to process the selectedProduct data, and the TableViews are set to the appropriate data and their columns are
    initialized.
    */
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

    /*
    Ensures the temp Product has been initialized and if it has, ModifyProduct's TextFields are set to the values
    that the temp Product holds.
    */
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

    /*
    If the TableView that is generated from Inventory's allParts list is not null, the selectedPart is set to
    the selected item in the inventoryPartsTableView.
    */
    public void setSelectedPart() throws NullPointerException {
        if (inventoryPartsTableView != null) {
            selectedPart = (Part) inventoryPartsTableView.getSelectionModel().getSelectedItem();
        }
    }

    /*
    If the TableView that is generated from temp's associatedParts list is not null, the selectedAssociatedPart
    is set to the selected item in the associatedPartsTableView.
    */
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
    Returns true if all fields pass validation, or false if any of the fields fail validation.
    If the field data passes validation it is saved to the class variables that temporarily hold part field data.
    */
    public boolean validateFields() {
        if (Inventory.stringCheck(nameField.getText())) {
            name = nameField.getText();
        }
        else {
            AddPart.warnUserValidation(0);
            return false;
        }
        if (Inventory.doubleCheck(priceCostField.getText())) {
            price = Double.parseDouble(priceCostField.getText());
        }
        else {
            AddPart.warnUserValidation(1);
            return false;
        }
        if (Inventory.intCheck(invField.getText())) {
            stock = Integer.parseInt(invField.getText());
        }
        else {
            AddPart.warnUserValidation(2);
            return false;
        }
        if (Inventory.intCheck(minField.getText())) {
            min = Integer.parseInt(minField.getText());
        }
        else {
            AddPart.warnUserValidation(3);
            return false;
        }
        if (Inventory.intCheck(maxField.getText())) {
            max = Integer.parseInt(maxField.getText());
        }
        else {
            AddPart.warnUserValidation(4);
            return false;
        }
        if (min > max) {
            AddPart.warnUserValidation(4);
            return false;
        }
        if (stock > max || stock < min) {
            AddPart.warnUserValidation(2);
            return false;
        }
        return true;
    }

    /*
    Calls closeWindow() when the user clicks the cancel button.
    */
    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    /*
    Checks to ensure the user has selected a part from the inventoryPartsTableView and, if they have,
    it is added to temp's associatedParts list. The associatedPartsTableView is then reset to the current
    associatedParts list.
    If the user has not selected a part, they are warned that they must select a part before attempting to add it
    to the associatedPartsList.
    */
    public void onAddAssociatedPartButton(ActionEvent actionEvent) {
        if (selectedPart != null) {
            temp.addAssociatedPart(selectedPart);
            associatedPartsTableView.setItems(temp.getAllAssociatedParts());
        }
        else {
            Alert addPartWarning = new Alert(Alert.AlertType.WARNING);
            addPartWarning.setContentText("Please select an part to add.");
            addPartWarning.show();
        }
    }

    /*
    Checks to ensure the user has selected a part from the associatedPartsTableView and, if they have,
    the user is prompted to confirm the removal from temp's associatedParts list.
    If the user confirms removal, the part is removed from the associatedParts list and associatedPartsTableView
    is then reset to the current associatedParts list.
    If the user has not selected a part, they are warned that they must select a part before attempting to add it
    to the associatedPartsList.
    */
    public void onRemoveAssociatedPartButton(ActionEvent actionEvent) {
        if (selectedAssociatedPart != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Please confirm removal of " + selectedAssociatedPart.getName() + ".");
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
        else {
            Alert deleteAssociatedPartWarning = new Alert(Alert.AlertType.WARNING);
            deleteAssociatedPartWarning.setContentText("Please select an associated part to remove.");
            deleteAssociatedPartWarning.show();
        }
    }

    /*
    Retrieves text from TextFields and temporarily stores it in variables. These variables are used to create a Product.
    After the Product is added to allProducts, Inventory.productId is incremented and the window is closed.
    */
    public void onSaveProductButton(ActionEvent actionEvent) {
        if (validateFields()) {
            temp.setName(nameField.getText());
            temp.setPrice(Double.parseDouble(priceCostField.getText()));
            temp.setStock(Integer.parseInt(invField.getText()));
            temp.setMin(Integer.parseInt(minField.getText()));
            temp.setMax(Integer.parseInt(maxField.getText()));
            Inventory.updateProduct(savedIndex, temp);
            closeWindow();
        }
    }

    /*
    If the user presses the Enter key or the TextField is left empty, an appropriate search is performed on the
    contents of the TextField. The contents are first checked if it contains an integer. If the contents of the
    field is an integer, the inventory parts are searched for a matching ID. If the contents are a String,
    the inventory is searched for a matching name.
    */
    public void onInventoryPartsSearchTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || inventoryPartsSearchTextField.getText() == "") {
            if (Inventory.intCheck(inventoryPartsSearchTextField.getText())) {
                int partInt = Integer.parseInt(inventoryPartsSearchTextField.getText());
                inventoryPartsTableView.setItems(Inventory.partById(Inventory.lookupPart(partInt)));
            }
            else {
                inventoryPartsTableView.setItems(Inventory.lookupPart(inventoryPartsSearchTextField.getText()));
            }
        }
    }

    /*
    Calls setSelectedPart() when the user clicks on the inventoryPartsTableView.
     */
    public void onInventoryPartsTableViewClicked(MouseEvent mouseEvent) {
        setSelectedPart();
    }

    /*
    Calls setSelectedAssociatedPart when the user clicks on the associatedPartTableView.
     */
    public void onAssociatedPartTableViewClicked(MouseEvent mouseEvent) {
        setSelectedAssociatedPart();
    }

}