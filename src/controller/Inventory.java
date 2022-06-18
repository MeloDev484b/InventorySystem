package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Part;
import model.Product;
import model.InHouse;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Inventory implements Initializable {
    // set stage for add parts
    Stage addPartStage = new Stage();
    Scene addPartScene;
    // set stage for modify parts
    Stage modifyPartStage = new Stage();
    Scene modifyPartScene;
    public Button ExitButton;
    public AnchorPane PartsAnchorPane;
    public TableView PartsTableView;
    public TableColumn PartIdColumn, PartNameColumn, PartInventoryLevelColumn, PartPricePerUnitColumn;
    public Button AddPartButton, DeletePartButton, ModifyPartButton;
    public TextField SearchPartsTextField;
    public TableView ProductsTableView;
    public TableColumn ProductIdColumn, ProductNameColumn, ProductInventoryLevelColumn, ProductPricePerUnitColumn;
    public Button AddProductButton, DeleteProductButton, ModifyProductButton;
    public TextField SearchProductsTextField;

    private static ObservableList <Part> allParts = FXCollections.observableArrayList();

    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();

    public static Part selectedPart;
    public static Product selectedProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main Screen active");
        PartsTableView.setItems(allParts);
        //Add parts to allParts
        allParts.add(new InHouse(5689, "Screw", 7.99, 10, 1, 20, 6480));
        allParts.add(new InHouse(1124, "Wedge", 3.88, 15, 1,20, 9785));
        allParts.add(new Outsourced(7855, "Nut", 1.69, 8, 1, 20, "A Company"));
        allParts.add(new Outsourced(4567, "Bun", 69.80, 1, 1, 20, "Generic Cola"));

        //Parts table columns
        PartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        PartInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        ProductsTableView.setItems(allProducts);
        //Add products to allProducts
        allProducts.add(new Product(3456, "Branch", 17.99, 19, 1, 20));
        allProducts.add(new Product(3466, "Cheese Wheel", 77.45, 17, 1, 20));
        allProducts.add(new Product(7688, "Sandwich", 12.44, 9, 1, 20));
        allProducts.add(new Product(9240, "Shovel", 9.21, 2, 1, 20));

        //Products columns
        ProductIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ProductInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }

    public void onExitButton(ActionEvent actionEvent) {
        System.out.println("Exit button pressed");
        System.exit(0);
    }


    public void onAddPartButton(ActionEvent event) throws IOException {
        System.out.println("Add part button pressed");
        FXMLLoader loadAddPart = new FXMLLoader(getClass().getResource("/view/AddPart.fxml"));
        Parent root = loadAddPart.load();
        addPartScene = new Scene(root);
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    public void onDeletePartButton(ActionEvent actionEvent) {
        System.out.println("Delete part button pressed");
        deletePart(selectedPart);
    }

    public void onModifyPartButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Modify part button pressed");
        FXMLLoader loadModifyPart = new FXMLLoader(getClass().getResource("/view/ModifyPart.fxml"));
        Parent root = loadModifyPart.load();
        modifyPartScene = new Scene(root);
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();
    }

    public void onAddProductButton(ActionEvent event) throws IOException{
        // TODO make this button open AddProduct.fxml
    }

    public void onDeleteProductButton(ActionEvent actionEvent) {
        System.out.println("Delete product button pressed");

    }

    public void onModifyProductButton(ActionEvent actionEvent) {
        System.out.println("Modify product button pressed");
    }

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static void lookupPart(int partId) {
        ObservableList <Part> searchedPart = FXCollections.observableArrayList();

    }

    public static void lookupProducts(int productId) {
        // TODO returns Product from int productId
    }

    public static void lookupPart(String partName) {
        // TODO returns ObservableList <Part> from String name
    }

    public static void lookupProducts(String productName) {
        // TODO returns ObservableList <Product> from String name
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        // TODO update Product
    }

    public static boolean deletePart(Part deleteMe) {
        return allParts.remove(deleteMe);
    }

    public static boolean deleteProduct(Product deleteMe) {
        return allProducts.remove(deleteMe);
    }

    public ObservableList getAllParts() {
        return allParts;
    }
    public ObservableList getAllProducts() {
        return allProducts;
    }

    public void onSearchPartsTextChanged(InputMethodEvent inputMethodEvent) {

    }

    public void onSearchProductsTextChanged(InputMethodEvent inputMethodEvent) {
    }

    public static int getIndex(Part part) {
        return allParts.indexOf(part);
    }
    public static Part getPart(int index) {
        return allParts.get(index);
    }
    public void setSelectedPart() {
        selectedPart = (Part) PartsTableView.getSelectionModel().getSelectedItem();
    }

    public void onPartsTableViewClick(MouseEvent mouseEvent) {
        setSelectedPart();
    }
}
