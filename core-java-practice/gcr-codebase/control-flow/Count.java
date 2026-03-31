
import java.util.Scanner;

public class Count {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        int count = 0;
        int num = Math.abs(number);   // handles negative numbers also

        if (num == 0) {
            count = 1;    
        } else {
            while (num != 0) {
                num = num / 10;   //remove last digit
                count++;          //increase count
            }
        }

        System.out.println("Number of digits: " + count);

        
    }
}
