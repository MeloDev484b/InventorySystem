package model;

import javafx.collections.FXCollections;
/*
The Part class is an abstract class to hold Part data. It is extended by the InHouse and Outsourced classes.
*/
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /*
    Constructor for the Part class.
    */
    public Part(int id, String name, double price, int stock, int min, int max) {
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
    public void setId(int partId) {
        this.id = partId;
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
    public void setName(String partName) {
        this.name = partName;
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
    public void setPrice(double price) {
        this.price = price;
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
    public void setStock(int stock) {
        this.stock = stock;
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
}
