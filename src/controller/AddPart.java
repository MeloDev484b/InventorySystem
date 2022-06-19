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

/*
The AddPart class is used to build InHouse and Outsourced objects and add them to allParts in the Inventory class.
*/
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

    /*
    Retrieves text from TextFields and temporarily stores it in variables. These variables are used to create either
    an inHouse or Outsourced object. After the Part is added to allParts, the window is closed.
    */
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
        Stage stage = (Stage) CancelButton.getScene().getWindow();
        stage.close();
    }

    /*
    Changes the label text to reflect the selected radio button.
    */
    public void onRadioSelect(ActionEvent actionEvent) {
        if (InHouseRadio.isSelected()) {
            InOutLabel.setText("Machine ID");
        }
        else {
            InOutLabel.setText("Company Name");
        }
    }
}
