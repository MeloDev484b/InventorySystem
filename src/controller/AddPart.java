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
    public TextField IdField;
    public TextField NameField;
    public TextField InvField;
    public TextField PriceCostField;
    public TextField MaxField;
    public TextField MinField;
    public TextField MachineIdField;

    public Button SaveButton;
    public Button CancelButton;
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
            InHouse newPart = new InHouse(id, name, price, stock, min, max, machineId);
            Inventory.addPart(newPart);
        }
        else {
            String companyName = InOutLabel.getText();
            Outsourced newPart = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.addPart(newPart);
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
