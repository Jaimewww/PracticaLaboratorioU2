package model;

/**
 * This interface defines the methods for managing the engine displacement of a vehicle.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public interface HasEngineDisplacement {
    double getEngineDisplacement();
    void setEngineDisplacement(double engineDisplacement) throws IllegalArgumentException;
}
