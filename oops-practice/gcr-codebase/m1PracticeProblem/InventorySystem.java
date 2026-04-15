import java.io.*;
import java.util.*;

abstract class Product {
  String name;
  double price;
  int quantity;

  Product(String n, double p, int q) {
    name = n;
    price = p;
    quantity = q;
  }

  abstract void display();

  double getValue() {
    return price * quantity;
  }
}

class Electronics extends Product {
  int warranty;

  Electronics(String n, double p, int q, int w) {
    super(n, p, q);
    warranty = w;
  }

  void display() {
    System.out.println(name + " - Price: " + price + ", Quantity: " + quantity + ", Warranty: " + warranty + " months");
  }
}

class Clothing extends Product {
  String size;

  Clothing(String n, double p, int q, String s) {
    super(n, p, q);
    size = s;
  }

  void display() {
    System.out.println(name + " - Price: " + price + ", Quantity: " + quantity + ", Size: " + size);
  }
}

public class InventorySystem {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int num = Integer.parseInt(br.readLine().trim());
    List<Product> inventory = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      String line = br.readLine().trim();
      String[] parts = line.split(", ");
      String type = parts[0];
      String name = parts[1];
      double price = Double.parseDouble(parts[2]);
      int qty = Integer.parseInt(parts[3]);
      if (type.equals("Electronics")) {
        int warranty = Integer.parseInt(parts[4]);
        inventory.add(new Electronics(name, price, qty, warranty));
        System.out.println("Product added to inventory: " + name);
      } else if (type.equals("Clothing")) {
        String size = parts[4];
        inventory.add(new Clothing(name, price, qty, size));
        System.out.println("Product added to inventory: " + name);
      }
    }
    System.out.println("Inventory:");
    for (Product p : inventory) {
      p.display();
    }
    double total = 0;
    for (Product p : inventory) {
      total += p.getValue();
    }
    System.out.println("Total value of the inventory: " + String.format("%.2f", total));
  }
}