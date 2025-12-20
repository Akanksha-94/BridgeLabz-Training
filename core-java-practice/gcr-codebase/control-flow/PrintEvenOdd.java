import java.util.Scanner;

public class PrintEvenOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();

        if (num <= 0) {
            System.out.println("Please enter a positive integer.");
        } else {
            for (int i = 1; i <= num; i++) {
                if (i % 2 == 0) {
                    System.out.println(i + " is an Even number.");
                } else {
                    System.out.println(i + " is an Odd number.");
                }
            }

        }
    }

}
