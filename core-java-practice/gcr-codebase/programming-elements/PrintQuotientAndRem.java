import java.util.Scanner;
public class PrintQuotientAndRem {
  public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number1 = sc.nextInt();
        int number2 = sc.nextInt();
        int quo = number1 / number2;
        int rem = number1 % number2;
        System.out.println("The Quotient is " + quo +
                           " and Remainder is " + rem +
                           " of two numbers " + number1 + " and " + number2);
}
}
