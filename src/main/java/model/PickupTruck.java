package model;

import service.InputService;

/**
 * Represents a Pickup Truck vehicle.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class PickupTruck extends Vehicle implements HasTractionType {
    boolean fourWheelTraction;

    private static int pickUpTruckCount = 0;
    private static double fuelConsumption = 0.0;
    private static double maintenanceCost = 0.0;

    public PickupTruck() {
    }


    @Override
    public boolean hasFourWheelTraction() {
        return fourWheelTraction;
    }

    @Override
    public void setFourWheelTraction(boolean fourWheelTraction) throws IllegalArgumentException {
        this.fourWheelTraction = fourWheelTraction;
    }

    @Override
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    @Override
    public double getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setFuelConsumption(double fuelConsumption) throws IllegalArgumentException {
        PickupTruck.fuelConsumption = fuelConsumption;
    }

    public void setMaintenanceCost(double maintenanceCost) throws IllegalArgumentException {
        PickupTruck.maintenanceCost = maintenanceCost;
    }

    @Override
    public VehicleType getType() {
        return VehicleType.PICKUP;
    }

    public static int getPickUpTruckCount() {
        return pickUpTruckCount;
    }

    @Override
    public void enterDetails(InputService inputService) {
        boolean hasFourWheelTraction = inputService.getBoolean("Does the Pickup Truck have 4x4 traction?");
        setFourWheelTraction(hasFourWheelTraction);

        if (PickupTruck.getPickUpTruckCount() == 0) {
            double fuelConsumption = inputService.getDouble("Enter fuel consumption for the PickUp (L/100Km):");
            double maintenanceCost = inputService.getDouble("Enter maintenance cost for the PickUp:");
            setFuelConsumption(fuelConsumption);
            setMaintenanceCost(maintenanceCost);
        }
        pickUpTruckCount++;
    }

    @Override
    public String toString() {
        return "PickupTruck - \n" +
                "Plate: " + getPlate() + "\n" +
                "4x4: " + (fourWheelTraction ? "Yes":"No") +
                "\nCarrying Capacity:" + getCarryingCapacity() + " Kg" +
                "\nLast Maintenance: " + getLastMaintenance();
    }
}
