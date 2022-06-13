package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Part;
import model.Product;

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

    private ObservableList <Part> partList = FXCollections.observableArrayList();

    private ObservableList <Product> productList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main Screen active");
        PartsTableView.setItems(partList);
        //Add parts to partList
        partList.add(new Part(5689, "Screw", 7.99, 10, 1, 20));
        partList.add(new Part(1124, "Wedge", 3.88, 15, 1,20));
        partList.add(new Part(7855, "Nut", 1.69, 8, 1, 20));
        partList.add(new Part(4567, "Bun", 69.80, 1, 1, 20));

        //Parts table columns
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        ProductsTableView.setItems(productList);
        //Add products to productList
        productList.add(new Product(5689, "Branch", 17.99, 19, 1, 20));
        productList.add(new Product(1124, "Cheese Wheel", 77.45, 17, 1, 20));
        productList.add(new Product(7855, "Sandwich", 12.44, 9, 1, 20));
        productList.add(new Product(4567, "Shovel", 9.21, 2, 1, 20));

        //Products columns
        ProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProductInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

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
