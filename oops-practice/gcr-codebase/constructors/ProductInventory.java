public class Product {

    // Instance variables (unique to each product)
    private String productName;
    private double price;

    // Class variable (shared among all products)
    private static int totalProducts = 0;

    // Parameterized constructor
    public Product(String productName, double price) {
        this.productName = productName;
        this.price = price;
        totalProducts++; // increment total products whenever a new product is created
    }

    // Instance method: displays details of this product
    public void displayProductDetails() {
        System.out.println("Product Name: " + productName + ", Price: $" + price);
    }

    // Class method (static): displays total number of products
    public static void displayTotalProducts() {
        System.out.println("Total Products Created: " + totalProducts);
    }

    // Main method to test the class
    public static void main(String[] args) {
        // Create products
        Product p1 = new Product("Laptop", 1200.0);
        Product p2 = new Product("Smartphone", 800.0);
        Product p3 = new Product("Headphones", 150.0);

        // Display product details (instance method)
        p1.displayProductDetails();
        p2.displayProductDetails();
        p3.displayProductDetails();

        // Display total products (class method)
        Product.displayTotalProducts();
    }
}
