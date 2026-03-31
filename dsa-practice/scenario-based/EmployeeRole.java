abstract class Employee {
    private final String name;
    private final double salary;
    private final double bonus;

    protected Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
        this.bonus = calculateBonus(salary);
    }

    protected double getSalary() {
        return salary;
    }

    protected abstract double calculateBonus(double salary);

    public double getBonus() {
        return bonus;
    }
}

class Manager extends Employee {
    public Manager(String name, double salary) {
        super(name, salary);
    }

    @Override
    protected double calculateBonus(double salary) {
        return salary * 0.10;
    }
}

class Developer extends Employee {
    public Developer(String name, double salary) {
        super(name, salary);
    }

    @Override
    protected double calculateBonus(double salary) {
        return salary > 50000.0 ? salary * 0.05 : 0.0;
    }
}

public class EmployeeRole {
    public static void main(String[] args) {
        Employee manager = new Manager("Alice", 80000);
        System.out.printf("%.2f%n", manager.getBonus());

        Employee dev1 = new Developer("Bob", 60000);
        System.out.printf("%.2f%n", dev1.getBonus());

        Employee dev2 = new Developer("Carol", 40000);
        System.out.printf("%.2f%n", dev2.getBonus());
    }
}