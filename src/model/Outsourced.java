package model;

import model.Part;
/*
The Outsourced class is a subclass of Part, used to hold companyName data.
*/
public class Outsourced extends Part {
    private String companyName;
    /*
    Constructor for Outsourced objects.
    */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /*
    Getter to return companyName as a String.
    */
    public String getCompanyName() {
        return companyName;
    }

    /*
    Setter to set companyName with the specified String.
    */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
