public class Circle {

    // Private attribute for encapsulation
    private double radius;

    // Default constructor (constructor chaining)
    public Circle() {
        this(1.0); // default radius
    }

    // Parameterized constructor
    public Circle(double radius) {
        if (radius >= 0) { // simple validation
            this.radius = radius;
        } else {
            this.radius = 1.0; // default value
            System.out.println("Invalid radius. Using default 1.0");
        }
    }

    // Getter for radius
    public double getRadius() {
        return radius;
    }

    // Setter for radius
    public void setRadius(double radius) {
        if (radius >= 0) {
            this.radius = radius;
        } else {
            System.out.println("Radius cannot be negative. Keeping previous value: " + this.radius);
        }
    }

    // Method to calculate area
    public double getArea() {
        return Math.PI * radius * radius;
    }

    

    // Method to calculate circumference
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    // Main method to test Circle class
    public static void main(String[] args) {
        // Using default constructor
        Circle c1 = new Circle();
        System.out.println("Circle 1 (default):");
        System.out.println("Radius: " + c1.getRadius());
        System.out.println("Area: " + c1.getArea());
        System.out.println("Circumference: " + c1.getCircumference());
        System.out.println();

        // Using parameterized constructor
        Circle c2 = new Circle(5.0);
        System.out.println("Circle 2 (radius 5.0):");
        System.out.println("Radius: " + c2.getRadius());
        System.out.println("Area: " + c2.getArea());
        System.out.println("Circumference: " + c2.getCircumference());
        System.out.println();

        // Using setter to change radius
        c2.setRadius(10.0);
        System.out.println("Circle 2 (radius updated to 10.0):");
        System.out.println("Radius: " + c2.getRadius());
        System.out.println("Area: " + c2.getArea());
        System.out.println("Circumference: " + c2.getCircumference());
    }
}
