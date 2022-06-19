package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/*
The Product class is used to hold Product data and an ObservableList of associatedParts.
*/
public class Product {
    private ObservableList <Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    /*
    Constructor for the Product class.
    */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /*
    Getter to return id as an int.
    */
    public int getId() {
        return id;
    }

    /*
    Setter to set id with the specified int.
     */
    public void setId(int productId) {
        this.id = productId;
    }

    /*
    Getter to return name as a String.
     */
    public String getName() {
        return name;
    }

    /*
    Setter to set name with the specified String.
     */
    public void setName(String productName) {
        this.name = productName;
    }

    /*
    Getter to return price as a double.
     */
    public double getPrice() {
        return price;
    }

    /*
    Setter to set price with the specified double.
     */
    public void setPrice(double pricePerUnit) {
        this.price = pricePerUnit;
    }

    /*
    Getter to return stock as an int.
     */
    public int getStock() {
        return stock;
    }

    /*
    Setter to set stock with the specified int.
     */
    public void setStock(int inventoryLevel) {
        this.stock = inventoryLevel;
    }

    /*
    Getter to return min as an int.
     */
    public int getMin() {
        return min;
    }

    /*
    Setter to set min with the specified int.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /*
    Getter to return max as an int.
     */
    public int getMax() {
        return max;
    }

    /*
    Setter to set max with the specified int.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /*
    Uses ObservableList.add() to add a Part to associatedParts.
    */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /*
    Returns true if the selectedAssociatedPart is removed from associatedParts.
    */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        return associatedParts.remove(selectedAssociatedPart);
    }

    /*
    Returns associatedParts as an ObservableList of Parts.
    */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
