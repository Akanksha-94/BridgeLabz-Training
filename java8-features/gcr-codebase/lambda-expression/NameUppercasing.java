import java.util.*;

public class NameUppercasing {
    
    static class Employee {
        String id;
        String name;
        String department;
        
        Employee(String id, String name, String department) {
            this.id = id;
            this.name = name;
            this.department = department;
        }
        
        public String getName() { return name; }
        public String getId() { return id; }
        public String getDepartment() { return department; }
        
        @Override
        public String toString() {
            return id + " - " + name + " (" + department + ")";
        }
    }
    
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("E001", "john doe", "Engineering"),
            new Employee("E002", "jane smith", "Marketing"),
            new Employee("E003", "mike johnson", "Finance"),
            new Employee("E004", "sarah wilson", "HR"),
            new Employee("E005", "david brown", "Operations")
        );
        
        System.out.println("Original Employee Names:");
        employees.forEach(System.out::println);
        
        System.out.println("\nUppercase Names for HR Letter:");
        System.out.println("==============================");
        
        // Using String::toUpperCase method reference
        employees.stream()
                 .map(Employee::getName)
                 .map(String::toUpperCase)
                 .forEach(System.out::println);
        
        System.out.println("\nFormatted HR Letter Names:");
        System.out.println("=========================");
        
        // Complete formatted output with method references
        employees.stream()
                 .map(emp -> emp.getId() + " - " + emp.getName().toUpperCase() + " (" + emp.getDepartment() + ")")
                 .forEach(System.out::println);
        
        System.out.println("\nDepartment Names in Uppercase:");
        System.out.println("==============================");
        
        // Department names using method reference
        employees.stream()
                 .map(Employee::getDepartment)
                 .map(String::toUpperCase)
                 .distinct()
                 .forEach(System.out::println);
    }
}