import java.util.Scanner;

public class LibraryReminder {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);

        for (int i = 1; i <= 5; i++) {

            System.out.println("Book " + i);

            System.out.print("Enter Due Day: ");

            int dueDate = sc.nextInt();

            System.out.print("Enter Return Day: ");

            int returnDate = sc.nextInt();

            if (returnDate > dueDate) {

                int lateDays = returnDate - dueDate;
                int fine = lateDays * 5;
                System.out.println("Late by " + lateDays + " days. Fine = ₹" + fine);
            } 
            else {
                System.out.println("No Fine. Returned On Time!");
            }
        }

    
    }
}
