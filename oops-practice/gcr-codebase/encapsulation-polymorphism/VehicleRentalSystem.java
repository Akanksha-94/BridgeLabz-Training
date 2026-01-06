import java.util.ArrayList;
import java.util.List;

// Interface-
interface Insurable {
    double calculateInsurance();
    String getInsuranceDetails();
}

// Abstract class-
abstract class Vehicle {

    // Encapsulated fields-
    private String vehicleNumber;
    private String type;
    protected double rentalRate;

    // Sensitive detail (encapsulated)-
    private String insurancePolicyNumber;

    public Vehicle(String vehicleNumber, String type, double rentalRate, String insurancePolicyNumber) {
        this.vehicleNumber = vehicleNumber;
        this.type = type;
        this.rentalRate = rentalRate;
        this.insurancePolicyNumber = insurancePolicyNumber;
    }

    // Abstract method-
    public abstract double calculateRentalCost(int days);

    // Getters (Encapsulation)-
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getType() {
        return type;
    }

    // Insurance policy number is PROTECTED (read-only)-
    public String getInsurancePolicyNumber() {
        return insurancePolicyNumber;
    }
}

// Car class-
class Car extends Vehicle implements Insurable {

    public Car(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Car", rentalRate, insurancePolicyNumber);
    }

    @Override
    public double calculateRentalCost(int days) {
        return rentalRate * days;
    }

    @Override
    public double calculateInsurance() {
        return rentalRate * 0.10; // 10% insurance
    }

    @Override
    public String getInsuranceDetails() {
        return "Car Insurance (10% of daily rate)";
    }
}

// Bike class-
class Bike extends Vehicle implements Insurable {

    public Bike(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Bike", rentalRate, insurancePolicyNumber);
    }

    @Override
    public double calculateRentalCost(int days) {
        return rentalRate * days;
    }

    @Override
    public double calculateInsurance() {
        return rentalRate * 0.05; // 5% insurance
    }

    @Override
    public String getInsuranceDetails() {
        return "Bike Insurance (5% of daily rate)";
    }
}

// Truck class-
class Truck extends Vehicle implements Insurable {

    public Truck(String vehicleNumber, double rentalRate, String insurancePolicyNumber) {
        super(vehicleNumber, "Truck", rentalRate, insurancePolicyNumber);
    }

    @Override
    public double calculateRentalCost(int days) {
        return (rentalRate * days) + 2000; // extra loading charge
    }

    @Override
    public double calculateInsurance() {
        return rentalRate * 0.15; // 15% insurance
    }

    @Override
    public String getInsuranceDetails() {
        return "Truck Insurance (15% of daily rate)";
    }
}

// Main class-
public class VehicleRentalSystem {

    public static void main(String[] args) {

        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car("CAR101", 2000, "CAR-INS-001"));
        vehicles.add(new Bike("BIKE202", 800, "BIKE-INS-002"));
        vehicles.add(new Truck("TRUCK303", 5000, "TRUCK-INS-003"));

        int rentalDays = 5;

        for (Vehicle vehicle : vehicles) {

            double rentalCost = vehicle.calculateRentalCost(rentalDays);
            double insurance = 0;

            System.out.println("Vehicle Number: " + vehicle.getVehicleNumber());
            System.out.println("Vehicle Type: " + vehicle.getType());
            System.out.println("Rental Days: " + rentalDays);
            System.out.println("Rental Cost: " + rentalCost);

            if (vehicle instanceof Insurable) {
                Insurable insurableVehicle = (Insurable) vehicle;
                insurance = insurableVehicle.calculateInsurance();
                System.out.println(insurableVehicle.getInsuranceDetails());
                System.out.println("Insurance Cost: " + insurance);
            }

            System.out.println("Insurance Policy No: " + vehicle.getInsurancePolicyNumber());
            System.out.println("----------------------------------");
        }
    }
}
