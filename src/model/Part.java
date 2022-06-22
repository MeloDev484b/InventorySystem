package model;

import javafx.collections.FXCollections;
/** The Part class is an abstract class to hold Part data. It is extended by the InHouse and Outsourced classes.
*/
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** Constructor for the Part class.
     @param id Part's ID.
     @param name Part's name.
     @param price Part's price per unit.
     @param stock Part's inventory level.
     @param min Minimum amount of part carried in stock.
     @param max Maximum amount of part carried in stock.
    */
    public Part(int id, String name, double price, int stock, int min, int max) {
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
     @param partId The Part's ID.
     */
    public void setId(int partId) {
        this.id = partId;
    }

    /** Getter to return name as a String.
     @return Part name as a String.
     */
    public String getName() {
        return name;
    }

    /** Setter to set name with the specified String.
     @param partName Name of the part.
     */
    public void setName(String partName) {
        this.name = partName;
    }

    /** Getter to return price as a double.
     @return price as a double.
     */
    public double getPrice() {
        return price;
    }

    /** Setter to set price with the specified double.
     @param price Price of the part.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** Getter to return stock as an int.
     @return stock as an int.
     */
    public int getStock() {
        return stock;
    }

    /** Setter to set stock with the specified int.
     * @param stock Inventory level.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** Getter to return min as an int.
     @returns min as an int.
     */
    public int getMin() {
        return min;
    }

    /** Setter to set min with the specified int.
     @param min minimum stock.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** Getter to return max as an int.
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
}
