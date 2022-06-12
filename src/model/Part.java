package model;

import javafx.collections.FXCollections;

public class Part {
    private int partId;
    private String partName;
    private int inventoryLevel;
    private double pricePerUnit;

    public Part(int id, String name, int iLevel, double price) {
        this.partId = id;
        this.partName = name;
        this.inventoryLevel = iLevel;
        this.pricePerUnit = price;
    }

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
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
