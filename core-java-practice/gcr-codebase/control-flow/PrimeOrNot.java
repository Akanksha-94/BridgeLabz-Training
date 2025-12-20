import java.util.Scanner;

public class PrimeOrNot {
  
  public static void main(String[] args) {
    Scanner sc= new Scanner(System.in);
    boolean isPrime = true;

    int number= sc.nextInt();
    if (number<= 1) {
      isPrime = false;
    } else {
      for (int i = 2; i <= Math.sqrt(number); i++) {
        if (number % i == 0) {
          isPrime = false;
          break;
        }
      }
    }

    if (isPrime) {
      System.out.println(num + " is a prime number.");
    } else {
      System.out.println(num + " is not a prime number.");
    }
  }
}
