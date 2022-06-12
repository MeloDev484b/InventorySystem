package model;

import javafx.collections.FXCollections;

public class Product {
    private int productId;
    private String productName;
    private int inventoryLevel;
    private double pricePerUnit;

    public Product(int id, String name, int iLevel, double price) {
        this.productId = id;
        this.productName = name;
        this.inventoryLevel = iLevel;
        this.pricePerUnit = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getInventoryLevel() {
        return inventoryLevel;
    }

    public void setInventoryLevel(int inventoryLevel) {
        this.inventoryLevel = inventoryLevel;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

}
