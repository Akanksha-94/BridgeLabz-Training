public class EmployeeManagementSystem {

    public static void main(String[] args) {

        // Creating employees
        Employee e1 = new Employee("Thamarai", 101, "Software Engineer");
        Employee e2 = new Employee("Rohan", 102, "Project Manager");

        // Display total employees
        Employee.displayTotalEmployees();

        // Check instanceof and display details
        if (e1 instanceof Employee) {
            e1.displayDetails();
        }

        if (e2 instanceof Employee) {
            e2.displayDetails();
        }
    }
}

class Employee {

    // 1. Static
    static String companyName = "Tech Solutions Inc.";
    static int totalEmployees = 0;

    // 3. Final variable
    private final int id;

    // Instance variables
    private String name;
    private String designation;

    // 2. Using this keyword
    public Employee(String name, int id, String designation) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        totalEmployees++;
    }

    // Static method
    public static void displayTotalEmployees() {
        System.out.println("Total Employees: " + totalEmployees);
    }

    // Method to display employee details
    public void displayDetails() {
        System.out.println("Company Name: " + companyName);
        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Designation: " + designation);
        System.out.println();
    }
}
