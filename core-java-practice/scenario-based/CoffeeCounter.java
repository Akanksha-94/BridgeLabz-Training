import java.util.Scanner;

public class CoffeeCounter {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    final double GST_RATE = 0.18; // 18% GST

    System.out.println("Welcome to Ravi's Café - Coffee Counter");
    while (true) {
      System.out.print("Enter coffee type (Americano/Latte/Espresso/Cappuccino/Mocha) or 'exit' to quit: ");
      String type = sc.next();
      if (type.equalsIgnoreCase("exit")) {
        System.out.println("Exiting. Thank you!");
        break;
      }

      int pricePerCup;
      switch (type.toLowerCase()) {
        case "americano":
          pricePerCup = 150;
          break;
        case "latte":
          pricePerCup = 200;
          break;
        case "espresso":
          pricePerCup = 120;
          break;
        case "cappuccino":
          pricePerCup = 180;
          break;
        case "mocha":
          pricePerCup = 220;
          break;
        default:
          System.out.println("Unknown coffee type. Please try again.");
          continue;
      }

      System.out.print("Enter quantity: ");
      int qty = sc.nextInt();
      if (qty <= 0) {
        System.out.println("Quantity must be positive. Try again.");
        continue;
      }

      double bill = pricePerCup * qty; // base price
      double gst = bill * GST_RATE; // GST amount
      double total = bill + gst; // final amount

      System.out.printf("Bill details: %d x %s @ %d = %.2f%n", qty, type, pricePerCup, bill);
      System.out.printf("GST (%.0f%%): %.2f%n", GST_RATE * 100, gst);
      System.out.printf("Total payable: %.2f%n", total);
      System.out.println("--- Next customer ---");
    }
  }
}
