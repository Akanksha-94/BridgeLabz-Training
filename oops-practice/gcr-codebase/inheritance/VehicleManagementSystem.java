//  Vehicle Management System with Hybrid Inheritance

// Interface
interface Refuelable {
    void refuel();
}

// Superclass
class Vehicle {
    int maxSpeed;
    String model;

    void displayVehicleInfo() {
        System.out.println("Model: " + model);
        System.out.println("Max Speed: " + maxSpeed + " km/h");
    }
}

// Electric Vehicle
class ElectricVehicle extends Vehicle {

    void charge() {
        System.out.println("Charging the electric vehicle");
    }
}

// Petrol Vehicle
class PetrolVehicle extends Vehicle implements Refuelable {

    @Override
    public void refuel() {
        System.out.println("Refueling petrol vehicle");
    }
}

// Main class
public class VehicleManagementSystem {
    public static void main(String[] args) {

        ElectricVehicle ev = new ElectricVehicle();
        ev.model = "Tesla Model 3";
        ev.maxSpeed = 200;

        PetrolVehicle pv = new PetrolVehicle();
        pv.model = "Honda City";
        pv.maxSpeed = 180;

        System.out.println("Electric Vehicle:");
        ev.displayVehicleInfo();
        ev.charge();

        System.out.println("\nPetrol Vehicle:");
        pv.displayVehicleInfo();
        pv.refuel();
    }
}

