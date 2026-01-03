// Restaurant Management System with Hybrid Inheritance
// Interface
interface Worker {
    void performDuties();
}

// Superclass
class Person {
    String name;
    int id;

    void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
    }
}

// Chef subclass
class Chef extends Person implements Worker {

    @Override
    public void performDuties() {
        System.out.println("Duty: Cooking food");
    }
}

// Waiter subclass
class Waiter extends Person implements Worker {

    @Override
    public void performDuties() {
        System.out.println("Duty: Serving food to customers");
    }
}

// Main class
public class RestaurantManagementSystem {
    public static void main(String[] args) {

        Worker chef = new Chef();
        ((Chef) chef).name = "Ramesh";
        ((Chef) chef).id = 101;

        Worker waiter = new Waiter();
        ((Waiter) waiter).name = "Suresh";
        ((Waiter) waiter).id = 102;

        System.out.println("Chef Details:");
        ((Chef) chef).displayDetails();
        chef.performDuties();

        System.out.println("\nWaiter Details:");
        ((Waiter) waiter).displayDetails();
        waiter.performDuties();
    }
}
