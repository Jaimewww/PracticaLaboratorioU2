package business;

import data.VehicleRepository;
import exception.EntityNotFoundException;
import model.Vehicle;
import java.time.LocalDate;

/**
 * Facade class for managing vehicle operations.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class VehicleFacade {
    private final VehicleRepository repo = new VehicleRepository();

    public Vehicle create(Vehicle vehicle) throws EntityNotFoundException {
        try{
            repo.find(vehicle.getPlate());
        } catch (EntityNotFoundException e) {
            return repo.save(vehicle);
        }
        throw new EntityNotFoundException("Vehicle with plate " + vehicle.getPlate() + " already exists.");
    }

    /**
     *
     * Updates the last maintenance date of a vehicle by its plate.
     * @param plate
     * @param localDate
     * @return
     * @throws EntityNotFoundException
     */
    public Vehicle update(String plate, LocalDate localDate) throws EntityNotFoundException {
        try {
            return repo.update(plate, localDate);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Vehicle with plate " + plate + " not found.");
        }
    }

    /*public Vehicle find(long id) throws EntityNotFoundException {
        try {
            return repo.find(id);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }*/

    public Vehicle find(String plate) throws EntityNotFoundException {
            return repo.find(plate);
    }
}
