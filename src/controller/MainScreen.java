package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreen implements Initializable {
    public Button ExitButton;
    public AnchorPane PartsAnchorPane;
    public TableView PartsTableView;
    public TableColumn PartIdColumn;
    public TableColumn PartNameColumn;
    public TableColumn PartInventoryLevelColumn;
    public TableColumn PartPricePerUnitColumn;
    public Button AddPartButton;
    public Button DeletePartButton;
    public Button ModifyPartButton;
    public TextField SearchPartsTextField;
    public TableView ProductsTableView;
    public TableColumn ProductIdColumn;
    public TableColumn ProductNameColumn;
    public TableColumn ProductInventoryLevelColumn;
    public TableColumn ProductPricePerUnitColumn;
    public Button AddProductButton;
    public Button DeleteProductButton;
    public Button ModifyProductButton;
    public TextField SearchProductsTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main Screen active");
    }

    public void OnExitButton(ActionEvent actionEvent) {
        System.out.println("Exit button pressed");
        System.exit(0);
    }

    public void OnAddPartButton(ActionEvent actionEvent) {
        System.out.println("Add part button pressed");
    }

    public void OnDeletePartButton(ActionEvent actionEvent) {
        System.out.println("Delete part button pressed");
    }

    public void OnModifyPartButton(ActionEvent actionEvent) {
        System.out.println("Modify part button pressed");
    }

    public void OnAddProductButton(ActionEvent actionEvent) {
        System.out.println("Add product button pressed");
    }

    public void OnDeleteProductButton(ActionEvent actionEvent) {
        System.out.println("Delete product button pressed");
    }

    public void OnModifyProductButton(ActionEvent actionEvent) {
        System.out.println("Modify product button pressed");
    }

}
