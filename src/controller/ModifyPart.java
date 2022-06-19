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
import model.Part;

import java.net.URL;
import java.util.ResourceBundle;

/*
The ModifyPart class is used to modify InHouse and Outsourced objects and save the modified Part at the
original index.
*/
public class ModifyPart implements Initializable {
    public RadioButton InHouseRadio, OutsourcedRadio;
    public TextField IdField, NameField, InvField, PriceCostField, MaxField, MinField, MachineIdField;
    public Label InOutLabel;
    public Button SaveButton, CancelButton;

    private InHouse inHousePart = null;
    private Outsourced outsourcedPart = null;

    private int savedIndex;

    /*
    Checks the subclass of the Part to be modified and checks the appropriate radio button. Stores the selectedPart
    from Inventory in the respective Part variable. The label for the last argument is set to the appropriate text
    by calling setLabel(), then partIntake() is called to fill the TextFields with the Part's data.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Modify part active");
        if (Inventory.selectedPart.getClass() == InHouse.class) {
            InHouseRadio.setSelected(true);
            inHousePart = (InHouse) Inventory.selectedPart;
        }
        else {
            OutsourcedRadio.setSelected(true);
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
        int id = Integer.parseInt(IdField.getText());
        String name = NameField.getText();
        double price = Double.parseDouble(PriceCostField.getText());
        int inventory = Integer.parseInt(InvField.getText());
        int min = Integer.parseInt(MinField.getText());
        int max = Integer.parseInt(MaxField.getText());
        if (InHouseRadio.isSelected()) {
            int machineId = Integer.parseInt(MachineIdField.getText());
            InHouse modifiedPart = new InHouse(id, name, price, inventory, min, max, machineId);
            Inventory.updatePart(savedIndex, modifiedPart);
        }
        else {
            String companyName = MachineIdField.getText();
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
    Uses Stage.close() to close the ModifyPart window.
    */
    private void closeWindow() {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
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
        if (InHouseRadio.isSelected()) {
            InOutLabel.setText("Machine ID");
        }
        else {
            InOutLabel.setText("Company Name");
        }
    }

    /*
    Sets the TextFields to the appropriate text from the Part stored in either InhousePart or OutsourcedPart.
    */
    public void partIntake() {

        if (inHousePart != null) {
            IdField.setText(String.valueOf(inHousePart.getId()));
            NameField.setText(String.valueOf(inHousePart.getName()));
            InvField.setText(String.valueOf(inHousePart.getStock()));
            PriceCostField.setText(String.valueOf(inHousePart.getPrice()));
            MaxField.setText(String.valueOf(inHousePart.getMax()));
            MinField.setText(String.valueOf(inHousePart.getMin()));
            MachineIdField.setText(String.valueOf(inHousePart.getMachineId()));
        }
        else if (outsourcedPart != null){
            IdField.setText(String.valueOf(outsourcedPart.getId()));
            NameField.setText(String.valueOf(outsourcedPart.getName()));
            InvField.setText(String.valueOf(outsourcedPart.getStock()));
            PriceCostField.setText(String.valueOf(outsourcedPart.getPrice()));
            MaxField.setText(String.valueOf(outsourcedPart.getMax()));
            MinField.setText(String.valueOf(outsourcedPart.getMin()));
            MachineIdField.setText(String.valueOf(outsourcedPart.getCompanyName()));
        }
    }
}

