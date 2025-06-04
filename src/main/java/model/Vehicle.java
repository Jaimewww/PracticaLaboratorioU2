package model;

import util.Validator;
import java.time.LocalDate;

/**
 * Vehicle class represents a generic vehicle with properties such as car plate, carrying capacity, and last maintenance date.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public abstract class Vehicle {
    protected String carPlate;
    protected double carryingCapacity;
    protected LocalDate lastMaintenance;
    protected double fuelConsumption;
    protected double maintenanceCost;


    public Vehicle() {
        this.carPlate = "";
        this.carryingCapacity = 0.0;
        this.fuelConsumption = 0.0;
        this.maintenanceCost = 0.0;
        this.lastMaintenance = LocalDate.now();
    }

    //Fuel consumption and maintenance cost are abstract methods to be implemented by subclasses.
    public double getFuelConsumption() {
        return fuelConsumption;
    }
    
    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setFuelConsumption(double fuelConsumption) throws IllegalArgumentException{
        Validator.validateNegatives(fuelConsumption);
        this.fuelConsumption = fuelConsumption;
    }

    public void setMaintenanceCost(double maintenanceCost) throws IllegalArgumentException{
       Validator.validateNegatives(maintenanceCost);
       this.maintenanceCost = maintenanceCost;
    }


    public void setPlate(String carPlate) throws IllegalArgumentException {
        if (!carPlate.matches("[A-Z0-9-]+")) {
            throw new IllegalArgumentException("Vehicle plate must contain only uppercase letters, digits, and hyphens.");
        }
        this.carPlate = carPlate;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public void setLastMaintenance(LocalDate lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public String getPlate(){
        return this.carPlate;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public LocalDate getLastMaintenance() {
        return lastMaintenance;
    }

    public abstract VehicleType getType();
}
