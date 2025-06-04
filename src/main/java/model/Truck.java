package model;

import util.Validator;

import java.time.LocalDate;

/**
 *
 * Truck class represents a vehicle with specific properties such as axle count, fuel consumption, and maintenance cost.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class Truck extends Vehicle implements HasAxleCount {
    private int axleCount;

    public Truck() {
    }
    
    @Override
    public int getAxleCount() {
        return this.axleCount;
    }

    @Override
    public void setAxleCount(int axleCount) throws IllegalArgumentException {
        Validator.validateNegatives(axleCount);
        this.axleCount=axleCount;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.TRUCK;
    }
    

    @Override
    public String toString() {
        return "Truck - " +
                "Plate: " + getPlate() + "\n" +
                "Axle Count: " + axleCount +
                "\nCarrying Capacity: " + getCarryingCapacity() + " Kg" +
                "\nLast Maintenance: " + getLastMaintenance() ;
    }
}
