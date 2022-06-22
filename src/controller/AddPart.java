package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static java.lang.Character.isAlphabetic;

/** The AddPart class is used to build InHouse and Outsourced objects and add them to allParts in the Inventory class.
*/
public class AddPart implements Initializable {
    /** Radio button to specify an InHouse Part.
     */
    public RadioButton inHouseRadio;
    /** Radio button to specify an Outsourced Part.
     */
    public RadioButton outsourcedRadio;
    /** TextFields for Part data.
     */
    public TextField idField, nameField, invField, priceCostField, maxField, minField, seventhArgField;
    /** The save and cancel buttons.
     */
    public Button saveButton, cancelButton;
    /** Label that is changed to reflect whether the inHouseRadio or the outsourcedRadio has been selected.
     */
    public Label seventhArgLabel;
    /** Temporary storage for the Part name.
     */
    private String name;
    /** Temporary storage for the Part price.
     */
    private double price;
    /** Temporary storage for the Part stock.
     */
    private int stock;
    /** Temporary storage for the Part minimum stock.
     */
    private int min;
    /** Temporary storage for the Part maximum stock.
     */
    private int max;
    /** Temporary storage for the Part machine ID.
     */
    private int machineId;
    /** Temporary storage for the Part manufacturer's company name.
     */
    private String companyName;

    /** On initialization, the inHouseRadio is set to selected.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inHouseRadio.setSelected(true);
    }

    /** Uses Stage.close() to close the AddPart window.
    */
    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /** Generates an Alert, and sets the text based on the field code entered.
     @param warningNumber int that correlates with the desired message.
    */
    public static void warnUserValidation(int warningNumber) {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        // name
        if (warningNumber == 0) {
            warning.setContentText("Name must begin with an alpha character.");
        }
        // price
        if (warningNumber == 1) {
            warning.setContentText("Price must be a decimal number.");
        }
        // stock
        if (warningNumber == 2) {
            warning.setContentText("Inv must be a whole number between min and max.");
        }
        // min
        if (warningNumber == 3) {
            warning.setContentText("Min must be a whole number, between 0 and max.");
        }
        // max
        if (warningNumber == 4) {
            warning.setContentText("Max must be a whole number, and greater than min.");
        }
        // machineId
        if (warningNumber == 5) {
            warning.setContentText("Machine ID can only contain numbers.");
        }
        if (warningNumber == 6) {
            warning.setContentText("Company name must begin with an alpha character.");
        }
        // companyName
        warning.show();
    }

    /** Saves validated data from the respective TextFields.
     @return true if all fields are validated, false if any are not.
     */
    public boolean validateFields() {
        if (Inventory.stringCheck(nameField.getText())) {
            name = nameField.getText();
        }
        else {
            warnUserValidation(0);
            return false;
        }
        if (Inventory.doubleCheck(priceCostField.getText())) {
            price = Double.parseDouble(priceCostField.getText());
        }
        else {
            warnUserValidation(1);
            return false;
        }
        if (Inventory.intCheck(invField.getText())) {
            stock = Integer.parseInt(invField.getText());
        }
        else {
            warnUserValidation(2);
            return false;
        }
        if (Inventory.intCheck(minField.getText())) {
            min = Integer.parseInt(minField.getText());
        }
        else {
            warnUserValidation(3);
            return false;
        }
        if (Inventory.intCheck(maxField.getText())) {
            max = Integer.parseInt(maxField.getText());
        }
        else {
            warnUserValidation(4);
            return false;
        }
        if (min > max) {
            warnUserValidation(4);
            return false;
        }
        if (stock > max || stock < min) {
            warnUserValidation(2);
            return false;
        }
        if (inHouseRadio.isSelected()) {
            if (Inventory.intCheck(seventhArgField.getText())) {
                machineId = Integer.parseInt(seventhArgField.getText());
            }
            else {
                warnUserValidation(5);
                return false;
            }
        }
        else if (outsourcedRadio.isSelected()) {
            if (Inventory.stringCheck(seventhArgField.getText())) {
                companyName = seventhArgField.getText();
            }
            else {
                warnUserValidation(6);
                return false;
            }
        }
        return true;
    }


    /** Checks if the inHouseRadio is selected and then uses class part data variables to create an appropriate part.
    After the Part is added to allParts, Inventory.partId is incremented and the window is closed.
    */
    public void onSaveButton(ActionEvent actionEvent) {
        boolean inHouse = inHouseRadio.isSelected();
        int id = Inventory.partId;
        if (validateFields()) {
            if (inHouse) {
                Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
            }
            else {
                Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
            }
            Inventory.incrementId(0);
            closeWindow();
        }
    }

    /** Calls closeWindow() when the user clicks the cancel button.
    */
    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    /** Checks if the inHouseRadio is selected and appropriately sets the label text.
    */
    public void onRadioSelect(ActionEvent actionEvent) {
        if (inHouseRadio.isSelected()) {
            seventhArgLabel.setText("Machine ID");
        }
        else {
            seventhArgLabel.setText("Company Name");
        }
    }
}
