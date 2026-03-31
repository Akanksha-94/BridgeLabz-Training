import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DatabaseToCSV {

    static class Employee {
        int empId;
        String name;
        String department;
        double salary;

        Employee(int empId, String name, String department, double salary) {
            this.empId = empId;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    }

    public static void main(String[] args) {
        String outputFile = "io-programming\\gcr-codebase\\csv-file-handling\\employees_from_db.csv";
        generateCSVReportFromDatabase(outputFile);
    }

    static void generateCSVReportFromDatabase(String outputFile) {
        List<Employee> employeeDatabase = new ArrayList<>();
        employeeDatabase.add(new Employee(1001, "John Doe", "Engineering", 95000));
        employeeDatabase.add(new Employee(1002, "Sarah Khan", "Marketing", 65000));
        employeeDatabase.add(new Employee(1003, "Michael Chen", "Finance", 85000));
        employeeDatabase.add(new Employee(1004, "Emily White", "Engineering", 92000));
        employeeDatabase.add(new Employee(1005, "David Lee", "Human Resources", 60000));

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("Employee ID,Name,Department,Salary");
            
            for (Employee emp : employeeDatabase) {
                writer.printf("%d,%s,%s,%.2f%n", emp.empId, emp.name, emp.department, emp.salary);
            }

            System.out.println("CSV report generated successfully: " + outputFile);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}