package data;

import exception.EntityNotFoundException;
import model.Vehicle;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*
 * Repository class for managing vehicles.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class VehicleRepository {
    private static final Map<Long, Vehicle> tableVehicleDB = new TreeMap<>();

    public VehicleRepository() {
    }

    public Vehicle save(Vehicle vehicle) throws IllegalArgumentException {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }
        for (Vehicle v : tableVehicleDB.values()) {
            if (v.getPlate().equals(vehicle.getPlate())) {
                throw new IllegalArgumentException("Vehicle with plate " + vehicle.getPlate() + " already exists.");
            }
        }
        long id = tableVehicleDB.size() + 1;
        tableVehicleDB.put(id, vehicle);
        return tableVehicleDB.get(id);
    }

    public Vehicle update(String plate, LocalDate localDate) throws EntityNotFoundException {
            long updateId = findId(plate);
            Vehicle vehicle = tableVehicleDB.get(updateId);
            vehicle.setLastMaintenance(localDate);
            return vehicle;
    }

    /**
     *
     * Finds the ID of a vehicle by its plate.
     * @param plate
     * @return
     * @throws EntityNotFoundException
     */
    private long findId(String plate) throws EntityNotFoundException {
        for (Map.Entry<Long, Vehicle> entry : tableVehicleDB.entrySet()) {
            if (entry.getValue().getPlate().equals(plate)) {
                return entry.getKey();
            }
        }
        throw new EntityNotFoundException("Vehicle not found with plate: " + plate);
    }

    public Vehicle find (long id) throws EntityNotFoundException {
        Vehicle vehicle = tableVehicleDB.get(id);
        if (vehicle == null) {
            throw new EntityNotFoundException("Vehicle not found with ID: " + id);
        }
        return vehicle;
    }

    public Vehicle find (String plate) throws EntityNotFoundException {
        for (Vehicle vehicle : tableVehicleDB.values()) {
            if (vehicle.getPlate().equals(plate)) {
                return vehicle;
            }
        }
        throw new EntityNotFoundException("Vehicle not found with plate: " + plate);
    }

    public static List<Vehicle> getAllVehicles() {
        return new ArrayList<>(tableVehicleDB.values());
    }
}
