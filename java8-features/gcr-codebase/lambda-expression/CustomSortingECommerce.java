import java.util.*;

public class CustomSortingECommerce {
    
    static class Product {
        String name;
        double price;
        double rating;
        double discount;
        
        Product(String name, double price, double rating, double discount) {
            this.name = name;
            this.price = price;
            this.rating = rating;
            this.discount = discount;
        }
        
        @Override
        public String toString() {
            return String.format("%s - $%.2f (Rating: %.1f, Discount: %.0f%%)", 
                               name, price, rating, discount);
        }
    }
    
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", 999.99, 4.5, 10),
            new Product("Phone", 699.99, 4.8, 15),
            new Product("Tablet", 399.99, 4.2, 20),
            new Product("Headphones", 199.99, 4.6, 5),
            new Product("Watch", 299.99, 4.3, 25)
        );
        
        System.out.println("Original Products:");
        products.forEach(System.out::println);
        
        // Sort by price (ascending)
        System.out.println("\nSorted by Price (Low to High):");
        products.stream()
                .sorted((p1, p2) -> Double.compare(p1.price, p2.price))
                .forEach(System.out::println);
        
        // Sort by rating (descending)
        System.out.println("\nSorted by Rating (High to Low):");
        products.stream()
                .sorted((p1, p2) -> Double.compare(p2.rating, p1.rating))
                .forEach(System.out::println);
        
        // Sort by discount (descending)
        System.out.println("\nSorted by Discount (High to Low):");
        products.stream()
                .sorted((p1, p2) -> Double.compare(p2.discount, p1.discount))
                .forEach(System.out::println);
        
        // Complex sorting: Rating desc, then price asc
        System.out.println("\nSorted by Rating (desc) then Price (asc):");
        products.stream()
                .sorted((p1, p2) -> {
                    int ratingCompare = Double.compare(p2.rating, p1.rating);
                    return ratingCompare != 0 ? ratingCompare : Double.compare(p1.price, p2.price);
                })
                .forEach(System.out::println);
    }
}