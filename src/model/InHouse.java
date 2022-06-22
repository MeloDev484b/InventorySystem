package model;

import model.Part;
/** The InHouse class is a subclass of Part, used to hold machineId data.
*/
public class InHouse extends Part {
    private int machineId;
    /** Constructor for InHouse objects.
     @param id Part's ID.
     @param name Part's name.
     @param price Part's price per unit.
     @param stock Part's inventory level.
     @param min Minimum amount of part carried in stock.
     @param max Maximum amount of part carried in stock.
     @param machineId The ID of the machine that was used to manufacture the Part.
    */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** Getter to return machineId as an integer.
     @return machineId as an int.
    */
    public int getMachineId() {
        return machineId;
    }

    /** Setter to set machineId with the specified int.
     @param machineId The ID of the machine that was used to manufacture the Part.
    */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
