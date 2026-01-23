import java.util.*;

/**
 * Group Objects by Property
 * Given a list of Employee objects, group them by their department using
 * Map<Department, List<Employee>>.
 * Example: Employees: [Alice (HR), Bob (IT), Carol (HR)] → Output: HR: [Alice,
 * Carol], IT: [Bob]
 */
public class GroupObjectsByProperty {
  static class Employee {
    String name;
    String department;
    double salary;

    Employee(String name, String department, double salary) {
      this.name = name;
      this.department = department;
      this.salary = salary;
    }

    @Override
    public String toString() {
      return name + " ($" + salary + ")";
    }
  }

  static class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
      this.name = name;
      this.category = category;
      this.price = price;
    }

    @Override
    public String toString() {
      return name + " ($" + price + ")";
    }
  }

  // Group employees by department
  public static Map<String, List<Employee>> groupEmployeesByDepartment(List<Employee> employees) {
    Map<String, List<Employee>> grouped = new HashMap<>();

    for (Employee employee : employees) {
      grouped.computeIfAbsent(employee.department, k -> new ArrayList<>())
          .add(employee);
    }

    return grouped;
  }

  // Alternative using streams
  public static Map<String, List<Employee>> groupEmployeesByDepartmentStream(List<Employee> employees) {
    return employees.stream()
        .collect(java.util.stream.Collectors.groupingBy(e -> e.department));
  }

  // Group products by category
  public static Map<String, List<Product>> groupProductsByCategory(List<Product> products) {
    Map<String, List<Product>> grouped = new HashMap<>();

    for (Product product : products) {
      grouped.computeIfAbsent(product.category, k -> new ArrayList<>())
          .add(product);
    }

    return grouped;
  }

  // Group products by category using streams
  public static Map<String, List<Product>> groupProductsByCategoryStream(List<Product> products) {
    return products.stream()
        .collect(java.util.stream.Collectors.groupingBy(p -> p.category));
  }

  public static void main(String[] args) {
    System.out.println("=== Group Objects by Property ===\n");

    // Example 1: Group employees by department
    System.out.println("Example 1: Group Employees by Department");
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee("Alice", "HR", 60000));
    employees.add(new Employee("Bob", "IT", 75000));
    employees.add(new Employee("Carol", "HR", 62000));
    employees.add(new Employee("David", "Finance", 70000));
    employees.add(new Employee("Eve", "IT", 78000));
    employees.add(new Employee("Frank", "HR", 59000));

    System.out.println("Employees:");
    for (Employee emp : employees) {
      System.out.println("  " + emp.name + " - " + emp.department + " - $" + emp.salary);
    }

    Map<String, List<Employee>> groupedByDept = groupEmployeesByDepartment(employees);
    System.out.println("\nGrouped by Department:");
    groupedByDept.forEach((department, empList) -> {
      System.out.println(department + ":");
      empList.forEach(emp -> System.out.println("  " + emp));
    });

    // Example 2: Using streams
    System.out.println("\n\nExample 2: Same using Stream API");
    Map<String, List<Employee>> groupedByDeptStream = groupEmployeesByDepartmentStream(employees);
    System.out.println("Grouped by Department (using streams):");
    groupedByDeptStream.forEach((department, empList) -> {
      System.out.println(department + ": " + empList);
    });

    // Example 3: Group products by category
    System.out.println("\n\nExample 3: Group Products by Category");
    List<Product> products = new ArrayList<>();
    products.add(new Product("Laptop", "Electronics", 1000));
    products.add(new Product("Shirt", "Clothing", 50));
    products.add(new Product("Phone", "Electronics", 800));
    products.add(new Product("Pants", "Clothing", 60));
    products.add(new Product("Monitor", "Electronics", 300));
    products.add(new Product("Book", "Entertainment", 20));
    products.add(new Product("Movie", "Entertainment", 15));

    System.out.println("Products:");
    for (Product prod : products) {
      System.out.println("  " + prod.name + " - " + prod.category + " - $" + prod.price);
    }

    Map<String, List<Product>> groupedByCategory = groupProductsByCategory(products);
    System.out.println("\nGrouped by Category:");
    groupedByCategory.forEach((category, prodList) -> {
      System.out.println(category + ":");
      prodList.forEach(prod -> System.out.println("  " + prod));
    });

    // Example 4: Statistics after grouping
    System.out.println("\n\nExample 4: Department Statistics");
    Map<String, List<Employee>> deptGroups = groupEmployeesByDepartment(employees);
    System.out.println("Department Summary:");
    deptGroups.forEach((department, empList) -> {
      double avgSalary = empList.stream()
          .mapToDouble(e -> e.salary)
          .average()
          .orElse(0);
      System.out.println(department + " - Count: " + empList.size() +
          ", Avg Salary: $" + String.format("%.2f", avgSalary));
    });

    // Example 5: Single group
    System.out.println("\n\nExample 5: Single Department");
    List<Employee> singleDeptEmployees = new ArrayList<>();
    singleDeptEmployees.add(new Employee("Tom", "Admin", 55000));
    singleDeptEmployees.add(new Employee("Jerry", "Admin", 56000));

    Map<String, List<Employee>> singleGrouped = groupEmployeesByDepartment(singleDeptEmployees);
    System.out.println("Grouped:");
    singleGrouped.forEach((department, empList) -> {
      System.out.println(department + ": " + empList);
    });

    // Example 6: Empty list
    System.out.println("\n\nExample 6: Empty Employee List");
    List<Employee> emptyEmployees = new ArrayList<>();
    Map<String, List<Employee>> emptyGrouped = groupEmployeesByDepartment(emptyEmployees);
    System.out.println("Grouped (empty): " + emptyGrouped);
  }
}
