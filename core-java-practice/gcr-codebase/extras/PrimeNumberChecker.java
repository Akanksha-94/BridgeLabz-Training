import java.util.Scanner;

public class PrimeNumberChecker {

  // Take input from user
  static int takeInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("=== Prime Number Checker ===");
    System.out.print("Enter a number to check if it's prime: ");
    return scanner.nextInt();
  }

  // Check if number is prime
  static boolean isPrime(int number) {
    if (number <= 1) {
      return false;
    }

    if (number == 2) {
      return true;
    }

    if (number % 2 == 0) {
      return false;
    }

    // Check odd divisors up to square root
    for (int i = 3; i <= Math.sqrt(number); i += 2) {
      if (number % i == 0) {
        return false;
      }
    }

    return true;
  }

  // Display result
  static void displayResult(int number, boolean prime) {
    System.out.println("\n--- Result ---");
    if (prime) {
      System.out.println(number + " is a PRIME number.");
    } else {
      System.out.println(number + " is NOT a prime number.");
    }
  }

  public static void main(String[] args) {
    int number = takeInput();
    boolean prime = isPrime(number);
    displayResult(number, prime);
  }
}
