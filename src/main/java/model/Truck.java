package model;

import service.InputService;
import util.Validator;

import java.time.LocalDate;

/**
 *
 * Truck class represents a vehicle with specific properties such as axle count, fuel consumption, and maintenance cost.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class Truck extends Vehicle implements HasAxleCount {
    private int axleCount;

    private static int truckCount = 0;
    private static double fuelConsumption = 0.0;
    private static double maintenanceCost = 0.0;

    public Truck() {
    }

    public Truck(String carPlate, double carryingCapacity, LocalDate lastMaintenance) {
        super(carPlate, carryingCapacity, lastMaintenance);
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
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setFuelConsumption(double fuelConsumption){
        Truck.fuelConsumption = fuelConsumption;
    }

    public void setMaintenanceCost(double maintenanceCost){
        Truck.maintenanceCost = maintenanceCost;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.TRUCK;
    }

    public static int getTruckCount() {
        return truckCount;
    }

    @Override
    public void enterDetails(InputService inputService) {
        int axleCount = inputService.getInt("Enter axle count for the Truck:");
        setAxleCount(axleCount);

        if (Truck.getTruckCount() == 0) {
            double fuelConsumption = inputService.getDouble("Enter fuel consumption for the Truck (L/100Km):");
            double maintenanceCost = inputService.getDouble("Enter maintenance cost for the Truck:");
            setFuelConsumption(fuelConsumption);
            setMaintenanceCost(maintenanceCost);
        }
        truckCount++;
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
