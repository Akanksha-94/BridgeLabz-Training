import java.util.ArrayList;
import java.util.List;

// Interface
interface Discountable {
    double applyDiscount();
    String getDiscountDetails();
}

// Abstract class
abstract class FoodItem {

    // Encapsulated fields
    private String itemName;
    protected double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    // Abstract method
    public abstract double calculateTotalPrice();

    // Concrete method
    public void getItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Price per unit: " + price);
        System.out.println("Quantity: " + quantity);
    }

    // Controlled access (Encapsulation)
    protected int getQuantity() {
        return quantity;
    }

    protected String getItemName() {
        return itemName;
    }
}

// Veg item
class VegItem extends FoodItem implements Discountable {

    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return price * getQuantity();
    }

    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * 0.10; // 10% discount
    }

    @Override
    public String getDiscountDetails() {
        return "10% discount on Veg Items";
    }
}

// Non-veg item
class NonVegItem extends FoodItem implements Discountable {

    private double nonVegCharge = 50;

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return (price * getQuantity()) + nonVegCharge;
    }

    @Override
    public double applyDiscount() {
        return calculateTotalPrice() * 0.05; // 5% discount
    }

    @Override
    public String getDiscountDetails() {
        return "5% discount on Non-Veg Items";
    }
}

// Main class
public class OnlineFoodDeliverySystem {

    // Polymorphic order processing method
    public static void processOrder(List<FoodItem> items) {

        for (FoodItem item : items) {

            double discount = 0;

            item.getItemDetails();
            double totalPrice = item.calculateTotalPrice();

            if (item instanceof Discountable) {
                Discountable discountableItem = (Discountable) item;
                discount = discountableItem.applyDiscount();
                System.out.println(discountableItem.getDiscountDetails());
            }

            double finalPrice = totalPrice - discount;

            System.out.println("Total Price: " + totalPrice);
            System.out.println("Discount: " + discount);
            System.out.println("Final Payable Amount: " + finalPrice);
            System.out.println("------------------------------");
        }
    }

    public static void main(String[] args) {

        List<FoodItem> order = new ArrayList<>();

        order.add(new VegItem("Paneer Butter Masala", 250, 2));
        order.add(new NonVegItem("Chicken Biryani", 300, 1));

        processOrder(order);
    }
}
