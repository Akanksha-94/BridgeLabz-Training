import java.util.Scanner;

public class MovieBookingApp {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);

        System.out.print("Enter number of customers: ");

        int customers = sc.nextInt();

        for (int i = 1; i <= customers; i++) {

            System.out.println("--- Customer " + i + " ---");

            System.out.print("Enter Movie Type (Action / Comedy / Horror): ");
            String movieType = sc.next();

            System.out.print("Enter Seat Type (Gold / Silver): ");

            String seat = sc.next();

            System.out.print("Want Snacks ? (yes / no): ");
            String snack = sc.next();

            int price = 0;
            boolean validMovie = true;

            switch (movieType.toLowerCase()) {
                
                case "action":
                    price = 200;
                    break;

                case "comedy":
                    price = 180;
                    break;

                case "horror":
                    price = 220;
                    break;

                default:
                    System.out.println("Invalid Movie Type! Skipping price calculation.");
                    validMovie = false;
            }

            if (validMovie) {
                if (seat.equalsIgnoreCase("Gold")) {
                    price += 100;
                } 
                else if (seat.equalsIgnoreCase("Silver")) {
                    price += 50;
                }

                if (snack.equalsIgnoreCase("yes")) {
                    price += 80;
                }

                System.out.println("Total Ticket Price: ₹" + price);
            }
        }

        
    }
}
