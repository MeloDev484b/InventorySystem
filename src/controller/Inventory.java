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
/*
The Inventory class is used to view and interact with the ObservableLists allParts and allProducts.
Objects in allParts and allProducts can be added, deleted, and modified by the user.
*/
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

    /*
    Initializes allParts and allProducts and sets the respective TableView to display the objects they contain.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Wrap allParts in a FilteredList
        FilteredList <Part> filteredParts = new FilteredList<>(allParts, p -> true);

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
    /*
    Uses ObservableList.add() to add a Part object to allParts.
    */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /*
    Uses ObservableList.add() to add a Product object to allProducts.
    */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /*
    Returns a Part contained in allParts that matches the supplied partId integer argument.
    */
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

    /*
    Returns a Product contained in allProducts that matches the supplied productId integer argument.
    */
    public Product lookupProduct(int productId) {
        // TODO 2. Change this
        ObservableList <Product> foundProducts = getAllProducts();
        for (Product product : foundProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /*
    Returns an ObservableList of Parts that match the partName.
    */
    public ObservableList<Part> lookupPart(String partName) {
        // TODO 3. Change this
        ObservableList <Part> allParts = getAllParts();
        ObservableList <Part> foundParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase(Locale.ROOT).contains(partName.toLowerCase(Locale.ROOT))) {
                foundParts.add(part);
            }
        }
        return foundParts;
    }

    /*
    Returns an ObservableList of Products that match the productName.
    */
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

    /*
    Uses ObservableList.set() to replace a Part in allParts, at the specified index.
    */
    public static void updatePart(int index, Part updatedPart) {
        allParts.set(index, updatedPart);
    }

    /*
    Uses ObservableList.set() to replace a Product in allProducts, at the specified index.
    */
    public static void updateProduct(int index, Product updatedProduct) {
        allProducts.set(index, updatedProduct);
    }

    /*
    Uses ObservableList.remove() to delete a Part in allParts. Returns true if the Part is deleted.
    */
    public static boolean deletePart(Part deleteMe) {
        return allParts.remove(deleteMe);
    }

    /*
    Uses ObservableList.remove() to delete a Product in allProducts. Returns true if the Product is deleted.
    */
    public static boolean deleteProduct(Product deleteMe) {
        return allProducts.remove(deleteMe);
    }

    /*
    Getter that returns the ObservableList allParts.
    */
    public ObservableList getAllParts() {
        return allParts;
    }

    /*
    Getter that returns the ObservableList allProducts.
    */
    public ObservableList getAllProducts() {
        return allProducts;
    }

    // Helper methods
    /*
    Uses ObservableList.indexOf() to return an integer index of the specified Part.
    */
    public static int getIndex(Part part) {
        return allParts.indexOf(part);
    }

    /*
    Uses ObservableList.get() to return a Part at the specified index.
    */
    public static Part getPart(int index) {
        return allParts.get(index);
    }

    /*
    Sets selectedPart to the Part that the user clicks in the PartsTableView.
    */
    public void setSelectedPart() {
        selectedPart = (Part) PartsTableView.getSelectionModel().getSelectedItem();
    }

    /*
    Sets selectedProduct to the Product that the user clicks in the PartsTableView.
    */
    public void setSelectedProduct() {
        selectedProduct = (Product) ProductsTableView.getSelectionModel().getSelectedItem();
    }

    // fxml methods
    /*
    Uses System.exit() to exit the application
    */
    public void onExitButton(ActionEvent actionEvent) {
        System.out.println("Exit button pressed");
        System.exit(0);
    }

    /*
    Loads and displays the AddPart GUI.
    */
    public void onAddPartButton(ActionEvent event) throws IOException {
        System.out.println("Add part button pressed");
        FXMLLoader loadAddPart = new FXMLLoader(getClass().getResource("/view/AddPart.fxml"));
        Parent root = loadAddPart.load();
        addPartScene = new Scene(root);
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    /*
    Calls deletePart() on the selectedPart when the user clicks the deletePartButton.
    */
    public void onDeletePartButton(ActionEvent actionEvent) {
        System.out.println("Delete part button pressed");
        deletePart(selectedPart);
    }

    /*
    Loads and displays the ModifyPart GUI.
    */
    public void onModifyPartButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Modify part button pressed");
        FXMLLoader loadModifyPart = new FXMLLoader(getClass().getResource("/view/ModifyPart.fxml"));
        Parent root = loadModifyPart.load();
        modifyPartScene = new Scene(root);
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();
    }

    /*
    Loads and displays the AddProduct GUI.
    */
    public void onAddProductButton(ActionEvent event) throws IOException{
        FXMLLoader loadAddProduct = new FXMLLoader(getClass().getResource("/view/AddProduct.fxml"));
        Parent root = loadAddProduct.load();
        modifyPartScene = new Scene(root);
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();
    }

    /*
    Calls setSelectedProduct() when the user clicks on the ProductsTableView.
    */
    public void onProductsTableViewClick(MouseEvent mouseEvent) {
        setSelectedProduct();
    }

    /*
    Calls setSelectedPart() when the user clicks on the PartsTableView.
    */
    public void onPartsTableViewClick(MouseEvent mouseEvent) {
        setSelectedPart();
    }

    /*
    Calls deleteProduct() on the selectedProduct when the user clicks the deleteProductButton.
    */
    public void onDeleteProductButton(ActionEvent actionEvent) {
        System.out.println("Delete product button pressed");

    }

    /*
    Calls modifyProduct() on the selectedProduct when the user clicks the modifyProductButton.
    */
    public void onModifyProductButton(ActionEvent actionEvent) {
        System.out.println("Modify product button pressed");
    }

    /*
    Searches allParts and displays allParts as a FilteredList. Not currently functional.
    */
    public void onSearchPartsButton(ActionEvent actionEvent) {
        //TODO 5. PartsTableView.setItems(searchedPartsList);
        if (SearchPartsTextField.getText().matches("^\\d+$")) {
            ObservableList <Part> temp = FXCollections.observableArrayList();
            temp.add(lookupPart(Integer.parseInt(SearchPartsTextField.getText())));
            PartsTableView.setItems(temp);
        }
        // TODO come back to this...we've got work to do
        else if (SearchPartsTextField.getText().matches(".*?")) {
            ObservableList <Part> foundParts = lookupPart(SearchPartsTextField.getText());
            for (Part part : allParts) {
                PartsTableView.getSelectionModel().select((allParts.indexOf(foundParts.get(getIndex(part)).getName())));
            }
        }
        else {
            System.out.println("Not found.");
            PartsTableView.setItems(allParts);
        }
    }

    /*
    Searches allProducts and displays allProducts as a FilteredList. Not currently functional.
    */
    public void onSearchProductsButton(ActionEvent actionEvent) {
        //TODO 6. ProductsTableView.setItems(searchedProductsList);
    }
}

