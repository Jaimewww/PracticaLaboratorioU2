package model;

import service.InputService;
import util.Validator;

/*
 * This class represents a Motorcycle, which is a type of Vehicle.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class Motorcycle extends Vehicle implements HasEngineDisplacement{
    private double engineDisplacement;

    private static int motorcycleCount = 0;
    private static double fuelConsumption = 0.0;
    private static double maintenanceCost = 0.0;

    public Motorcycle() {
    }

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
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORCYCLE;
    }

    public void setFuelConsumption(double fuelConsumption) throws IllegalArgumentException {
        Motorcycle.fuelConsumption = fuelConsumption;
    }

    public void setMaintenanceCost(double maintenanceCost) throws IllegalArgumentException {
        Motorcycle.maintenanceCost = maintenanceCost;
    }

    public static int getMotorcycleCount() {
        return motorcycleCount;
    }

    @Override
    public void enterDetails(InputService inputService) {
        double engineDisplacement = inputService.getDouble("Enter engine displacement for the Motorcycle (cm^3):");
        setEngineDisplacement(engineDisplacement);

        if (Motorcycle.getMotorcycleCount() == 0) {
            double fuelConsumption = inputService.getDouble("Enter fuel consumption for the Motorcycle (L/100Km):");
            double maintenanceCost = inputService.getDouble("Enter maintenance cost for the Motorcycle:");
            setFuelConsumption(fuelConsumption);
            setMaintenanceCost(maintenanceCost);
        }
        motorcycleCount++;
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
