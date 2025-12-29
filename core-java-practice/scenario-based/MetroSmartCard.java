import java.util.Scanner;

public class MetroSmartCard {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);

        System.out.print("Enter initial Smart Card Balance:  ");
        double balance = sc.nextDouble();

        while (balance > 0) {

            System.out.print("Enter Distance (km) or 0 to exit:  ");

            double distance = sc.nextDouble();

            if (distance == 0) {
                System.out.println("Thank  you for using Metro!");

                break;
            }

            if (distance < 0) {

                System.out.println("Distance cannot be negative!");
                
                continue;
            }

            double fare = (distance <= 5) ? 20 :(distance <= 10) ? 30 : 40;

            if (fare > balance) {
                System.out.println("Insufficient Balance! Recharge Needed.");
                break;
            }

            balance -= fare;

            System.out.println("Fare Deducted: ₹ " + fare);

            System.out.println("Remaining Balance: ₹ " + balance);
        }

        System.out.println("\nJourney Ended. Final Balance: ₹ " + balance);
        
    }
}
