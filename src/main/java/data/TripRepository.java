package data;

import exception.EntityNotFoundException;
import model.Trip;
import model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Repository class for managing trips associated with vehicles.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class TripRepository {
    private final Map<Long, Trip> trips = new TreeMap<>();

    public Trip save(Vehicle vehicle, Trip trip) throws IllegalArgumentException {
        if (trip == null) {
            throw new IllegalArgumentException("Trip cannot be null.");
        }
        long id = trips.size() + 1;
        trip.setVehiclePlate(vehicle.getPlate());
        trips.put(id, trip);
        return trip;
    }

    /*public Trip find(Long id) throws IllegalArgumentException {
        Trip trip = trips.get(id);
        if (trip == null) {
            throw new IllegalArgumentException("Trip not found with ID: " + id);
        }
        return trip;
    }

    public Trip find(String plate) throws IllegalArgumentException, EntityNotFoundException {
        for (Trip trip : trips.values()) {
            if (trip.getVehiclePlate().equals(plate)) {
                return trip;
            }
        }
        throw new EntityNotFoundException("Trip not found with plate: " + plate);
    }*/

    /**
     *
     * Finds all trips associated with a specific vehicle.
     * @param vehicle
     * @return
     * @throws EntityNotFoundException
     */
    /*public List<Trip> find(Vehicle vehicle) throws EntityNotFoundException {
        if(trips.isEmpty()){
            throw new EntityNotFoundException("No trips found");
        }
        List<Trip> tripList = new ArrayList<>();
        for (Trip trip : trips.values()) {
            if (trip.getVehiclePlate().equals(vehicle.getPlate())) {
                tripList.add(trip);
            }
        }
        if(!tripList.isEmpty()) {
            return tripList;
        }
        throw new EntityNotFoundException("Trips not found for this plate: " + vehicle.getPlate());
    }*/
}
