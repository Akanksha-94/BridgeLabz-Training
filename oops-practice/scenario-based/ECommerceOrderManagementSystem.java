import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * E-Commerce Order Management System
 * Real Scenario: Users browse products, place orders, cancel them, and track
 * delivery.
 * 
 * Concepts Used:
 * - OOP: Product, Order, Customer
 * - Interface: Payment (Card, UPI, Wallet)
 * - Polymorphism: Different payment behaviors
 * - Exception Handling: PaymentFailedException, OrderCancellationException
 */

// Custom Exceptions
class PaymentFailedException extends Exception {
  public PaymentFailedException(String message) {
    super(message);
  }
}

class OrderCancellationException extends Exception {
  public OrderCancellationException(String message) {
    super(message);
  }
}

// Product class
class Product {
  private String productId;
  private String name;
  private double price;
  private int stock;
  private String category;

  public Product(String productId, String name, double price, int stock, String category) {
    this.productId = productId;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.category = category;
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

  public int getStock() {
    return stock;
  }

  public String getCategory() {
    return category;
  }

  public void decreaseStock(int quantity) throws Exception {
    if (quantity > stock) {
      throw new Exception("Insufficient stock for " + name);
    }
    stock -= quantity;
  }

  public void increaseStock(int quantity) {
    stock += quantity;
  }

  @Override
  public String toString() {
    return String.format("%-20s | %-30s | $%-8.2f | Stock: %-5d | %s",
        productId, name, price, stock, category);
  }
}

// Payment Interface
interface Payment {
  boolean processPayment(double amount) throws PaymentFailedException;

  String getPaymentMethod();
}

// Credit Card Payment
class CardPayment implements Payment {
  private String cardNumber;
  private double balance;

  public CardPayment(String cardNumber, double balance) {
    this.cardNumber = cardNumber;
    this.balance = balance;
  }

  @Override
  public boolean processPayment(double amount) throws PaymentFailedException {
    if (amount > balance) {
      throw new PaymentFailedException("Insufficient card balance!");
    }
    balance -= amount;
    return true;
  }

  @Override
  public String getPaymentMethod() {
    return "Credit Card (" + cardNumber.substring(cardNumber.length() - 4) + ")";
  }
}

// UPI Payment
class UPIPayment implements Payment {
  private String upiId;
  private double balance;

  public UPIPayment(String upiId, double balance) {
    this.upiId = upiId;
    this.balance = balance;
  }

  @Override
  public boolean processPayment(double amount) throws PaymentFailedException {
    if (amount > balance) {
      throw new PaymentFailedException("Insufficient UPI balance!");
    }
    balance -= amount;
    return true;
  }

  @Override
  public String getPaymentMethod() {
    return "UPI (" + upiId + ")";
  }
}

// Wallet Payment
class WalletPayment implements Payment {
  private double walletBalance;

  public WalletPayment(double initialBalance) {
    this.walletBalance = initialBalance;
  }

  @Override
  public boolean processPayment(double amount) throws PaymentFailedException {
    if (amount > walletBalance) {
      throw new PaymentFailedException("Insufficient wallet balance!");
    }
    walletBalance -= amount;
    return true;
  }

  @Override
  public String getPaymentMethod() {
    return "Digital Wallet";
  }
}

// Order Item
class OrderItem {
  private Product product;
  private int quantity;

  public OrderItem(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getSubtotal() {
    return product.getPrice() * quantity;
  }

  @Override
  public String toString() {
    return String.format("%-20s | Qty: %-5d | Unit: $%-8.2f | Subtotal: $%-10.2f",
        product.getName(), quantity, product.getPrice(), getSubtotal());
  }
}

// Order class
class Order {
  private String orderId;
  private String customerId;
  private List<OrderItem> items;
  private String status; // PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
  private Payment paymentMethod;
  private LocalDateTime orderDate;
  private LocalDateTime deliveryDate;

  public Order(String orderId, String customerId) {
    this.orderId = orderId;
    this.customerId = customerId;
    this.items = new ArrayList<>();
    this.status = "PENDING";
    this.orderDate = LocalDateTime.now();
  }

  public void addItem(OrderItem item) {
    items.add(item);
    System.out.println("✓ Added " + item.getQuantity() + "x " +
        item.getProduct().getName() + " to order");
  }

  public void setPaymentMethod(Payment payment) {
    this.paymentMethod = payment;
  }

  public double getTotalAmount() {
    return items.stream().mapToDouble(OrderItem::getSubtotal).sum();
  }

  public boolean processPayment() throws PaymentFailedException {
    if (paymentMethod == null) {
      throw new PaymentFailedException("Payment method not set");
    }
    return paymentMethod.processPayment(getTotalAmount());
  }

  public void confirmOrder() {
    if ("PENDING".equals(status)) {
      status = "CONFIRMED";
      System.out.println("✓ Order " + orderId + " confirmed");
    }
  }

  public void shipOrder() {
    if ("CONFIRMED".equals(status)) {
      status = "SHIPPED";
      System.out.println("✓ Order " + orderId + " shipped");
    }
  }

  public void deliverOrder() {
    if ("SHIPPED".equals(status)) {
      status = "DELIVERED";
      deliveryDate = LocalDateTime.now();
      System.out.println("✓ Order " + orderId + " delivered");
    }
  }

  public void cancelOrder() throws OrderCancellationException {
    if ("DELIVERED".equals(status) || "SHIPPED".equals(status)) {
      throw new OrderCancellationException("Cannot cancel " + status + " order");
    }
    status = "CANCELLED";
    System.out.println("✓ Order " + orderId + " cancelled");
  }

  public void displayOrderDetails() {
    System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║                         ORDER DETAILS                              ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
    System.out.println("Order ID: " + orderId + " | Customer: " + customerId +
        " | Status: " + status);
    System.out.println("Order Date: " + orderDate);
    if (deliveryDate != null) {
      System.out.println("Delivery Date: " + deliveryDate);
    }
    System.out.println("╟───────────────────────────────────────────────────────────────────╢");

    for (OrderItem item : items) {
      System.out.println(item);
    }

    System.out.println("╟───────────────────────────────────────────────────────────────────╢");
    System.out.println("Total Amount: $" + String.format("%.2f", getTotalAmount()));
    if (paymentMethod != null) {
      System.out.println("Payment Method: " + paymentMethod.getPaymentMethod());
    }
    System.out.println("╚═══════════════════════════════════════════════════════════════════╝\n");
  }

  public String getOrderId() {
    return orderId;
  }

  public String getStatus() {
    return status;
  }
}

public class ECommerceOrderManagementSystem {
  private Map<String, Product> catalog;
  private Map<String, Order> orders;
  private int orderCounter;

  public ECommerceOrderManagementSystem() {
    this.catalog = new HashMap<>();
    this.orders = new HashMap<>();
    this.orderCounter = 0;
  }

  public void addProduct(Product product) {
    catalog.put(product.getProductId(), product);
    System.out.println("✓ Product added: " + product.getName());
  }

  public void displayCatalog() {
    System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║                      PRODUCT CATALOG                              ║");
    System.out.println("╠═══════════════════════════════════════════════════════════════════╣");

    for (Product p : catalog.values()) {
      System.out.println(p);
    }

    System.out.println("╚═══════════════════════════════════════════════════════════════════╝\n");
  }

  public Order createOrder(String customerId) {
    orderCounter++;
    Order order = new Order("ORD" + orderCounter, customerId);
    orders.put(order.getOrderId(), order);
    System.out.println("✓ Order created: " + order.getOrderId());
    return order;
  }

  public static void main(String[] args) {
    ECommerceOrderManagementSystem ecommerce = new ECommerceOrderManagementSystem();

    System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║      E-COMMERCE ORDER MANAGEMENT SYSTEM - DEMONSTRATION          ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════════╝\n");

    // PHASE 1: Add products
    System.out.println("--- PHASE 1: Add Products ---");
    ecommerce.addProduct(new Product("P001", "Laptop", 999.99, 10, "Electronics"));
    ecommerce.addProduct(new Product("P002", "Mouse", 25.99, 50, "Electronics"));
    ecommerce.addProduct(new Product("P003", "Keyboard", 79.99, 30, "Electronics"));
    ecommerce.addProduct(new Product("P004", "Monitor", 299.99, 15, "Electronics"));

    ecommerce.displayCatalog();

    // PHASE 2: Create order and add items
    System.out.println("--- PHASE 2: Create Order ---");
    Order order1 = ecommerce.createOrder("CUST001");
    order1.addItem(new OrderItem(ecommerce.catalog.get("P001"), 1));
    order1.addItem(new OrderItem(ecommerce.catalog.get("P002"), 2));
    order1.addItem(new OrderItem(ecommerce.catalog.get("P003"), 1));

    // PHASE 3: Payment processing
    System.out.println("\n--- PHASE 3: Payment Processing ---");
    order1.setPaymentMethod(new CardPayment("1234567890123456", 2000));
    try {
      if (order1.processPayment()) {
        System.out.println("✓ Payment successful");
        order1.confirmOrder();
      }
    } catch (PaymentFailedException e) {
      System.out.println("✗ Payment failed: " + e.getMessage());
    }

    order1.displayOrderDetails();

    // PHASE 4: Order status tracking
    System.out.println("--- PHASE 4: Order Status Tracking ---");
    order1.shipOrder();
    order1.deliverOrder();
    order1.displayOrderDetails();

    // PHASE 5: Another order with UPI payment
    System.out.println("--- PHASE 5: Another Order with UPI ---");
    Order order2 = ecommerce.createOrder("CUST002");
    order2.addItem(new OrderItem(ecommerce.catalog.get("P004"), 1));
    order2.setPaymentMethod(new UPIPayment("customer@upi", 500));

    try {
      if (order2.processPayment()) {
        System.out.println("✓ Payment successful");
        order2.confirmOrder();
      }
    } catch (PaymentFailedException e) {
      System.out.println("✗ Payment failed: " + e.getMessage());
    }

    order2.displayOrderDetails();

    // PHASE 6: Payment failure scenario
    System.out.println("--- PHASE 6: Payment Failure Scenario ---");
    Order order3 = ecommerce.createOrder("CUST003");
    order3.addItem(new OrderItem(ecommerce.catalog.get("P001"), 1));
    order3.setPaymentMethod(new WalletPayment(100)); // Insufficient balance

    try {
      order3.processPayment();
    } catch (PaymentFailedException e) {
      System.out.println("✗ Payment failed: " + e.getMessage());
    }

    // PHASE 7: Order cancellation
    System.out.println("\n--- PHASE 7: Order Cancellation ---");
    try {
      order3.cancelOrder();
      System.out.println("✓ Order cancelled successfully");
    } catch (OrderCancellationException e) {
      System.out.println("✗ Cancellation failed: " + e.getMessage());
    }

    System.out.println("\n╔═══════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    SIMULATION COMPLETED                           ║");
    System.out.println("╚═══════════════════════════════════════════════════════════════════╝");
  }
}
