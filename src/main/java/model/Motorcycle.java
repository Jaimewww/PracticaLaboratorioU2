package model;

import service.InputService;
import util.Validator;

/*
 * This class represents a Motorcycle, which is a type of Vehicle.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class Motorcycle extends Vehicle implements HasEngineDisplacement{
    private double engineDisplacement;

    @Override
    public double getEngineDisplacement() {
        return this.engineDisplacement;
    }

    @Override
    public void setEngineDisplacement(double engineDisplacement) throws IllegalArgumentException {
        Validator.validateNegatives(engineDisplacement);
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORCYCLE;
    }

    @Override
    public String toString() {
        return "Motorcycle - " +
                ", Plate: " + getPlate() + "\n" +
                "Engine Displacement: " + engineDisplacement + " cm^3" +
                "\nCarrying Capacity: " + getCarryingCapacity() + "Kg" +
                "\nLast Maintenance: " + getLastMaintenance() ;
    }
}
