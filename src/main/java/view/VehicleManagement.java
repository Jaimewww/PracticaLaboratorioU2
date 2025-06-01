package view;

import business.ServiceFacade;
import business.VehicleFacade;
import data.VehicleRepository;
import exception.EntityNotFoundException;
import model.*;
import service.InputService;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/*
    * VehicleManagement class provides methods for managing vehicles and trips.
    * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]s
 */
public class VehicleManagement {

    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public int mainMenu(InputService inputService) throws NumberFormatException {
        while (true) {
            try{
                System.out.println("=== Delivery Fleet Management ===");
                System.out.println("Authors: [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]");
                System.out.println("1. Register Vehicle");
                System.out.println("2. Register Trip");
                System.out.println("3. Update Maintenance date");
                System.out.println("4. Vehicle List");
                System.out.println("5. Exit");
                int option = inputService.getInt("Select an option: ");
                if (option < 1 || option > 5) {
                    System.out.println("Invalid option");
                    continue;
                }
                return option;

            } catch (NumberFormatException e){
                System.out.println("Invalid input. Please enter a valid number.");
                clearConsole();
            }
        }
    }

    /**
     *
     * @param inputService
     * @return A subclass of Vehicle based on user input.
     */
    public Vehicle enterVehicleType(InputService inputService) {
        while (true) {
            System.out.println("=== Register Vehicle ===");
            Vehicle vehicle;
            System.out.println("1. Truck\n2. Motorcycle\n3. Pickup");
            int vehicleType = inputService.getInt("Enter vehicle type: ");
            switch (vehicleType) {
                case 1:
                    vehicle = new Truck();
                    break;
                case 2:
                    vehicle = new Motorcycle();
                    break;
                case 3:
                    vehicle = new PickupTruck();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    continue;
            }
            return vehicle;
        }
    }

    /**
     *
     * This method collects vehicle data from the user and sets it in the Vehicle object.
     * @param inputService
     * @param vehicle
     */
    public void enterVehicleData(InputService inputService, Vehicle vehicle) {
        while (true) {
            try {
                System.out.println("=== Enter Vehicle Data ===");
                String plate = inputService.getString("Enter vehicle plate. Format [A-Z - 0-9]:");
                vehicle.setPlate(plate);
            } catch (IllegalArgumentException e){
                System.out.println("Invalid plate format. Please try again.");
                continue;
            }

            double carryingCapacity = inputService.getDouble("Enter carrying capacity (Kg):");
            vehicle.setCarryingCapacity(carryingCapacity);

            try {
                LocalDate lastMaintenanceDate = LocalDate.parse(inputService.getString(
                        "Enter last maintenance date (YYYY-MM-DD):"));
                vehicle.setLastMaintenance(lastMaintenanceDate);
                System.out.println("Vehicle registered successfully: " + vehicle.getPlate());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

    }

    public void enterVehicleDetails(Truck truck, InputService inputService) {
        truck.enterDetails(inputService);
    }

    public void enterVehicleDetails(Motorcycle motorcycle, InputService inputService) {
        motorcycle.enterDetails(inputService);
    }

    public void enterVehicleDetails(PickupTruck pickup, InputService inputService) {
        pickup.enterDetails(inputService);
    }

    /**
     *
     * This method collects trip data from the user and sets it in the Trip object.
     * @param trip
     * @param inputService
     * @param facade
     */
    public void enterTripData(Trip trip,  InputService inputService, VehicleFacade facade) {
        while(true){
            try {
                System.out.println("=== Register Trip ===");
                String plate = inputService.getString("Enter vehicle plate. Format [A-Z - 0-9]:");
                try{
                    facade.find(plate);
                } catch (EntityNotFoundException e){
                    System.out.println("Vehicle not found with plate: " + plate);
                    continue;
                }
                trip.setVehiclePlate(plate);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid vehicle plate format. Please try again.");
                continue;
            }

            try {
                trip.setTripDate(LocalDate.parse(inputService.getString("Enter trip date (YYYY-MM-DD):")));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                continue;
            }

            trip.setDistanceTravelled(inputService.getDouble("Enter distance traveled (Km):"));
            break;
        }
        System.out.println("Trip registered for vehicle with plate: " + trip.getVehiclePlate() +
                " on date: " + trip.getTripDate() + " with distance: " + trip.getDistanceTravelled() + " Km.");
    }

    public void showTripCost(Trip trip, VehicleFacade vehicleFacade) {
        try{
            Vehicle vehicle = vehicleFacade.find(trip.getVehiclePlate());
            double cost = util.CostCalculator.calculateTripCost(vehicle, trip);
            System.out.println("=== Trip Cost ===");
            System.out.println("Trip cost for vehicle with plate " + trip.getVehiclePlate() + ": $" + cost);
            System.out.println("Fuel cost: $" + vehicle.getFuelConsumption());
            System.out.println("Maintenance cost: $" + vehicle.getMaintenanceCost());
            ServiceFacade serviceFacade = new ServiceFacade();
            serviceFacade.maintenanceMessage(vehicle);
        } catch (EntityNotFoundException e) {
            System.out.println("Vehicle not found with plate: " + trip.getVehiclePlate());
        }
    }

    /**
     *
     * This method retrieves and displays a list of all vehicles registered in the system.
     */
    public void showVehicleList() {
        List<Vehicle> vehicles = VehicleRepository.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }
        ServiceFacade serviceFacade = new ServiceFacade();
        for (Vehicle vehicle : vehicles) {
            System.out.println("=== Vehicle Details ===");
            System.out.println(vehicle.toString());
            serviceFacade.maintenanceMessage(vehicle);
            System.out.println("==================================");
        }
    }

    /**
     *
     * This method handles the main menu options for vehicle management.
     * @param facade
     */
    public static void handleMenuOption(VehicleFacade facade){

        try {
            VehicleManagement management = new VehicleManagement();
            Scanner scanner = new Scanner(System.in);
            InputService inputService = new InputService(scanner);
            while(true){
                int option = management.mainMenu(inputService);
                switch (option) {
                    case 1:
                        Vehicle vehicle = management.enterVehicleType(inputService);
                        management.enterVehicleData(inputService, vehicle);
                        management.addExtraParameters(vehicle, inputService);
                        facade.create(vehicle);
                        System.out.println("Vehicle registered successfully: " + vehicle.getPlate());

                        break;
                    case 2:
                        Trip trip = new Trip();
                        management.enterTripData(trip, inputService, facade);
                        management.showTripCost(trip, facade);
                        break;
                    case 3:
                        System.out.println("Enter vehicle plate to update maintenance date:");
                        String plate = inputService.getString("Enter vehicle plate: ");
                        try{
                            facade.find(plate);
                            LocalDate newMaintenanceDate = LocalDate.parse(inputService.getString("Enter new maintenance date (YYYY-MM-DD):"));
                            facade.update(plate, newMaintenanceDate);
                            System.out.println("Maintenance date updated successfully for vehicle with plate: " + plate);

                        }catch(EntityNotFoundException e) {
                            System.out.println("Vehicle not found with plate: " + plate);
                        } catch (DateTimeParseException e) {
                            System.out.println("Wrong Format Date");
                        }catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        management.showVehicleList();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                }
            }
        } catch (IllegalArgumentException | DateTimeParseException | EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     *
     * This method adds extra parameters to the vehicle based on its type.
     * @param vehicle
     * @param inputService
     */
    public void addExtraParameters(Vehicle vehicle, InputService inputService) {
        switch (vehicle.getType()){
            case TRUCK:
                enterVehicleDetails((Truck) vehicle, inputService);
                break;
            case MOTORCYCLE:
                enterVehicleDetails((Motorcycle) vehicle, inputService);
                break;
            case PICKUP:
                enterVehicleDetails((PickupTruck) vehicle, inputService);
                break;
        }
    }

    public static void main(String[] args) {
        VehicleFacade facade = new VehicleFacade();
        handleMenuOption(facade);
    }
}
