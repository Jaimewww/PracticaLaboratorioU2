package model;

/*
    * This interface defines the methods for managing the axle count of a vehicle.
    * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public interface HasAxleCount {
    int getAxleCount();
    void setAxleCount(int axleCount) throws IllegalArgumentException;
}
