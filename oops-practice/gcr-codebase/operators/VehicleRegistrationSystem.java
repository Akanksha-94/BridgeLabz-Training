class Vehicle {
    static double registrationFee = 150.0;      // Static variable common to all vehicles
    
    String ownerName;
    String vehicleType;
    final String registrationNumber;            // Final variable (cannot be changed)

    // Constructor using 'this'
    Vehicle(String ownerName, String vehicleType, String registrationNumber) {
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
        this.registrationNumber = registrationNumber;
    }

    // Static method to update registration fee
    static void updateRegistrationFee(double newFee) {
        registrationFee = newFee;
    }

    // Method to display details
    void displayDetails(Object obj) {
        if (obj instanceof Vehicle) {           // instanceof check
            Vehicle v = (Vehicle) obj;
            System.out.println("Owner Name: " + v.ownerName);
            System.out.println("Vehicle Type: " + v.vehicleType);
            System.out.println("Registration Number: " + v.registrationNumber);
            System.out.println("Registration Fee: $" + registrationFee);
            System.out.println();
        } else {
            System.out.println("Invalid Object");
        }
    }
}

public class VehicleRegistrationSystem {
    public static void main(String[] args) {

        Vehicle v1 = new Vehicle("Honest raj", "Sedan", "ABC123");
        Vehicle v2 = new Vehicle("Price danish", "SUV", "XYZ789");

        v1.displayDetails(v1);
        v2.displayDetails(v2);
    }
}
