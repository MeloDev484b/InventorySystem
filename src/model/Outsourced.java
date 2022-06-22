package model;

import model.Part;
/** The Outsourced class is a subclass of Part, used to hold companyName data.
*/
public class Outsourced extends Part {
    private String companyName;
    /** Constructor for Outsourced objects.
     @param id Part's ID.
     @param name Part's name.
     @param price Part's price per unit.
     @param stock Part's inventory level.
     @param min Minimum amount of part carried in stock.
     @param max Maximum amount of part carried in stock.
     @param companyName The name of the company that manufactured the part.
    */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** Getter to return companyName as a String.
     @return companyName as a String.
    */
    public String getCompanyName() {
        return companyName;
    }

    /** Setter to set companyName with the specified String.
     @param companyName The company's name.
    */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
