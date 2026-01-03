public class VehicleRegistration {

    // Instance variables (unique to each vehicle)
    private String ownerName;
    private String vehicleType;

    // Class variable (shared among all vehicles)
    private static double registrationFee = 100.0; // default fee

    // Parameterized constructor
    public VehicleRegistration(String ownerName, String vehicleType) {
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
    }

    // Instance method: displays vehicle details
    public void displayVehicleDetails() {
        System.out.println("Owner: " + ownerName + ", Vehicle Type: " + vehicleType + ", Registration Fee: $" + registrationFee);
    }

    // Class method: updates registration fee for all vehicles
    public static void updateRegistrationFee(double newFee) {
        if (newFee > 0) {
            registrationFee = newFee;
        } else {
            System.out.println("Invalid fee. Registration fee remains $" + registrationFee);
        }
    }

    // Main method to test the Vehicle class
    public static void main(String[] args) {
        // Create vehicles
        VehicleRegistration v1 = new VehicleRegistration("Alice", "Car");
        VehicleRegistration v2 = new VehicleRegistration("Bob", "Motorcycle");

        // Display details before updating fee
        v1.displayVehicleDetails();
        v2.displayVehicleDetails();

        // Update registration fee for all vehicles
        VehicleRegistration.updateRegistrationFee(150.0);

        System.out.println("\nAfter updating registration fee:");

        // Display details after updating fee
        v1.displayVehicleDetails();
        v2.displayVehicleDetails();
    }
}
