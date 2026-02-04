import java.util.*;

public class ProductSortDemo {

  static class Product {
    String id;
    String name;
    double price;
    double rating; // 0.0 - 5.0
    double discount; // percentage 0-100

    Product(String id, String name, double price, double rating, double discount) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.rating = rating;
      this.discount = discount;
    }

    @Override
    public String toString() {
      return String.format("%s{name='%s', price=%.2f, rating=%.1f, discount=%.1f%%}", id, name, price, rating,
          discount);
    }
  }

  public static void main(String[] args) {
    List<Product> products = new ArrayList<>();
    products.add(new Product("P1", "Wireless Mouse", 25.0, 4.5, 10));
    products.add(new Product("P2", "Mechanical Keyboard", 85.0, 4.8, 20));
    products.add(new Product("P3", "Webcam", 40.0, 4.2, 15));
    products.add(new Product("P4", "USB-C Dock", 120.0, 4.6, 5));

    System.out.println("Original order:");
    products.forEach(System.out::println);

    // Sort dynamically by price (ascending)
    products.sort(Comparator.comparingDouble(p -> p.price));
    System.out.println("\nSorted by price (asc):");
    products.forEach(System.out::println);

    // Sort dynamically by rating (descending)
    products.sort((a, b) -> Double.compare(b.rating, a.rating));
    System.out.println("\nSorted by rating (desc):");
    products.forEach(System.out::println);

    // Sort dynamically by discount (descending) for a sale campaign
    products.sort(Comparator.comparingDouble((Product p) -> p.discount).reversed());
    System.out.println("\nSorted by discount (desc):");
    products.forEach(System.out::println);
  }
}