class Item {
    String itemCode;
    String itemName;
    double price;

    // Method to display item details
    void displayItem() {
        System.out.println("itemCode : " + itemCode);
        System.out.println("itemPrice : " + price);
        System.out.println("itemName : " + itemName);
        System.out.println("----------------------------");
    }


    // Method to calculate total cost
    double calculateTotalCost(int quantity) {
        return price * quantity;
    }

    public static void main(String[] args) {
      
        // First item
        Item item1 = new Item();
        item1.itemCode = "01AA";
        item1.itemName = "Water bottle";
        item1.price = 500.0;

        // Second item
        Item item2 = new Item();
        item2.itemCode = "01BB";
        item2.itemName = "Rice";
        item2.price = 700.0;

        // Third item
        Item item3 = new Item();
        item3.itemCode = "02AA";
        item3.itemName = "blackboard";
        item3.price = 400.0;

        // Display item details
        item1.displayItem();
        item2.displayItem();
        item3.displayItem();

        // Example: calculate total cost for a quantity
        int quantity = 3;
        System.out.println("Total cost of " + quantity + " Water bottles: "
                + item1.calculateTotalCost(quantity));
    }
}
