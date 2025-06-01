package util;

import model.PickupTruck;
import model.Trip;
import model.Vehicle;

/**
 *
 * Utility class for calculating the cost of a trip based on the vehicle's fuel consumption and maintenance costs.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class CostCalculator {

    public static double calculateTripCost(Vehicle vehicle, Trip trip) {
        double cost = (trip.getDistanceTravelled() * vehicle.getFuelConsumption()
                * Constants.PRICE_PER_LITER)+vehicle.getMaintenanceCost();
        if(vehicle instanceof PickupTruck){
            cost+= cost * Constants.PICKUP_COST_PERCENTAJE; // 20% surcharge for Pickup Trucks
        }
        return cost;
    }
}
