import java.util.ArrayList;
import java.util.List;

// Interface for GPS functionality-
interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}

// Abstract class Vehicle-
abstract class Vehicle {

    // Encapsulated fields-
    private String vehicleId;
    private String driverName;
    protected double ratePerKm;

    private String currentLocation;

    public Vehicle(String vehicleId, String driverName, double ratePerKm, String currentLocation) {
        this.vehicleId = vehicleId;
        this.driverName = driverName;
        this.ratePerKm = ratePerKm;
        this.currentLocation = currentLocation;
    }

    // Abstract method-
    public abstract double calculateFare(double distance);

    // Concrete method-
    public void getVehicleDetails() {
        System.out.println("Vehicle ID: " + vehicleId);
        System.out.println("Driver Name: " + driverName);
        System.out.println("Rate per km: " + ratePerKm);
        System.out.println("Current Location: " + currentLocation);
    }

    // Encapsulation: getters and setters-
    public String getVehicleId() { return vehicleId; }
    public String getDriverName() { return driverName; }

    protected String getCurrentLocationField() { return currentLocation; }
    protected void setCurrentLocationField(String location) { this.currentLocation = location; }
}

// Car class-
class Car extends Vehicle implements GPS {

    public Car(String vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm, currentLocation);
    }

    @Override
    public double calculateFare(double distance) {
        return ratePerKm * distance; // standard fare
    }

    @Override
    public String getCurrentLocation() {
        return getCurrentLocationField();
    }

    @Override
    public void updateLocation(String newLocation) {
        setCurrentLocationField(newLocation);
    }
}

// Bike class-
class Bike extends Vehicle implements GPS {

    public Bike(String vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm, currentLocation);
    }

    @Override
    public double calculateFare(double distance) {
        return ratePerKm * distance * 0.8; // cheaper than car
    }

    @Override
    public String getCurrentLocation() {
        return getCurrentLocationField();
    }

    @Override
    public void updateLocation(String newLocation) {
        setCurrentLocationField(newLocation);
    }
}

// Auto class-
class Auto extends Vehicle implements GPS {

    public Auto(String vehicleId, String driverName, double ratePerKm, String currentLocation) {
        super(vehicleId, driverName, ratePerKm, currentLocation);
    }

    @Override
    public double calculateFare(double distance) {
        return ratePerKm * distance * 0.9; // slightly cheaper than car
    }

    @Override
    public String getCurrentLocation() {
        return getCurrentLocationField();
    }

    @Override
    public void updateLocation(String newLocation) {
        setCurrentLocationField(newLocation);
    }
}

// Main class-
public class RideHailingApp {

    // Polymorphic method to calculate fares-
    public static void processRides(List<Vehicle> vehicles, double distance) {
        for (Vehicle vehicle : vehicles) {
            vehicle.getVehicleDetails();
            double fare = vehicle.calculateFare(distance);
            System.out.println("Fare for " + distance + " km: " + fare);
            System.out.println("-----------------------------");
        }
    }

    public static void main(String[] args) {

        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car("CAR101", "Amit", 15, "Downtown"));
        vehicles.add(new Bike("BIKE202", "Riya", 10, "City Center"));
        vehicles.add(new Auto("AUTO303", "Suresh", 12, "Market"));

        double distance = 10; // km
        processRides(vehicles, distance);

        // Demonstrating GPS-
        Vehicle car = vehicles.get(0);
        if (car instanceof GPS) {
            GPS gpsCar = (GPS) car;
            System.out.println("Current Car Location: " + gpsCar.getCurrentLocation());
            gpsCar.updateLocation("Uptown");
            System.out.println("Updated Car Location: " + gpsCar.getCurrentLocation());
        }
    }
}
