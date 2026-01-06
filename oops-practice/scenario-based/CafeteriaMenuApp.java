import java.util.Scanner;
public class CafeteriaMenuApp {
    // Store menu items
    static String[] menuItems = {
            "Idli",
            "Dosa",
            "Poha",
            "Upma",
            "Sandwich",
            "Burger",
            "Pasta",
            "Pizza",
            "Tea",
            "Coffee"
    };
    // Method to display menu
    public static void displayMenu() {
        System.out.println("===== Cafeteria Menu =====");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println(i + " : " + menuItems[i]);
        }
    }
    // Method to get item by index
    public static String getItemByIndex(int index) {
        if (index >= 0 && index < menuItems.length) {
            return menuItems[index];
        } else {
            return "Invalid selection!";
        }
    }
    // Main method
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            displayMenu();
            System.out.print("\nSelect item index: ");
            int choice = scanner.nextInt();
            String selectedItem = getItemByIndex(choice);
            System.out.println("You selected: " + selectedItem);
        }
    }
}