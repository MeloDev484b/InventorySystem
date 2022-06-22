package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** The Product class is used to hold Product data and an ObservableList of associatedParts.
*/
public class Product {
    private ObservableList <Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /** Constructor for the Product class.
    @param id Product's ID.
     @param name Product's name.
     @param price Product's price per unit.
     @param stock The amount of Product on hand.
     @param min The minimum amount of Product to be carried.
     @param max The maximum amount of Product to be carried.
    */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** Getter to return id as an int.
    @return id as an int.
    */
    public int getId() {
        return id;
    }

    /** Setter to set id with the specified int.
    @param productId the product ID.
     */
    public void setId(int productId) {
        this.id = productId;
    }

    /** Getter to return name as a String.
    @return name as a String.
     */
    public String getName() {
        return name;
    }

    /** Setter to set name with the specified String.
    @param productName name of the product.
     */
    public void setName(String productName) {
        this.name = productName;
    }

    /** Getter for price.
    @return price as a double.
     */
    public double getPrice() {
        return price;
    }

    /** Setter to set price with the specified double.
    @param pricePerUnit price per unit.
     */
    public void setPrice(double pricePerUnit) {
        this.price = pricePerUnit;
    }

    /** Getter for stock.
    @return stock as an int.
     */
    public int getStock() {
        return stock;
    }

    /** Setter to set stock with the specified int.
    @param inventoryLevel amount of stock
     */
    public void setStock(int inventoryLevel) {
        this.stock = inventoryLevel;
    }

    /** Getter for min.
     @return min as an int.
     */
    public int getMin() {
        return min;
    }

    /** Setter for min.
    @param min minimum stock.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** Getter for max.
    @return max as an int.
     */
    public int getMax() {
        return max;
    }

    /** Setter to set max with the specified int.
     @param max maximum stock.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /** Uses ObservableList.add() to add a Part to associatedParts.
    */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /** Removes selectedAssociatedPart from associatedParts.
    @return true if the selectedAssociatedPart is removed from associatedParts.
     @param selectedAssociatedPart selected associated part.
    */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /** Getter for associatedParts.
    @return associatedParts as an ObservableList of Parts.
    */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
