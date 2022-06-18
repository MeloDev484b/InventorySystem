package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Modify part active");
        partIntake(Inventory.isInHouse);
    }

    public void onSaveButton(ActionEvent actionEvent) {
    }

    public void onCancelButton(ActionEvent actionEvent) {
    }

    public void onRadioSelect(ActionEvent actionEvent) {
        if (InHouseRadio.isSelected()) {
            InOutLabel.setText("Machine ID");
        }
        else {
            InOutLabel.setText("Company Name");
        }
    }
    public void partIntake(boolean partSourceIn) {
        if (partSourceIn) {
            IdField.setText(String.valueOf(Inventory.tempIn.getId()));
            NameField.setText(String.valueOf(Inventory.tempIn.getName()));
            InvField.setText(String.valueOf(Inventory.tempIn.getStock()));
            PriceCostField.setText(String.valueOf(Inventory.tempIn.getPrice()));
            MaxField.setText(String.valueOf(Inventory.tempIn.getMax()));
            MinField.setText(String.valueOf(Inventory.tempIn.getMin()));
            MachineIdField.setText(String.valueOf(Inventory.tempIn.getMachineId()));
        }
        else {
            IdField.setText(String.valueOf(Inventory.tempOut.getId()));
            NameField.setText(String.valueOf(Inventory.tempOut.getName()));
            InvField.setText(String.valueOf(Inventory.tempOut.getStock()));
            PriceCostField.setText(String.valueOf(Inventory.tempOut.getPrice()));
            MaxField.setText(String.valueOf(Inventory.tempOut.getMax()));
            MinField.setText(String.valueOf(Inventory.tempOut.getMin()));
            MachineIdField.setText(String.valueOf(Inventory.tempOut.getCompanyName()));
        }
    }

}
