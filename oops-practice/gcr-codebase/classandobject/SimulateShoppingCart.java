class CartItem {
    String itemName;
    double price;
    int quantity;

    // Method to add item to cart
    void addItem(String name, double itemPrice, int qty) {
        itemName = name;
        price = itemPrice;
        quantity += qty;   // increase quantity if item already exists
        System.out.println(name + " added to cart");
    }

    // Method to remove item from cart
    void removeItem(int qty) {
        if (quantity >= qty) {
            quantity -= qty;
            System.out.println(qty + " item(s) removed from cart");
        } else {
            System.out.println("Not enough items to remove");
        }
    }

    // Method to display total cost
    void displayTotalCost() {
        double totalCost = price * quantity;
        System.out.println("Item: " + itemName);
        System.out.println("Quantity: " + quantity);
        System.out.println("Total Cost: " + totalCost);
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
      
        CartItem cart = new CartItem();


        // Add items
        cart.addItem("Laptop", 50000.0, 1);
        cart.displayTotalCost();

        cart.addItem("Laptop", 50000.0, 1); // adding same item again
        cart.displayTotalCost();

        // Remove item
        cart.removeItem(1);
        cart.displayTotalCost();
    }
}
