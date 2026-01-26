import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Employee implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private String department;
  private double salary;

  public Employee(int id, String name, String department, double salary) {
    this.id = id;
    this.name = name;
    this.department = department;
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: $" + salary;
  }
}

public class EmployeeSerialization {

  public static void saveEmployees(List<Employee> employees, String filename) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
      oos.writeObject(employees);
      System.out.println("Employees saved successfully to " + filename);
    } catch (IOException e) {
      System.out.println("Error saving employees: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static List<Employee> loadEmployees(String filename) {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
      @SuppressWarnings("unchecked")
      List<Employee> employees = (List<Employee>) ois.readObject();
      return employees;
    } catch (IOException e) {
      System.out.println("Error loading employees: " + e.getMessage());
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.out.println("Employee class not found: " + e.getMessage());
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  public static void main(String[] args) {
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee(101, "John Doe", "Engineering", 85000));
    employees.add(new Employee(102, "Jane Smith", "Marketing", 75000));
    employees.add(new Employee(103, "Mike Johnson", "Sales", 70000));
    employees.add(new Employee(104, "Sarah Williams", "HR", 65000));

    String filename = "employees.dat";

    saveEmployees(employees, filename);

    List<Employee> loadedEmployees = loadEmployees(filename);
    System.out.println("\nLoaded Employees:");
    for (Employee emp : loadedEmployees) {
      System.out.println(emp);
    }
  }
}
