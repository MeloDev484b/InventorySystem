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

public class ModifyPart implements Initializable {
    public RadioButton InHouseRadio, OutsourcedRadio;
    public TextField IdField, NameField, InvField, PriceCostField, MaxField, MinField, MachineIdField;
    public Label InOutLabel;
    public Button SaveButton, CancelButton;

    private InHouse inHousePart = null;
    private Outsourced outsourcedPart = null;

    private int savedIndex;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Modify part active");
        if (Inventory.selectedPart.getClass() == InHouse.class) {
            InHouseRadio.setSelected(true);
            inHousePart = (InHouse) Inventory.selectedPart;
            outsourcedPart = null;
        }
        else {
            OutsourcedRadio.setSelected(true);
            outsourcedPart = (Outsourced) Inventory.selectedPart;
            inHousePart = null;
        }
        setLabel();
        partIntake();
    }

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

    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    public void onRadioSelect(ActionEvent actionEvent) {
        setLabel();
    }

    public void setLabel() {
        if (InHouseRadio.isSelected()) {
            InOutLabel.setText("Machine ID");
        }
        else {
            InOutLabel.setText("Company Name");
        }
    }
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

