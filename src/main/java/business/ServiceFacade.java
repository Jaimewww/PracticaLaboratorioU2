package business;

import model.Vehicle;
import service.ConsoleService;
import service.Notification;
import util.Constants;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * ServiceFacade class provides a facade for vehicle maintenance notifications.
 * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */

public class ServiceFacade {

    private final Notification notificationService = new Notification(new ConsoleService());

    /**
     *
     * Sends a maintenance notification if the vehicle's last maintenance date exceeds the maximum allowed days.
     * @param vehicle
     */
    public void maintenanceMessage(Vehicle vehicle) {
        if (ChronoUnit.DAYS.between(vehicle.getLastMaintenance(), LocalDate.now())> Constants.MAX_MAINTENANCE_DAYS){
            notificationService.NotifyUser("Pending maintenance since: " + vehicle.getLastMaintenance()+"\nURGENT MAINTENANCE REQUIRED");
        }
    }
}
