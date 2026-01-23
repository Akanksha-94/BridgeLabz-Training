import java.util.*;

public class ShoppingCart {
    
    static class Product {
        String name;
        double price;
        int quantity;

        Product(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public double getTotalPrice() {
            return price * quantity;
        }

        @Override
        public String toString() {
            return name + " - $" + String.format("%.2f", price);
        }
    }

    static class CartManager {
       
        private Map<String, Double> productPrices = new HashMap<>();
        
        private Map<String, Integer> cartItems = new LinkedHashMap<>();
        
        private Map<String, Double> sortedByPrice;

        public void addAvailableProduct(String name, double price) {
            productPrices.put(name, price);
            System.out.println("✓ Product added to inventory: " + name + " - $" + 
                             String.format("%.2f", price));
        }

        public void addToCart(String productName, int quantity) {
            if (!productPrices.containsKey(productName)) {
                System.out.println("✗ Product not found: " + productName);
                return;
            }
            
            if (quantity <= 0) {
                System.out.println("✗ Invalid quantity: " + quantity);
                return;
            }
            
            cartItems.put(productName, 
                         cartItems.getOrDefault(productName, 0) + quantity);
            System.out.println("✓ Added " + quantity + "x " + productName + " to cart");
        }

        public void removeFromCart(String productName) {
            if (cartItems.remove(productName) != null) {
                System.out.println("✓ Removed " + productName + " from cart");
            } else {
                System.out.println("✗ Product not in cart: " + productName);
            }
        }

        public void updateQuantity(String productName, int newQuantity) {
            if (!cartItems.containsKey(productName)) {
                System.out.println("✗ Product not in cart: " + productName);
                return;
            }
            
            if (newQuantity <= 0) {
                removeFromCart(productName);
                return;
            }
            
            cartItems.put(productName, newQuantity);
            System.out.println("✓ Updated quantity of " + productName + " to " + newQuantity);
        }

        public void displayCart() {
            System.out.println("\n=== Shopping Cart (In Order Added) ===");
            if (cartItems.isEmpty()) {
                System.out.println("Cart is empty.");
                return;
            }
            
            for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                String productName = entry.getKey();
                int quantity = entry.getValue();
                double unitPrice = productPrices.get(productName);
                double totalPrice = unitPrice * quantity;
                
                System.out.println(productName + " x" + quantity + 
                                 " @ $" + String.format("%.2f", unitPrice) + 
                                 " = $" + String.format("%.2f", totalPrice));
            }
        }

        public void displayCartSortedByPrice() {
            System.out.println("\n=== Shopping Cart (Sorted by Price) ===");
            if (cartItems.isEmpty()) {
                System.out.println("Cart is empty.");
                return;
            }

            TreeMap<Double, List<String>> sortedByPrice = new TreeMap<>();
            
            for (String productName : cartItems.keySet()) {
                double price = productPrices.get(productName);
                sortedByPrice.computeIfAbsent(price, k -> new ArrayList<>()).add(productName);
            }
            
            sortedByPrice.forEach((price, products) -> {
                for (String productName : products) {
                    int quantity = cartItems.get(productName);
                    double totalPrice = price * quantity;
                    System.out.println(productName + " x" + quantity + 
                                     " @ $" + String.format("%.2f", price) + 
                                     " = $" + String.format("%.2f", totalPrice));
                }
            });
        }

        public double calculateTotal() {
            double total = 0;
            for (Map.Entry<String, Integer> entry : cartItems.entrySet()) {
                double price = productPrices.get(entry.getKey());
                total += price * entry.getValue();
            }
            return total;
        }

        public void displayStatistics() {
            System.out.println("\n=== Cart Statistics ===");
            System.out.println("Items in cart: " + cartItems.size());
            
            int totalQuantity = cartItems.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println("Total quantity: " + totalQuantity);
            
            double total = calculateTotal();
            System.out.println("Total price: $" + String.format("%.2f", total));
            
            double average = cartItems.isEmpty() ? 0 : total / cartItems.size();
            System.out.println("Average per item type: $" + String.format("%.2f", average));
        }

        public void displayAvailableProducts() {
            System.out.println("\n=== Available Products ===");
            productPrices.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEach(entry -> 
                            System.out.println(entry.getKey() + " - $" + 
                                             String.format("%.2f", entry.getValue())));
        }

        public void clearCart() {
            cartItems.clear();
            System.out.println("✓ Cart cleared.");
        }

        public void checkout() {
            double total = calculateTotal();
            if (total == 0) {
                System.out.println("✗ Cart is empty. Nothing to checkout.");
                return;
            }
            
            System.out.println("\n=== Checkout ===");
            displayCart();
            System.out.println("Total Amount: $" + String.format("%.2f", total));
            System.out.println("✓ Payment processed. Thank you for your purchase!");
            clearCart();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Shopping Cart System ===\n");
        
        CartManager cart = new CartManager();

        System.out.println("--- Adding Products to Inventory ---");
        cart.addAvailableProduct("Apple", 1.50);
        cart.addAvailableProduct("Banana", 0.75);
        cart.addAvailableProduct("Orange", 2.00);
        cart.addAvailableProduct("Mango", 3.50);
        cart.addAvailableProduct("Grapes", 4.00);
        cart.addAvailableProduct("Watermelon", 6.00);

        cart.displayAvailableProducts();

        System.out.println("\n--- Adding Items to Cart ---");
        cart.addToCart("Apple", 5);
        cart.addToCart("Banana", 3);
        cart.addToCart("Mango", 2);
        cart.addToCart("Orange", 4);
        cart.addToCart("Watermelon", 1);

        cart.displayCart();
        cart.displayCartSortedByPrice();
        cart.displayStatistics();

        System.out.println("\n--- Updating Cart ---");
        cart.updateQuantity("Apple", 3);
        cart.removeFromCart("Banana");

        cart.displayCart();
        System.out.println("\nTotal: $" + String.format("%.2f", cart.calculateTotal()));

        cart.checkout();

        System.out.println("\n--- New Shopping Session ---");
        cart.addToCart("Grapes", 2);
        cart.addToCart("Orange", 1);
        cart.displayCart();
        cart.displayStatistics();
        cart.checkout();
    }
}
