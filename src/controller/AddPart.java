package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.InHouse;
import model.Outsourced;
import model.Part;


import javax.naming.Name;
import java.net.URL;
import java.util.ResourceBundle;

public class AddPart implements Initializable {
    public RadioButton InHouseRadio;
    public RadioButton OutsourcedRadio;
    public TextField IdField, NameField, InvField, PriceCostField, MaxField, MinField, MachineIdField;
    public Button SaveButton, CancelButton;
    public Label InOutLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add part active");
    }

    public void onSaveButton(ActionEvent actionEvent) {
        boolean inHouse = InHouseRadio.isSelected();
        boolean outsourced = OutsourcedRadio.isSelected();
        int id = Integer.parseInt(IdField.getText()); // TODO ID generation function
        String name = NameField.getText();
        double price = Double.parseDouble(PriceCostField.getText());
        int stock = Integer.parseInt(InvField.getText());
        int min = Integer.parseInt(MinField.getText());
        int max = Integer.parseInt(MaxField.getText());

        if (inHouse) {
            int machineId = Integer.parseInt(InOutLabel.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
        }
        else {
            String companyName = InOutLabel.getText();
            Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
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
        if (InHouseRadio.isSelected()) {
            InOutLabel.setText("Machine ID");
        }
        else {
            InOutLabel.setText("Company Name");
        }
    }
}
