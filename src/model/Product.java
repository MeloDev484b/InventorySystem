package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList <Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;

    private int min;
    private int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int productId) {
        this.id = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double pricePerUnit) {
        this.price = pricePerUnit;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int inventoryLevel) {
        this.stock = inventoryLevel;
    }


    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }
    public void deleteAssociatedPart(Part selectedAssociatedPart) {
        //check if selected part exists first
        associatedParts.remove(selectedAssociatedPart);
    }
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
