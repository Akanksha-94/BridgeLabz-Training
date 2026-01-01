public class ShoppingCartSystem {

    public static void main(String[] args) {

        // Creating product objects
        Product p1 = new Product("P001", "Laptop", 1200.0, 5);
        Product p2 = new Product("P002", "Smartphone", 800.0, 10);

        // Updating static discount for all products
        Product.updateDiscount(10.0);

        // Check instanceof and display details
        if (p1 instanceof Product) {
            p1.displayProductDetails();
        }

        if (p2 instanceof Product) {
            p2.displayProductDetails();
        }
    }
}

class Product {

    // 1. Static variable (shared discount for all products)
    static double discount;

    // 3. Final variable for Product ID
    private final String productID;

    // Instance variables
    private String productName;
    private double price;
    private int quantity;

    // 2. Using THIS in constructor
    public Product(String productID, String productName, double price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    // Static Method to update discount
    public static void updateDiscount(double d) {
        discount = d;
    }

    // Display Product Details
    public void displayProductDetails() {
        if (this instanceof Product) {
            System.out.println("Product ID: " + productID);
            System.out.println("Product Name: " + productName);
            System.out.println("Price: $" + price);
            System.out.println("Quantity: " + quantity);
            System.out.println("Discount: " + discount + "%");
            double discountedPrice = price - (price * discount / 100);
            System.out.println("Price after Discount: $" + discountedPrice);
            System.out.println();
        }
    }
}
