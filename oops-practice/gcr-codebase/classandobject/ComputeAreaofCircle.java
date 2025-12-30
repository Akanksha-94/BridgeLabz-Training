class Circle {
    double radius;

    // Method to calculate area
    void calculateArea() {
        double area = Math.PI * radius * radius;
        System.out.printf("Area of circle: %.4f\n", area);
    }

    // Method to calculate circumference
    void calculateCircumference() {
        double circumference = 2 * Math.PI * radius;
        System.out.printf("Circumference of circle: %.4f\n", circumference);
    }

    public static void main(String[] args) {
      
        Circle c = new Circle();

        // Assign radius
        c.radius = 2.5;


        // Display results
        c.calculateArea();
        c.calculateCircumference();
    }
}
