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
import java.util.Optional;
import java.util.ResourceBundle;
import static java.lang.Character.isAlphabetic;

/** The Inventory class is used to view and interact with the ObservableLists allParts and allProducts.
Objects in allParts and allProducts can be added, deleted, and modified by the user.
*/
public class Inventory implements Initializable {
    /** Prepare Stages for later use.
     */
    Stage addPartStage, modifyPartStage, modifyProductStage, addProductStage = new Stage();
    /** Prepare Scenes for later use.
     */
    Scene addPartScene, modifyPartScene, modifyProductScene, addProductScene;
    /** Exit button.
     */
    public Button exitButton;
    /** TableViews for parts and products.
     */
    public TableView partsTableView, productsTableView;
    /** TableColumns for Parts.
     */
    public TableColumn partIdColumn, partNameColumn, partInventoryLevelColumn, partPricePerUnitColumn;
    /** Add, delete, and modify buttons for parts.
     */
    public Button addPartButton, deletePartButton, modifyPartButton;
    /** Search TextFields.
     */
    public TextField searchPartsTextField, searchProductsTextField;
    /** TableColumns for Products.
     */
    public TableColumn productIdColumn, productNameColumn, productInventoryLevelColumn, productPricePerUnitColumn;
    /** Add, delete, and modify buttons for Products.
     */
    public Button addProductButton, deleteProductButton, modifyProductButton;
    /** ObservableList to hold all Parts in inventory.
     */
    private static ObservableList <Part> allParts = FXCollections.observableArrayList();
    /** ObservableList to hold all Products in inventory.
     */
    private static ObservableList <Product> allProducts = FXCollections.observableArrayList();
    /** Temporary storage for a selected Part.
     */
    public static Part selectedPart = null;
    /** Temporary storage for a selected Product.
     */
    public static Product selectedProduct = null;
    /** Static variable to hold the current Part ID number.
     */
    public static int partId = 1000;
    /** Static variable to hold the current Product ID number.
     */
    public static int productId = 1000;

    /** Initializes allParts and allProducts and sets the respective TableView to display the objects they contain.
    */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        allParts.add(new InHouse(995, "Screw", 7.99, 10, 1, 20, 6480));
        allParts.add(new InHouse(996, "Wedge", 3.88, 15, 1,20, 9785));
        allParts.add(new Outsourced(997, "Nut", 1.69, 8, 1, 20, "A Company"));
        allParts.add(new Outsourced(998, "Bun", 9.80, 1, 1, 20, "Generic Cola"));
        allParts.add(new Outsourced(999, "Bun", 25.40, 17, 1, 20, "Plus-Ultra Cola"));

        //Add test Products to allProducts
        allProducts.add(new Product(995, "Branch", 17.99, 19, 1, 20));
        allProducts.add(new Product(996, "Cheese Wheel", 77.45, 17, 1, 20));
        allProducts.add(new Product(997, "Sandwich", 12.44, 9, 1, 20));
        allProducts.add(new Product(998, "Shovel", 9.21, 2, 1, 20));
        allProducts.add(new Product(999, "Shovel", 23.34, 5, 1, 20));

    }

    // UML-specified methods
    /** Uses ObservableList.add() to add a Part object to allParts.
     @param newPart Part to be added to allParts.
    */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** Uses ObservableList.add() to add a Product object to allProducts.
     @param newProduct Part to be added to allProducts.
    */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** Returns a Part contained in allParts that matches the supplied partId integer argument. If no part is matched,
    the user is shown a warning that the part was not found and returns null.
     @return Part if a part is found, null if a match is not found.
    */
    public static Part lookupPart(int partId) {
        ObservableList <Part> foundParts = getAllParts();
        for (Part part : foundParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        Alert notFound = new Alert(Alert.AlertType.INFORMATION);
        notFound.setContentText("Part not found.");
        notFound.show();
        return null;
    }

    /** Returns a Product contained in allProducts that matches the supplied productId integer argument. If no
    product is matched, the user is shown a warning that the product was not found and returns null.
     @return Product if a product is found, null if no match is found.
    */
    public Product lookupProduct(int productId) {
        ObservableList <Product> foundProducts = getAllProducts();
        for (Product product : foundProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        Alert notFound = new Alert(Alert.AlertType.INFORMATION);
        notFound.setContentText("Product not found.");
        notFound.show();
        return null;
    }

    /** Checks allParts for a partName that matches the Part.getName() and adds it to the partsByName list.
    If no Parts match, the original allParts list is returned and the user is notified that the searched part
    does not exist.
     @return ObservableList of Parts, or allParts if no matches are found.
    */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> partsByName = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                partsByName.add(part);
            }
        }
        if (partsByName.size() > 0) {
            return partsByName;
        }
        else {
            Alert notFound = new Alert(Alert.AlertType.INFORMATION);
            notFound.setContentText("Part not found.");
            notFound.show();
            return allParts;
        }
    }

    /** Checks allProducts for a ProductName that matches the Product.getName() and adds it to the ProductsByName list.
    If no Products match, the original allProducts list is returned and the user is notified that the searched Product
    does not exist.
     @return ObservableList of Products, or allProducts if no matches are found.
    */
    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsByName = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                productsByName.add(product);
            }
        }
        if (productsByName.size() > 0) {
            return productsByName;
        }
        else {
            Alert notFound = new Alert(Alert.AlertType.INFORMATION);
            notFound.setContentText("Product not found.");
            notFound.show();
            return allProducts;
        }
    }

    /** Uses ObservableList.set() to replace a Part in allParts, at the specified index.
     @param index Index of the Part to be replaced.
     @param updatedPart Part to be saved over the old Part.
    */
    public static void updatePart(int index, Part updatedPart) {
        allParts.set(index, updatedPart);
    }

    /** Uses ObservableList.set() to replace a Product in allProducts, at the specified index.
     @param index Index of the Product to be replaced
     @param updatedProduct Product to be saved over the old Product.
    */
    public static void updateProduct(int index, Product updatedProduct) {
        allProducts.set(index, updatedProduct);
    }

    /** Uses ObservableList.remove() to delete a Part in allParts. Returns true if the Part is deleted.
     @param deleteMe The part to be deleted.
     @return allParts as an ObservableList.
     */
    public static boolean deletePart(Part deleteMe) {
        return allParts.remove(deleteMe);
    }

    /** Uses ObservableList.remove() to delete a Product in allProducts. Returns true if the Product is deleted.
     @param deleteMe The product to be deleted.
     @return allProducts as an ObservableList.
    */
    public static boolean deleteProduct(Product deleteMe) {
        return allProducts.remove(deleteMe);
    }

    /** Getter that returns the ObservableList allParts.
     @return allParts as an ObservableList.
    */
    public static ObservableList getAllParts() {
        return allParts;
    }

    /** Getter that returns the ObservableList allProducts.
     @return allProducts as an ObservableList.
    */
    public ObservableList getAllProducts() {
        return allProducts;
    }

    // Helper methods
    /** Checks allParts for a partId that matches the Part.getId() and adds it to the partsById list.
    If no Parts match, the original allParts list is returned.
     @param partToId The part to be searched by its ID.
     @return allParts as an ObservableList if it is not found, partById as an ObservableList if it is found.
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

    /** Checks allProducts for a productId that matches the Product.getId() and adds it to the productsById list.
    If no Products match, the original allProducts list is returned.
     @param productToId The part to be searched by its ID.
     @return allProducts as an ObservableList if it is not found, productById as an ObservableList if it is found.
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

    /** Increments partId or productId based on the argument supplied.
     @param selection 0 to increment partId, any other int to increment productId.

    */
    public static int incrementId(int selection) {
        if (selection == 0) {
            return partId++;
        }
        else {
            return productId++;
        }
    }

    /** Uses ObservableList.indexOf() to return an integer index of the specified Part.
     @param part The Part to get the index of.
     @return The index of part as an int.
    */
    public static int getIndex(Part part) {
        return allParts.indexOf(part);
    }

    /** Uses ObservableList.indexOf() to return an integer index of the specified Product.
     @param product The Product to get the index of.
     @return The index of product as an int.
    */
    public static int getIndex (Product product) {return allProducts.indexOf(product);}

    /** Uses ObservableList.get() to return a Part at the specified index.
     @param index The desired index as an int.
     @return Part at the specified index.
    */
    public static Part getPart(int index) {
        return allParts.get(index);
    }

    /** Uses ObservableList.get() to return a Product at the specified index.
     @param index The desired index as an int.
     @return Product at the specified index.
    */
    public static Product getProduct(int index) {return allProducts.get(index);}

    /** Sets selectedProduct to null to ensure the user does not unintentionally delete or modify a product when they
    return to the ProductsTableView.
    Sets selectedPart to the Part in allParts that matches the part that the user clicks in the PartsTableView.
    */
    public void setSelectedPart() {
        selectedProduct = null;
        selectedPart = getPart(getIndex((Part) partsTableView.getSelectionModel().getSelectedItem()));
    }

    /** Sets selectedPart to null to ensure the user does not unintentionally delete or modify a part when they
    return to the PartsTableView.
    Sets selectedProduct to the Product that the user clicks in the ProductsTableView.
    */
    public void setSelectedProduct() {
        selectedPart = null;
        selectedProduct = getProduct(getIndex((Product) productsTableView.getSelectionModel().getSelectedItem()));
    }

    /** Checks for a NumberFormatException to determine if the String argument is an integer.
     Returns true if an int can be parsed from the String argument.
     Returns false if the argument is null, or if a NumberFormatException occurs.

    "RUNTIME ERROR"
    Initially I ran into runtime errors in the Inventory GUI as I attempted to parse Strings for integers.
    When the Strings did not contain the correct type of data, I would run into a NumberFormatException.
    I created methods to catch these exceptions and return false to indicate the String did not contain
    an integer.

     @param checkMe The String to be checked.
     @return true if checkMe is an int, false if not.
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

    /** Checks for a NumberFormatException to determine if the String argument is a double.
     Returns true if a double can be parsed from the String argument. Returns false if the argument is null,
     or if a NumberFormatException occurs.

    "RUNTIME ERROR"
    Initially I ran into runtime errors in the Inventory GUI as I attempted to parse Strings for doubles.
    When the Strings did not contain the correct type of data, I would run into a NumberFormatException.
    I created methods to catch these exceptions and return false to indicate the String did not contain
    a double.
     @param checkMe The String to be checked.
     @return true if checkMe is a double, false if not.
    */
    public static boolean doubleCheck(String checkMe) {
        if (checkMe == null) {
            return false;
        }
        try {
            Double.parseDouble(checkMe);
        }
        catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /** Runs isAlphabetic against the first character to determine if the String argument begins with an alphabetic
    character. Returns true if the String argument begins with an alphabetic character, returns false if the
    argument is null, an empty string, or begins with a non-alphabetic character.
     @param checkMe The String to be checked.
     @return true if checkMe begins with an alphabetic character, false if not.
    */
    public static boolean stringCheck(String checkMe) {
        if (checkMe == null || checkMe == "") {
            return false;
        }
        if (isAlphabetic(checkMe.charAt(0))) {
            return true;
        }
        else {
            return false;
        }
    }

    /** Generates an information alert with text that depends on whether the user has selected a Part or a Product.
    */
    private void showInfoMessage() {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        if (selectedPart != null) {
            info.setContentText(selectedPart.getName() + " was not deleted.");
        }
        else if (selectedProduct != null){
            info.setContentText(selectedProduct.getName() + " was not deleted.");
        }
        info.show();
    }

    /** Generates warning alert with text that depends on the supplied integer argument.
     @param warningType 0 for the Part deletion warning, any other int for Product deletion warning.
    */
    private void selectBeforeDeleteWarning(int warningType) {
        Alert deleteWarning = new Alert(Alert.AlertType.WARNING);
        if (warningType == 0) {
            deleteWarning.setContentText("Select a part to delete.");
        }
        else {
            deleteWarning.setContentText("Select a product to delete.");
        }
        deleteWarning.show();
    }

    // fxml methods
    /** Calls setSelectedPart() when the user clicks on the PartsTableView, and it is not empty.
    */
    public void onPartsTableViewClick(MouseEvent mouseEvent) {
        if (!partsTableView.getSelectionModel().isEmpty()) {
            setSelectedPart();
        }
    }

    /** Calls setSelectedProduct() when the user clicks on the ProductsTableView, and it is not empty.
    */
    public void onProductsTableViewClick(MouseEvent mouseEvent) {
        if (!productsTableView.getSelectionModel().isEmpty()) {
            setSelectedProduct();
        }
    }

    /** Loads and displays the AddPart GUI.
    */
    public void onAddPartButton(ActionEvent event) throws IOException {
        System.out.println("Add part button pressed");
        FXMLLoader loadAddPart = new FXMLLoader(getClass().getResource("/view/AddPart.fxml"));
        Parent root = loadAddPart.load();
        addPartScene = new Scene(root);
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    /** Loads and displays the AddProduct GUI.
    */
    public void onAddProductButton(ActionEvent event) throws IOException{
        FXMLLoader loadAddProduct = new FXMLLoader(getClass().getResource("/view/AddProduct.fxml"));
        Parent root = loadAddProduct.load();
        modifyProductScene = new Scene(root);
        modifyProductStage.setScene(modifyProductScene);
        modifyProductStage.show();
    }

    /** If selectedPart is not null an alert is displayed to the user. If the user clicks OK,
    deletePart() is called on the selectedPart, and selectedPart is set to null so it no longer
    stores a copy of the clicked part.
    If the user clicks CANCEL or closes the window, an info panel is displayed to the user to inform them
    the selectedPart has not been deleted. The selectedPart is then set to null.
    If the user has not selected a part, the user is warned they must select a part before attempting to delete it.
    */
    public void onDeletePartButton(ActionEvent actionEvent) {
        if (selectedPart != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setContentText("Please confirm deletion of " + selectedPart.getName() + ".");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(!result.isPresent()) {
                showInfoMessage();
                selectedPart = null;
            }
            else if(result.get() == ButtonType.OK) {
                deletePart(selectedPart);
                selectedPart = null;
            }
            else if(result.get() == ButtonType.CANCEL) {
                showInfoMessage();
                selectedPart = null;
            }
        }
        else {
            selectBeforeDeleteWarning(0);
        }
    }

    /** If selectedProduct is not null an alert is displayed to the user. If the user clicks OK,
    deleteProduct() is called on the selectedProduct, and selectedProduct is set to null so it no longer
    stores a copy of the clicked Product.
    If the user clicks CANCEL or closes the window, an info panel is displayed to the user to inform them
    the selectedProduct has not been deleted. The selectedProduct is then set to null.
    If the user has not selected a Product, the user is warned they must select a Product before attempting to
    delete it.
    */
    public void onDeleteProductButton(ActionEvent actionEvent) {
        if (selectedProduct != null) {
            if (selectedProduct.getAllAssociatedParts().size() > 0) {
                Alert associatedPartWarning = new Alert(Alert.AlertType.WARNING);
                associatedPartWarning.setContentText("Remove associated parts before deleting " + selectedProduct.getName() + ".");
                associatedPartWarning.show();
                selectedProduct = null;
            }
            else {
                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setContentText("Please confirm deletion of " + selectedProduct.getName() + ".");
                Optional<ButtonType> result = confirmation.showAndWait();
                if(!result.isPresent()) {
                    showInfoMessage();
                    selectedProduct = null;
                }
                else if(result.get() == ButtonType.OK) {
                    deleteProduct(selectedProduct);
                    selectedProduct = null;
                }
                else if(result.get() == ButtonType.CANCEL) {
                    showInfoMessage();
                    selectedProduct = null;
                }
            }
        }
        else {
            selectBeforeDeleteWarning(1);
        }
    }

    /** Checks to see if the user has selected a part, then loads and displays the ModifyPart GUI if they have.
    If the user has not selected a part, they are warned to select a part before attempting to modify it.
    */
    public void onModifyPartButton(ActionEvent actionEvent) throws IOException {
        if (selectedPart != null) {
            FXMLLoader loadModifyPart = new FXMLLoader(getClass().getResource("/view/ModifyPart.fxml"));
            Parent root = loadModifyPart.load();
            modifyPartScene = new Scene(root);
            modifyPartStage.setScene(modifyPartScene);
            modifyPartStage.show();
        }
        else {
            Alert selectPartWarning = new Alert(Alert.AlertType.WARNING);
            selectPartWarning.setContentText("Please select a part to modify.");
            selectPartWarning.show();
        }
    }

    /** Checks to see if the user has selected a Product, then loads and displays the ModifyProduct GUI if they have.
    If the user has not selected a product, they are warned to select a product before attempting to modify it.
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
            Alert selectProductWarning = new Alert(Alert.AlertType.WARNING);
            selectProductWarning.setContentText("Please select a product to modify.");
            selectProductWarning.show();
        }
    }

    /** Calls System.exit() to exit the application
    */
    public void onExitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    /** When the user presses enter after typing in the searchPartsTextField, or the searchPartsTextField is left empty
    the contents of the TextField are checked to determine if they are an integer or not. If the contents are an
    integer, the partsTableView is set to the results of partById search. If the contents are not an integer, the
    partsTableView is set to the results of a part name search.
    */
    public void onSearchPartsTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || searchPartsTextField.getText() == "") {
            if (intCheck(searchPartsTextField.getText())) {
                int partInt = Integer.parseInt(searchPartsTextField.getText());
                partsTableView.setItems(partById(lookupPart(partInt)));
            }
            else {
                partsTableView.setItems(lookupPart(searchPartsTextField.getText()));
            }
        }
    }

    /** When the user presses enter after typing in the searchProductsTextField, or the searchProductsTextField
    is left empty, the contents of the TextField are checked to determine if they are an integer or not.
    If the contents are an integer, the productsTableView is set to the results of productById search.
    If the contents are not an integer, the productsTableView is set to the results of a product name search.
    */
    public void onSearchProductsTextField(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER || searchPartsTextField.getText() == "") {
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