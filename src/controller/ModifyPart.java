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

/*
The ModifyPart class is used to modify InHouse and Outsourced objects and save the modified Part at the
original index.
*/
public class ModifyPart implements Initializable {
    public RadioButton inHouseRadio, outsourcedRadio;
    public TextField idField, nameField, invField, priceCostField, maxField, minField, machineIdField;
    public Label inOutLabel;
    public Button saveButton, cancelButton;

    private InHouse inHousePart = null;
    private Outsourced outsourcedPart = null;

    private int savedIndex;

    // TODO validate input

    /*
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

    /*
    When the user clicks the saveButton the TextField data is saved in variables and then an appropriate
    subclass is created with that data. The index of the Part that was modified is used to save
    the modified Part to allParts.
    */
    public void onSaveButton(ActionEvent actionEvent) {
        savedIndex = Inventory.getIndex(Inventory.selectedPart);
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        double price = Double.parseDouble(priceCostField.getText());
        int inventory = Integer.parseInt(invField.getText());
        int min = Integer.parseInt(minField.getText());
        int max = Integer.parseInt(maxField.getText());
        if (inHouseRadio.isSelected()) {
            int machineId = Integer.parseInt(machineIdField.getText());
            InHouse modifiedPart = new InHouse(id, name, price, inventory, min, max, machineId);
            Inventory.updatePart(savedIndex, modifiedPart);
        }
        else {
            String companyName = machineIdField.getText();
            Outsourced modifiedPart = new Outsourced(id, name, price, inventory, min, max, companyName);
            Inventory.updatePart(savedIndex, modifiedPart);
        }
        closeWindow();
    }

    /*
    Calls closeWindow() when the user clicks the cancel button.
    */
    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    /*
    Uses Stage.close() to close the ModifyPart window and sets Inventory.selectedPart to null.
    */
    private void closeWindow() {
        Inventory.selectedPart = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /*
    Changes the label by calling setLabel() when the user selects a radio button.
    */
    public void onRadioSelect(ActionEvent actionEvent) {
        setLabel();
    }

    /*
    Changes the label text to reflect the selected radio button.
    */
    public void setLabel() {
        if (inHouseRadio.isSelected()) {
            inOutLabel.setText("Machine ID");
        }
        else {
            inOutLabel.setText("Company Name");
        }
    }

    /*
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
            machineIdField.setText(String.valueOf(inHousePart.getMachineId()));
        }
        else if (outsourcedPart != null){
            idField.setText(String.valueOf(outsourcedPart.getId()));
            nameField.setText(String.valueOf(outsourcedPart.getName()));
            invField.setText(String.valueOf(outsourcedPart.getStock()));
            priceCostField.setText(String.valueOf(outsourcedPart.getPrice()));
            maxField.setText(String.valueOf(outsourcedPart.getMax()));
            minField.setText(String.valueOf(outsourcedPart.getMin()));
            machineIdField.setText(String.valueOf(outsourcedPart.getCompanyName()));
        }
    }
}

