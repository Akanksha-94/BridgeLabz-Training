public class Person {

    // Private attributes
    private String name;
    private int age;
    private String email;

    // Default constructor
    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.email = "Unknown";
    }

    // Parameterized constructor
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Copy constructor
    public Person(Person other) {
        this.name = other.name;
        this.age = other.age;
        this.email = other.email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0) {  // simple validation
            this.age = age;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method to display person info
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Email: " + email);
    }

    // Main method to test the class
    public static void main(String[] args) {

        // Create a person using parameterized constructor
        Person p1 = new Person("Alice", 25, "alice@example.com");

        p1.displayInfo(); // Output: Name: Alice, Age: 25, Email: alice@example.com



        // Create a copy of the above person using copy constructor
        Person p2 = new Person(p1);

        p2.displayInfo(); // Output: Name: Alice, Age: 25, Email: alice@example.com



        // Create a person using default constructor
        Person p3 = new Person();
        
        p3.displayInfo(); // Output: Name: Unknown, Age: 0, Email: Unknown
    }
}
