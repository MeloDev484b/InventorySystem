package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.util.Locale;
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

    // TODO last task: add try/catch, validate input, comment blocks everywhere
    // UML-specified methods
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    public Part lookupPart(int partId) {
        // TODO 1. Test this
        ObservableList <Part> foundParts = getAllParts();
        for (Part part : foundParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        return null;
    }
    public Product lookupProduct(int productId) {
        // TODO 2. Test this
        ObservableList <Product> foundProducts = getAllProducts();
        for (Product product : foundProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
    public ObservableList<Part> lookupPart(String partName) {
        // TODO 3. Test this
        ObservableList <Part> allParts = getAllParts();
        ObservableList <Part> foundParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase(Locale.ROOT).contains(partName.toLowerCase(Locale.ROOT))) {
                foundParts.add(part);
            }
        }
        return foundParts;
    }
    public ObservableList<Product> lookupProduct(String productName) {
        // TODO 4. Test this
        ObservableList <Product> foundProducts = getAllProducts();
        for (Product product : foundProducts) {
            if (product.getName() != productName) {
                foundProducts.remove(product);
            }
        }
        return foundProducts;
    }
    public static void updatePart(int index, Part updatedPart) {
        allParts.set(index, updatedPart);
    }
    public static void updateProduct(int index, Product updatedProduct) {
        allProducts.set(index, updatedProduct);
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

    // Helper methods
    public static int getIndex(Part part) {
        return allParts.indexOf(part);
    }
    public static Part getPart(int index) {
        return allParts.get(index);
    }
    public void setSelectedPart() {
        selectedPart = (Part) PartsTableView.getSelectionModel().getSelectedItem();
    }
    public void setSelectedProduct() {
        selectedProduct = (Product) ProductsTableView.getSelectionModel().getSelectedItem();
    }

    // fxml methods
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
        FXMLLoader loadAddProduct = new FXMLLoader(getClass().getResource("/view/AddProduct.fxml"));
        Parent root = loadAddProduct.load();
        modifyPartScene = new Scene(root);
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();
    }
    public void onProductsTableViewClick(MouseEvent mouseEvent) {
        setSelectedProduct();
    }
    public void onPartsTableViewClick(MouseEvent mouseEvent) {
        setSelectedPart();
    }
    public void onDeleteProductButton(ActionEvent actionEvent) {
        System.out.println("Delete product button pressed");

    }
    public void onModifyProductButton(ActionEvent actionEvent) {
        System.out.println("Modify product button pressed");
    }

    public void onSearchPartsButton(ActionEvent actionEvent) {
        //TODO 5. PartsTableView.setItems(searchedPartsList);
        if (SearchPartsTextField.getText().matches("\\d+")) {
            ObservableList <Part> temp = FXCollections.observableArrayList();
            temp.add(lookupPart(Integer.parseInt(SearchPartsTextField.getText())));
            PartsTableView.setItems(temp);
        }
        else if (SearchPartsTextField.getText().matches(".")) {
            ObservableList <Part> foundParts = lookupPart(SearchPartsTextField.getText());
            PartsTableView.setItems(foundParts);
        }
        else {
            System.out.println("Not found.");
            PartsTableView.setItems(allParts);
        }
    }

    public void onSearchProductsButton(ActionEvent actionEvent) {
        //TODO 6. ProductsTableView.setItems(searchedProductsList);
    }
}

