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

// TODO validate input

/*
The AddPart class is used to build InHouse and Outsourced objects and add them to allParts in the Inventory class.
*/
public class AddPart implements Initializable {
    public RadioButton inHouseRadio;
    public RadioButton outsourcedRadio;
    public TextField idField, nameField, invField, priceCostField, maxField, minField, machineIdField;
    public Button saveButton, cancelButton;
    public Label inOutLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Add part active");
    }

    /*
    Retrieves text from TextFields and temporarily stores it in variables. These variables are used to create either
    an inHouse or Outsourced object. After the Part is added to allParts, Inventory.partId is incremented and the window is closed.
    */
    public void onSaveButton(ActionEvent actionEvent) {
        boolean inHouse = inHouseRadio.isSelected();
        boolean outsourced = outsourcedRadio.isSelected();
        int id = Inventory.partId;
        String name = nameField.getText();
        double price = Double.parseDouble(priceCostField.getText());
        int stock = Integer.parseInt(invField.getText());
        int min = Integer.parseInt(minField.getText());
        int max = Integer.parseInt(maxField.getText());

        if (inHouse) {
            int machineId = Integer.parseInt(inOutLabel.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineId));
        }
        else {
            String companyName = inOutLabel.getText();
            Inventory.addPart(new Outsourced(id, name, price, stock, min, max, companyName));
        }
        Inventory.incrementId(0);
        closeWindow();
    }

    /*
    Calls closeWindow() when the user clicks the cancel button.
    */
    public void onCancelButton(ActionEvent actionEvent) {
        closeWindow();
    }

    /*
    Uses Stage.close() to close the AddPart window.
    */
    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /*
    Changes the label text to reflect the selected radio button.
    */
    public void onRadioSelect(ActionEvent actionEvent) {
        if (inHouseRadio.isSelected()) {
            inOutLabel.setText("Machine ID");
        }
        else {
            inOutLabel.setText("Company Name");
        }
    }
}
