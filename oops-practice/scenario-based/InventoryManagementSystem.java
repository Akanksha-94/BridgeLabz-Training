import java.util.*;

/**
 * Inventory Management System
 * Real Scenario: Warehouse staff track products, update stock, and generate
 * alerts.
 * 
 * Concepts Used:
 * - OOP: Product, Inventory
 * - Interface: AlertService
 * - Exception Handling: OutOfStockException, InvalidQuantityException
 */

// Custom Exceptions
class OutOfStockException extends Exception {
  public OutOfStockException(String message) {
    super(message);
  }
}

class InvalidQuantityException extends Exception {
  public InvalidQuantityException(String message) {
    super(message);
  }
}

// Alert interface
interface AlertService {
  void sendAlert(String message);
}

// Email Alert
class EmailAlert implements AlertService {
  private String emailAddress;

  public EmailAlert(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @Override
  public void sendAlert(String message) {
    System.out.println("📧 EMAIL ALERT to " + emailAddress + ": " + message);
  }
}

// SMS Alert
class SMSAlert implements AlertService {
  private String phoneNumber;

  public SMSAlert(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Override
  public void sendAlert(String message) {
    System.out.println("📱 SMS ALERT to " + phoneNumber + ": " + message);
  }
}

// Push Notification Alert
class PushNotificationAlert implements AlertService {
  private String userId;

  public PushNotificationAlert(String userId) {
    this.userId = userId;
  }

  @Override
  public void sendAlert(String message) {
    System.out.println("🔔 PUSH NOTIFICATION to " + userId + ": " + message);
  }
}

// Stock Record
class StockRecord {
  private String recordId;
  private String action; // ADD, REMOVE, ADJUST
  private int quantity;
  private long timestamp;
  private String reason;

  public StockRecord(String action, int quantity, String reason) {
    this.recordId = "REC" + System.currentTimeMillis();
    this.action = action;
    this.quantity = quantity;
    this.timestamp = System.currentTimeMillis();
    this.reason = reason;
  }

  @Override
  public String toString() {
    return String.format("%-15s | %-10s | Qty: %-6d | Reason: %-30s",
        recordId, action, quantity, reason);
  }
}

// Product class
class Product {
  private String productId;
  private String name;
  private double price;
  private int quantity;
  private int minStockLevel;
  private int maxStockLevel;
  private List<StockRecord> stockHistory;

  public Product(String productId, String name, double price, int initialQuantity,
      int minStockLevel, int maxStockLevel) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.quantity = initialQuantity;
    this.minStockLevel = minStockLevel;
    this.maxStockLevel = maxStockLevel;
    this.stockHistory = new ArrayList<>();
    this.stockHistory.add(new StockRecord("ADD", initialQuantity, "Initial stock"));
  }

  public String getProductId() {
    return productId;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public int getMinStockLevel() {
    return minStockLevel;
  }

  public int getMaxStockLevel() {
    return maxStockLevel;
  }

  public void addStock(int amount) throws InvalidQuantityException {
    if (amount <= 0) {
      throw new InvalidQuantityException("Amount must be positive");
    }
    if (quantity + amount > maxStockLevel) {
      System.out.println("⚠ Warning: Adding " + amount + " would exceed max level (" +
          maxStockLevel + "). Adding only " + (maxStockLevel - quantity));
      amount = maxStockLevel - quantity;
    }
    quantity += amount;
    stockHistory.add(new StockRecord("ADD", amount, "Stock replenishment"));
    System.out.println("✓ Added " + amount + " units to " + name);
  }

  public void removeStock(int amount) throws OutOfStockException, InvalidQuantityException {
    if (amount <= 0) {
      throw new InvalidQuantityException("Amount must be positive");
    }
    if (amount > quantity) {
      throw new OutOfStockException("Not enough stock! Available: " + quantity);
    }
    quantity -= amount;
    stockHistory.add(new StockRecord("REMOVE", amount, "Stock removal"));
    System.out.println("✓ Removed " + amount + " units from " + name);
  }

  public boolean isLowStock() {
    return quantity <= minStockLevel;
  }

  public boolean isOverstock() {
    return quantity >= maxStockLevel;
  }

  public List<StockRecord> getStockHistory() {
    return new ArrayList<>(stockHistory);
  }

  @Override
  public String toString() {
    return String.format("%-12s | %-25s | Price: $%-8.2f | Stock: %-6d | Min: %-6d | Max: %-6d",
        productId, name, price, quantity, minStockLevel, maxStockLevel);
  }
}

// Inventory class
class Inventory {
  private Map<String, Product> products;
  private List<AlertService> alertServices;

  public Inventory() {
    this.products = new HashMap<>();
    this.alertServices = new ArrayList<>();
  }

  public void addAlertService(AlertService service) {
    alertServices.add(service);
  }

  public void addProduct(Product product) {
    products.put(product.getProductId(), product);
    System.out.println("✓ Product added: " + product.getName());
  }

  public void updateStock(String productId, int quantity, String action)
      throws OutOfStockException, InvalidQuantityException {
    Product product = products.get(productId);
    if (product == null) {
      System.out.println("✗ Product not found: " + productId);
      return;
    }

    if ("ADD".equals(action)) {
      product.addStock(quantity);
    } else if ("REMOVE".equals(action)) {
      product.removeStock(quantity);
    }

    checkAndSendAlerts(product);
  }

  private void checkAndSendAlerts(Product product) {
    if (product.isLowStock()) {
      String message = "LOW STOCK ALERT: " + product.getName() +
          " has only " + product.getQuantity() + " units left!";
      sendAlert(message);
    }

    if (product.isOverstock()) {
      String message = "OVERSTOCK ALERT: " + product.getName() +
          " has " + product.getQuantity() + " units (Max: " +
          product.getMaxStockLevel() + ")";
      sendAlert(message);
    }
  }

  private void sendAlert(String message) {
    for (AlertService service : alertServices) {
      service.sendAlert(message);
    }
  }

  public void displayInventory() {
    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                       INVENTORY STATUS                            ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Product product : products.values()) {
      String status = "NORMAL";
      if (product.isLowStock())
        status = "LOW STOCK";
      if (product.isOverstock())
        status = "OVERSTOCK";

      System.out.println(product + " [" + status + "]");
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
  }

  public void displayProductDetails(String productId) {
    Product product = products.get(productId);
    if (product == null) {
      System.out.println("✗ Product not found: " + productId);
      return;
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    PRODUCT DETAILS                                ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");
    System.out.println("Product ID: " + product.getProductId());
    System.out.println("Name: " + product.getName());
    System.out.println("Price: $" + String.format("%.2f", product.getPrice()));
    System.out.println("Current Stock: " + product.getQuantity());
    System.out.println("Min Level: " + product.getMinStockLevel());
    System.out.println("Max Level: " + product.getMaxStockLevel());
    System.out.println("╟────────────────────────────────────────────────────────────────────╢");
    System.out.println("Stock History:");

    for (StockRecord record : product.getStockHistory()) {
      System.out.println("  " + record);
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");
  }

  public double getInventoryValue() {
    return products.values().stream()
        .mapToDouble(p -> p.getPrice() * p.getQuantity())
        .sum();
  }

  public int getTotalItems() {
    return products.values().stream()
        .mapToInt(Product::getQuantity)
        .sum();
  }
}

public class InventoryManagementSystem {
  public static void main(String[] args) {
    Inventory inventory = new Inventory();

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║        INVENTORY MANAGEMENT SYSTEM - DEMONSTRATION               ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

    // Add alert services
    inventory.addAlertService(new EmailAlert("manager@warehouse.com"));
    inventory.addAlertService(new SMSAlert("+1234567890"));
    inventory.addAlertService(new PushNotificationAlert("warehouse_admin"));

    // PHASE 1: Add products
    System.out.println("--- PHASE 1: Add Products ---");
    inventory.addProduct(new Product("P001", "Laptop", 999.99, 20, 5, 50));
    inventory.addProduct(new Product("P002", "Mouse", 25.99, 100, 20, 200));
    inventory.addProduct(new Product("P003", "Keyboard", 79.99, 50, 15, 100));
    inventory.addProduct(new Product("P004", "Monitor", 299.99, 15, 5, 40));
    inventory.addProduct(new Product("P005", "USB Cable", 9.99, 5, 20, 100));

    inventory.displayInventory();

    // PHASE 2: Low stock scenario
    System.out.println("--- PHASE 2: Low Stock Alert ---");
    try {
      inventory.updateStock("P005", 10, "REMOVE");
    } catch (OutOfStockException | InvalidQuantityException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    inventory.displayInventory();

    // PHASE 3: Replenish stock
    System.out.println("\n--- PHASE 3: Replenish Stock ---");
    try {
      inventory.updateStock("P005", 200, "ADD");
    } catch (OutOfStockException | InvalidQuantityException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    inventory.displayInventory();

    // PHASE 4: Multiple stock operations
    System.out.println("--- PHASE 4: Multiple Stock Operations ---");
    try {
      inventory.updateStock("P002", 150, "REMOVE");
      inventory.updateStock("P003", 60, "ADD");
      inventory.updateStock("P004", 30, "ADD");
    } catch (OutOfStockException | InvalidQuantityException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    inventory.displayInventory();

    // PHASE 5: Error handling - insufficient stock
    System.out.println("--- PHASE 5: Error Handling - Out of Stock ---");
    try {
      inventory.updateStock("P001", 100, "REMOVE");
    } catch (OutOfStockException | InvalidQuantityException e) {
      System.out.println("✗ Error caught: " + e.getMessage());
    }

    // PHASE 6: Product details with history
    System.out.println("--- PHASE 6: Product Details with Stock History ---");
    inventory.displayProductDetails("P005");

    // PHASE 7: Inventory statistics
    System.out.println("--- PHASE 7: Inventory Statistics ---");
    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                  INVENTORY STATISTICS                             ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");
    System.out.println("Total Items in Stock: " + inventory.getTotalItems());
    System.out.println("Total Inventory Value: $" + String.format("%.2f", inventory.getInventoryValue()));
    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    SIMULATION COMPLETED                           ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝");
  }
}
