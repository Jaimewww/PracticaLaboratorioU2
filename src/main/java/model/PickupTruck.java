package model;

/**
 * Represents a Pickup Truck vehicle.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class PickupTruck extends Vehicle implements HasTractionType {
    boolean fourWheelTraction;

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
    public VehicleType getType() {
        return VehicleType.PICKUP;
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
