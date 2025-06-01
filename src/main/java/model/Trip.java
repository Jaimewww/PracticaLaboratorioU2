package model;

import util.Validator;

import java.time.LocalDate;

/**
 *
 * Trip class represents a trip made by a vehicle.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class Trip {
    private double distanceTravelled;
    private LocalDate tripDate;
    private String vehiclePlate;

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        if (vehiclePlate == null || vehiclePlate.isEmpty()) {
            throw new IllegalArgumentException("Vehicle plate cannot be null or empty.");
        }
        if (!vehiclePlate.matches("[A-Z0-9-]+")) {
            throw new IllegalArgumentException("Vehicle plate must contain only uppercase letters, digits, and hyphens.");
        }
        this.vehiclePlate = vehiclePlate;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) throws IllegalArgumentException {
        Validator.validateNegatives(distanceTravelled);
        this.distanceTravelled = distanceTravelled;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public void setTripDate(LocalDate tripDate) {
        this.tripDate = tripDate;
    }
}
