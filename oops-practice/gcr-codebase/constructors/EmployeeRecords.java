public class EmployeeRecords {

    public int employeeID;        // public
    protected String department;  // protected
    private double salary;        // private

    public EmployeeRecords(int employeeID, String department, double salary) {
        this.employeeID = employeeID;
        this.department = department;
        this.salary = salary;
    }

    // Public method to modify salary
    public void updateSalary(double salary) {
        if (salary > 0)
            this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void display() {
        System.out.println("Employee ID: " + employeeID + ", Department: " + department + ", Salary: " + salary);
    }
}

// Subclass
class Manager extends EmployeeRecords {

    public Manager(int employeeID, String department, double salary) {
        super(employeeID, department, salary);
    }

    public void showManagerInfo() {
        System.out.println("Accessing public Employee ID: " + employeeID);
        System.out.println("Accessing protected Department: " + department);
    }
}
