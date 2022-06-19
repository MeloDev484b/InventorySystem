package model;

import model.Part;
/*
The InHouse class is a subclass of Part, used to hold machineId data.
*/
public class InHouse extends Part {
    private int machineId;
    /*
    Constructor for InHouse objects.
    */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /*
    Getter to return machineId as an integer.
    */
    public int getMachineId() {
        return machineId;
    }

    /*
    Setter to set machineId with the specified int.
    */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
