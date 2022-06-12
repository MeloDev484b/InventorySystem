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
        partList.add(new Part(5689, "Screw", 7, 3.99));
        partList.add(new Part(1124, "Wedge", 3, 55.99));
        partList.add(new Part(7855, "Nut", 1, 8.99));
        partList.add(new Part(4567, "Bun", 69, 1.99));

        //Parts table columns
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("partName"));
        PartInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryLevel"));
        PartPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));


        ProductsTableView.setItems(productList);
        //Add products to productList
        productList.add(new Product(5689, "Branch", 17, 3.99));
        productList.add(new Product(1124, "Cheese Wheel", 77, 55.99));
        productList.add(new Product(7855, "Sandwich", 12, 8.99));
        productList.add(new Product(4567, "Shovel", 9, 1.99));

        //Products columns
        ProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        ProductInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("inventoryLevel"));
        ProductPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
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
