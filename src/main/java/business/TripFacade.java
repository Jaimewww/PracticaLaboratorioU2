package business;

import data.TripRepository;
import exception.EntityNotFoundException;
import model.Trip;
import model.Vehicle;
import util.Validator;

import java.util.List;

/*
 * Facade class for managing trips associated with vehicles.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class TripFacade {
    private final TripRepository tripRepository=new TripRepository();

    public Trip create(Vehicle vehicle, Trip trip) throws EntityNotFoundException {
        try{
            tripRepository.save(vehicle,trip);
        }catch(IllegalArgumentException e){
            throw new EntityNotFoundException(e.getMessage());
        }
        return trip;
    }

    /**
     * Finds a trip by its ID.
     * @param vehicle
     * @return
     * @throws EntityNotFoundException
     */
    public List<Trip> findAll(Vehicle vehicle) throws EntityNotFoundException {
        try{
            return tripRepository.find(vehicle);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
