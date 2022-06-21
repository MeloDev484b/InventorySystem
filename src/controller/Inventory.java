package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    // set stage for add products
    Stage addProductStage = new Stage();
    Scene addProductScene;
    // set stage for modify products
    Stage modifyProductStage = new Stage();
    Scene modifyProductScene;
    // set stage for delete warning
    Stage confirmDeleteStage = new Stage();
    Scene confirmDeleteScene;
    // fxml objects
    public CheckBox idSearchCheckBox;
    public Button exitButton;
    public AnchorPane partsAnchorPane;
    public TableView partsTableView, productsTableView;
    public TableColumn partIdColumn, partNameColumn, partInventoryLevelColumn, partPricePerUnitColumn;
    public Button addPartButton, deletePartButton, modifyPartButton;
    public TextField searchPartsTextField, searchProductsTextField;
    public TableColumn productIdColumn, productNameColumn, productInventoryLevelColumn, productPricePerUnitColumn;
    public Button addProductButton, deleteProductButton, modifyProductButton;

    private static ObservableList <Part> allParts = FXCollections.observableArrayList();
    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();

    public static boolean delete = false;
    public static Part selectedPart = null;
    public static Product selectedProduct = null;
    public static int partId = 1000;
    public static int productId = 1000;

    /*
    Initializes allParts and allProducts and sets the respective TableView to display the objects they contain.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Main Screen active");
        partsTableView.setItems(allParts);
        productsTableView.setItems(allProducts);

        //Set Parts table columns
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //Set Products columns
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPricePerUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productInventoryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //Add test Parts to allParts
        allParts.add(new InHouse(5689, "Screw", 7.99, 10, 1, 20, 6480));
        allParts.add(new InHouse(1124, "Wedge", 3.88, 15, 1,20, 9785));
        allParts.add(new Outsourced(7855, "Nut", 1.69, 8, 1, 20, "A Company"));
        allParts.add(new Outsourced(4567, "Bun", 69.80, 1, 1, 20, "Generic Cola"));

        //Add test Products to allProducts
        allProducts.add(new Product(3456, "Branch", 17.99, 19, 1, 20));
        allProducts.add(new Product(3466, "Cheese Wheel", 77.45, 17, 1, 20));
        allProducts.add(new Product(7688, "Sandwich", 12.44, 9, 1, 20));
        allProducts.add(new Product(9240, "Shovel", 9.21, 2, 1, 20));

    }

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
    public static Part lookupPart(int partId) {
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
        ObservableList <Product> foundProducts = getAllProducts();
        for (Product product : foundProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    /*
    Checks allParts for a partName that matches the Part.getName() and adds it to the partsByName list.
    If no Parts match, the original allParts list is returned and the user is notified that the searched part
    does not exist.
    */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsByName = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().equalsIgnoreCase(partName)) {
                partsByName.add(part);
            }
        }
        if (partsByName.size() > 0) {
            return partsByName;
        }
        else {
            return allParts;
        }
    }

    /*
    Checks allProducts for a ProductName that matches the Product.getName() and adds it to the ProductsByName list.
    If no Products match, the original allProducts list is returned and the user is notified that the searched Product
    does not exist.
    */
    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsByName = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().equalsIgnoreCase(productName)) {
                productsByName.add(product);
            }
        }
        if (productsByName.size() > 0) {
            return productsByName;
        }
        else {
            return allProducts;
        }
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
    public static ObservableList getAllParts() {
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
    Checks allParts for a partId that matches the Part.getId() and adds it to the partsById list.
    If no Parts match, the original allParts list is returned and the user is notified that the searched part
    does not exist.
    */
    public static ObservableList<Part> partById(Part partToId) {
        if (partToId != null) {
            ObservableList<Part> partById = FXCollections.observableArrayList();
            for (Part part : allParts) {
                if (part.equals(partToId)) {
                    partById.add(part);
                }
            }
            if (partById.size() > 0) {
                return partById;
            }
            else {
                return allParts;
            }
        }
        else {
            return allParts;
        }
    }

    /*
    Checks allProducts for a productId that matches the Product.getId() and adds it to the productsById list.
    If no Products match, the original allProducts list is returned and the user is notified that the searched product
    does not exist.
    */
    public ObservableList<Product> productById(Product productToId) {
        if (productToId != null) {
            ObservableList<Product> productById = FXCollections.observableArrayList();
            for (Product product : allProducts) {
                if (product.equals(productToId)) {
                    productById.add(product);
                }
            }
            if (productById.size() > 0) {
                return productById;
            }
            else {
                return allProducts;
            }
        }
        else {
            return allProducts;
        }
    }

    /*
    When supplied int value of 0, partId is incremented and returned, when supplied an int value of 1
    productId is incremented and returned.
    */
    public static int incrementId(int selection) {
        if (selection == 0) {
            return partId++;
        }
        else {
            return productId++;
        }
    }

    /*
    Uses ObservableList.indexOf() to return an integer index of the specified Part.
    */
    public static int getIndex(Part part) {
        return allParts.indexOf(part);
    }

    /*
    Uses ObservableList.indexOf() to return an integer index of the specified Product.
    */
    public static int getIndex (Product product) {return allProducts.indexOf(product);}

    /*
    Uses ObservableList.get() to return a Part at the specified index.
    */
    public static Part getPart(int index) {
        return allParts.get(index);
    }

    /*
    Uses ObservableList.get() to return a Product at the specified index.
    */
    public static Product getProduct(int index) {return allProducts.get(index);}

    /*
    Sets selectedPart to the Part in allParts that matches the part that the user clicks in the PartsTableView.
    */
    public void setSelectedPart() {
        selectedPart = getPart(getIndex((Part) partsTableView.getSelectionModel().getSelectedItem()));
    }

    /*
    Sets selectedProduct to the Product that the user clicks in the PartsTableView.
    */
    public void setSelectedProduct() {
        selectedProduct = getProduct(getIndex((Product) productsTableView.getSelectionModel().getSelectedItem()));
    }

    /*
     Returns true if an int can be parsed from the String argument.
    */
    public static boolean intCheck(String checkMe) {
        if (checkMe == null) {
            return false;
        }
        try {
            Integer.parseInt(checkMe);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // fxml methods
    /*
    Calls setSelectedPart() when the user clicks on the PartsTableView.
    */
    public void onPartsTableViewClick(MouseEvent mouseEvent) {
        setSelectedPart();
    }

    /*
    Calls setSelectedProduct() when the user clicks on the ProductsTableView.
    */
    public void onProductsTableViewClick(MouseEvent mouseEvent) {
        setSelectedProduct();
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
    Loads and displays the AddProduct GUI.
    */
    public void onAddProductButton(ActionEvent event) throws IOException{
        FXMLLoader loadAddProduct = new FXMLLoader(getClass().getResource("/view/AddProduct.fxml"));
        Parent root = loadAddProduct.load();
        modifyProductScene = new Scene(root);
        modifyProductStage.setScene(modifyProductScene);
        modifyProductStage.show();
    }

    /*
    Calls deletePart() on the selectedPart when the user clicks the deletePartButton, then resets PartsTableView.
    */
    public void onDeletePartButton(ActionEvent actionEvent) throws IOException {
        System.out.println("Delete part button pressed");
        FXMLLoader loadDeleteWarning = new FXMLLoader(getClass().getResource("/view/ConfirmDelete.fxml"));
        Parent root = loadDeleteWarning.load();
        confirmDeleteScene = new Scene(root);
        confirmDeleteStage.setScene(confirmDeleteScene);
        confirmDeleteStage.show();
        if (delete) {
            deletePart(selectedPart);
            partsTableView.setItems(allParts);
        }
    }

    /*
    Calls deleteProduct() on the selectedProduct when the user clicks the deleteProductButton.
    */
    public void onDeleteProductButton(ActionEvent actionEvent) {
        System.out.println("Delete product button pressed");
        deleteProduct(selectedProduct);
    }

    /*
    Checks to see if the user has selected a part, then loads and displays the ModifyPart GUI if they have.
    */
    public void onModifyPartButton(ActionEvent actionEvent) throws IOException {
        if (selectedPart != null) {
            System.out.println("Modify part button pressed");
            FXMLLoader loadModifyPart = new FXMLLoader(getClass().getResource("/view/ModifyPart.fxml"));
            Parent root = loadModifyPart.load();
            modifyPartScene = new Scene(root);
            modifyPartStage.setScene(modifyPartScene);
            modifyPartStage.show();
        }
        else {
            System.out.println("Select a part first");
        }
    }

    /*
    Checks to see if the user has selected a Product, then loads and displays the ModifyProduct GUI if they have.
    */
    public void onModifyProductButton(ActionEvent actionEvent) throws IOException {
        if (selectedProduct != null) {
            System.out.println("Modify product button pressed");
            FXMLLoader loadModifyProduct = new FXMLLoader(getClass().getResource("/view/ModifyProduct.fxml"));
            Parent root = loadModifyProduct.load();
            modifyProductScene = new Scene(root);
            modifyProductStage.setScene(modifyProductScene);
            modifyProductStage.show();
        }
        else {
            System.out.println("Select a product first");
        }
    }

    /*
    Uses System.exit() to exit the application
    */
    public void onExitButton(ActionEvent actionEvent) {
        System.out.println("Exit button pressed");
        System.exit(0);
    }

    /*
    When the user presses enter after typing in the searchPartsTextField, the PartsTableView is set to show
    the results of lookupPart().
    */
    public void onSearchPartsTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (intCheck(searchPartsTextField.getText())) {
                int partInt = Integer.parseInt(searchPartsTextField.getText());
                partsTableView.setItems(partById(lookupPart(partInt)));
            }
            else {
                partsTableView.setItems(lookupPart(searchPartsTextField.getText()));
            }
        }
    }

    /*
    When the user presses enter after typing in the searchProductsTextField, the ProductsTableView is set to show
    the results of lookupProduct().
    */
    public void onSearchProductsTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (intCheck(searchProductsTextField.getText())) {
                int productInt = Integer.parseInt(searchProductsTextField.getText());
                productsTableView.setItems(productById(lookupProduct(productInt)));
            }
            else {
                productsTableView.setItems(lookupProduct(searchProductsTextField.getText()));
            }
        }
    }
}