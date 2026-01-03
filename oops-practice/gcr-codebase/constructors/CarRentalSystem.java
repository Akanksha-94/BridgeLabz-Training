public class CarRentalSystem {

    // Private attributes for encapsulation
    private String customerName;
    private String carModel;
    private int rentalDays;
    private double costPerDay;

    // Default constructor
    public CarRentalSystem() {
        this.customerName = "Unknown";
        this.carModel = "Standard";
        this.rentalDays = 1;
        this.costPerDay = 100.0; // default cost per day
    }

    // Parameterized constructor
    public CarRentalSystem(String customerName, String carModel, int rentalDays, double costPerDay) {
        this.customerName = customerName;
        this.carModel = carModel;
        if (rentalDays > 0) {
            this.rentalDays = rentalDays;
        } else {
            this.rentalDays = 1;
            System.out.println("Invalid rental days. Set to 1 by default.");
        }
        if (costPerDay > 0) {
            this.costPerDay = costPerDay;
        } else {
            this.costPerDay = 100.0;
            System.out.println("Invalid cost per day. Set to 100.0 by default.");
        }
    }

    // Copy constructor
    public CarRentalSystem(CarRentalSystem other) {
        this.customerName = other.customerName;
        this.carModel = other.carModel;
        this.rentalDays = other.rentalDays;
        this.costPerDay = other.costPerDay;
    }

    // Getters and setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        if (rentalDays > 0) {
            this.rentalDays = rentalDays;
        } else {
            System.out.println("Invalid rental days. Keeping previous value: " + this.rentalDays);
        }
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        if (costPerDay > 0) {
            this.costPerDay = costPerDay;
        } else {
            System.out.println("Invalid cost per day. Keeping previous value: " + this.costPerDay);
        }
    }

    // Method to calculate total cost
    public double calculateTotalCost() {
        return rentalDays * costPerDay;
    }

    // Method to display rental info
    public void displayRentalInfo() {
        System.out.println("Customer: " + customerName + ", Car Model: " + carModel +
                           ", Rental Days: " + rentalDays + ", Cost per Day: " + costPerDay +
                           ", Total Cost: " + calculateTotalCost());
    }

    // Main method to test CarRental class
    public static void main(String[] args) {
        // Default rental
        CarRentalSystem rental1 = new CarRentalSystem();
        rental1.displayRentalInfo();

        // Parameterized rental
        CarRentalSystem rental2 = new CarRentalSystem("Alice", "SUV", 5, 250.0);
        rental2.displayRentalInfo();

        // Copy constructor
        CarRentalSystem rental3 = new CarRentalSystem(rental2);
        rental3.setCustomerName("Bob"); // Change customer for the copy
        rental3.displayRentalInfo();
    }
}
