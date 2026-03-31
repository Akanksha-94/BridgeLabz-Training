import java.util.Scanner;

public class BusRouteTracker {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);

        int stopNum = 1;
        double totalDistance = 0;
        String choice;

        while (true) {

            System.out.print("Enter distance from previous stop to  Stop " + stopNum + " (in  km): ");

            double distance = sc.nextDouble();

            totalDistance += distance;

            System.out.println("Total Distance Covered: " + totalDistance + " km");

            System.out.print("Do you want to continue to next stop? (yes /no): ");
            choice =  sc.next();

            if (choice.equalsIgnoreCase(" no")) {
                System.out.println("Passenger got off  the bus.");
                
                break;
            }

            stopNum++;
        }

        System.out.println("Final Distance Travelled: " + totalDistance + " km");
    
    }
}
