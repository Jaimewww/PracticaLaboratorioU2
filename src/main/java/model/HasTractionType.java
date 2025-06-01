package model;

/**
 *
 * This interface defines the methods for managing the traction type of a vehicle.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public interface HasTractionType {
    boolean hasFourWheelTraction();
    void setFourWheelTraction(boolean fourWheelTraction) throws IllegalArgumentException;
}
