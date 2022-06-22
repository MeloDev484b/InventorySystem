package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;

import java.net.URL;
import java.util.ResourceBundle;

/**
The ModifyPart class is used to modify InHouse and Outsourced objects and save the modified Part at the
original index.
*/
public class ModifyPart implements Initializable {
    public RadioButton inHouseRadio, outsourcedRadio;
    public TextField idField, nameField, invField, priceCostField, maxField, minField, seventhArgField;
    public Label seventhArgLabel;
    public Button saveButton, cancelButton;
    private InHouse inHousePart = null;
    private Outsourced outsourcedPart = null;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private int machineId;
    private String companyName;
    private int savedIndex;

    /**
    Checks the subclass of the Part to be modified and checks the appropriate radio button. Stores the selectedPart
    from Inventory in the respective Part variable. The label for the last argument is set to the appropriate text
    by calling setLabel(), then partIntake() is called to fill the TextFields with the Part's data.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Modify part active");
        if (Inventory.selectedPart.getClass() == InHouse.class) {
            inHouseRadio.setSelected(true);
            inHousePart = (InHouse) Inventory.selectedPart;
        }
        else {
            outsourcedRadio.setSelected(true);
            outsourcedPart = (Outsourced) Inventory.selectedPart;
        }
        setLabel();
        partIntake();
    }

    /**
    When the user clicks the saveButton the TextField data is saved in variables and then an appropriate
    subclass is created with that data. The inHouseRadio button is checked to see if it is selected, if it is
    selected, an InHouse part is created with the appropriate arguments, and if it is not, an Outsourced part
    is created. The index of the Part that is being modified is used to save the modified Part to allParts.
    */
    public void onSaveButton(ActionEvent actionEvent) {
        if (validateFields()) {
            savedIndex = Inventory.getIndex(Inventory.selectedPart);
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double price = Double.parseDouble(priceCostField.getText());
            int inventory = Integer.parseInt(invField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            if (inHouseRadio.isSelected()) {
                int machineId = Integer.parseInt(seventhArgField.getText());
                InHouse modifiedPart = new InHouse(id, name, price, inventory, min, max, machineId);
                Inventory.updatePart(savedIndex, modifiedPart);
            }
            else {
                String companyName = seventhArgField.getText();
                Outsourced modifiedPart = new Outsourced(id, name, price, inventory, min, max, companyName);
                Inventory.updatePart(savedIndex, modifiedPart);
            }
            closeWindow();
        }
    }

    /**
    Calls closeWindow() when the user clicks the cancel button.
    */
    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    /**
    Uses Stage.close() to close the ModifyPart window and sets Inventory.selectedPart to null.
    */
    private void closeWindow() {
        Inventory.selectedPart = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
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
        if (inHouseRadio.isSelected()) {
            if (Inventory.intCheck(seventhArgField.getText())) {
                machineId = Integer.parseInt(seventhArgField.getText());
            }
            else {
                AddPart.warnUserValidation(5);
                return false;
            }
        }
        else if (outsourcedRadio.isSelected()) {
            if (Inventory.stringCheck(seventhArgField.getText())) {
                companyName = seventhArgField.getText();
            }
            else {
                AddPart.warnUserValidation(6);
                return false;
            }
        }
        return true;
    }

    /**
    Changes the label by calling setLabel() when the user selects a radio button.
    */
    public void onRadioSelect(ActionEvent actionEvent) {
        setLabel();
    }

    /**
    Changes the label text to reflect the selected radio button.
    */
    public void setLabel() {
        if (inHouseRadio.isSelected()) {
            seventhArgLabel.setText("Machine ID");
        }
        else {
            seventhArgLabel.setText("Company Name");
        }
    }

    /**
    Sets the TextFields to the appropriate text from the Part stored in either InhousePart or OutsourcedPart.
    */
    public void partIntake() {

        if (inHousePart != null) {
            idField.setText(String.valueOf(inHousePart.getId()));
            nameField.setText(String.valueOf(inHousePart.getName()));
            invField.setText(String.valueOf(inHousePart.getStock()));
            priceCostField.setText(String.valueOf(inHousePart.getPrice()));
            maxField.setText(String.valueOf(inHousePart.getMax()));
            minField.setText(String.valueOf(inHousePart.getMin()));
            seventhArgField.setText(String.valueOf(inHousePart.getMachineId()));
        }
        else if (outsourcedPart != null){
            idField.setText(String.valueOf(outsourcedPart.getId()));
            nameField.setText(String.valueOf(outsourcedPart.getName()));
            invField.setText(String.valueOf(outsourcedPart.getStock()));
            priceCostField.setText(String.valueOf(outsourcedPart.getPrice()));
            maxField.setText(String.valueOf(outsourcedPart.getMax()));
            minField.setText(String.valueOf(outsourcedPart.getMin()));
            seventhArgField.setText(String.valueOf(outsourcedPart.getCompanyName()));
        }
    }
}

