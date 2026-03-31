import java.util.Scanner;

public class DigitFrequencyProgram {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        long number = sc.nextLong();

        // Handle negative numbers
        number = Math.abs(number);

        // Special case if number is 0
        if (number == 0) {
            System.out.println("Digit 0 appears 1 time");
            return;
        }

        // Step 1: Count digits
        long temp = number;
        int count = 0;

        while (temp > 0) {
            temp /= 10;
            count++;
        }

        // Step 2: Store digits in an array
        int[] digits = new int[count];
        temp = number;

        for (int i = count - 1; i >= 0; i--) {
            digits[i] = (int)(temp % 10);
            temp /= 10;
        }

        // Step 3: Create frequency array of size 10
        int[] freq = new int[10];

        for (int digit : digits) {
            freq[digit]++;
        }

        // Step 4: Display frequency
        System.out.println("\nDigit Frequency:");
        for (int i = 0; i < 10; i++) {
            if (freq[i] > 0) {
                System.out.println("Digit " + i + " appears " + freq[i] + " times");
            }
        }

    }
}
