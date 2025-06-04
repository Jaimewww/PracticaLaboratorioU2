package view;

import business.ServiceFacade;
import business.VehicleFacade;
import data.VehicleRepository;
import exception.EntityNotFoundException;
import model.*;
import service.ConsoleInputService;
import service.Reader;
import util.CostCalculator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/*
    * VehicleManagement class provides methods for managing vehicles and trips.
    * @Author [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]
 */
public class VehicleManagement {
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public int mainMenu(Reader reader) throws NumberFormatException {
        while (true) {
            try{
                System.out.println("=== Delivery Fleet Management ===");
                System.out.println("Authors: [Miguel Armas, Soledad Buri, Jaime Landazuri, Cael Soto]");
                System.out.println("1. Register Vehicle");
                System.out.println("2. Register Trip");
                System.out.println("3. Update Maintenance date");
                System.out.println("4. Vehicle List");
                System.out.println("5. Exit");
                int option = reader.getInt("Select an option: ");
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
     * @param reader
     * @return A subclass of Vehicle based on user input.
     */
    public Vehicle enterVehicleType(Reader reader) {
        while (true) {
            System.out.println("=== Register Vehicle ===");
            Vehicle vehicle;
            System.out.println("1. Truck\n2. Motorcycle\n3. Pickup");
            int vehicleType = reader.getInt("Enter vehicle type: ");
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
     * @param reader
     * @param vehicle
     */
    public void enterVehicleData(Reader reader, Vehicle vehicle) {
        while (true) {
            try {
                System.out.println("=== Enter Vehicle Data ===");
                String plate = reader.getString("Enter vehicle plate. Format [A-Z - 0-9]:");
                vehicle.setPlate(plate);
            } catch (IllegalArgumentException e){
                System.out.println("Invalid plate format. Please try again.");
                continue;
            }

            double fuelConsumption = reader.getDouble("Enter fuel consumption (L/100Km):");
            vehicle.setFuelConsumption(fuelConsumption);

            double maintenanceCost = reader.getDouble("Enter maintenance cost ($):");
            vehicle.setMaintenanceCost(maintenanceCost);

            double carryingCapacity = reader.getDouble("Enter carrying capacity (Kg):");
            vehicle.setCarryingCapacity(carryingCapacity);

            try {
                LocalDate lastMaintenanceDate = LocalDate.parse(reader.getString(
                        "Enter last maintenance date (YYYY-MM-DD):"));
                vehicle.setLastMaintenance(lastMaintenanceDate);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
            addExtraParameters(vehicle, reader);
            System.out.println("Vehicle registered successfully: " + vehicle.getPlate());
        }

    }

    public void enterVehicleDetails(Truck truck, Reader reader) {
        int axleCount = reader.getInt("Enter axle count for the Truck:");
        truck.setAxleCount(axleCount);
    }

    public void enterVehicleDetails(Motorcycle motorcycle, Reader reader) {
        double engineDisplacement = reader.getDouble("Enter engine displacement for the Motorcycle (cm^3):");
        motorcycle.setEngineDisplacement(engineDisplacement);
    }

    public void enterVehicleDetails(PickupTruck pickup, Reader reader) {
        boolean fourWheelTraction = reader.getBoolean("Does the Pickup Truck have 4x4 traction? (y/n)");
        pickup.setFourWheelTraction(fourWheelTraction);
    }

    /**
     *
     * This method collects trip data from the user and sets it in the Trip object.
     * @param trip
     * @param reader
     * @param facade
     */
    public void enterTripData(Trip trip, Reader reader, VehicleFacade facade) {
        while(true){
            try {
                System.out.println("=== Register Trip ===");
                String plate = reader.getString("Enter vehicle plate. Format [A-Z - 0-9]:");
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
                trip.setTripDate(LocalDate.parse(reader.getString("Enter trip date (YYYY-MM-DD):")));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                continue;
            }

            trip.setDistanceTravelled(reader.getDouble("Enter distance traveled (Km):"));
            break;
        }
        System.out.println("Trip registered for vehicle with plate: " + trip.getVehiclePlate() +
                " on date: " + trip.getTripDate() + " with distance: " + trip.getDistanceTravelled() + " Km.");
    }

    public void showTripCost(Trip trip, VehicleFacade vehicleFacade) {
        try{
            Vehicle vehicle = vehicleFacade.find(trip.getVehiclePlate());
            double cost = CostCalculator.calculateTripCost(vehicle, trip);
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
    public static void handleMenuOption(VehicleFacade facade, Reader reader) {

        try {
            VehicleManagement management = new VehicleManagement();
            while(true){
                int option = management.mainMenu(reader);
                switch (option) {
                    case 1:
                        Vehicle vehicle = management.enterVehicleType(reader);
                        management.enterVehicleData(reader, vehicle);
                        management.addExtraParameters(vehicle, reader);
                        facade.create(vehicle);
                        System.out.println("Vehicle registered successfully: " + vehicle.getPlate());

                        break;
                    case 2:
                        Trip trip = new Trip();
                        management.enterTripData(trip, reader, facade);
                        management.showTripCost(trip, facade);
                        break;
                    case 3:
                        System.out.println("Enter vehicle plate to update maintenance date:");
                        String plate = reader.getString("Enter vehicle plate: ");
                        try{
                            facade.find(plate);
                            LocalDate newMaintenanceDate = LocalDate.parse(reader.getString("Enter new maintenance date (YYYY-MM-DD):"));
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
     * @param reader
     */
    public void addExtraParameters(Vehicle vehicle, Reader reader) {
        switch (vehicle.getType()){
            case TRUCK:
                enterVehicleDetails((Truck) vehicle, reader);
                break;
            case MOTORCYCLE:
                enterVehicleDetails((Motorcycle) vehicle, reader);
                break;
            case PICKUP:
                enterVehicleDetails((PickupTruck) vehicle, reader);
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader(new ConsoleInputService(scanner));
        VehicleFacade facade = new VehicleFacade();
        handleMenuOption(facade, reader);
    }
}
