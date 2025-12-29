import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
  private static int targetNumber;
  private static int guessCount = 0;

  // Generate random number between 1 and 100
  static int generateRandomNumber() {
    Random random = new Random();
    return random.nextInt(100) + 1;
  }

  // Get user feedback on the guess
  static String getUserFeedback(int guess, int target) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("\nComputer guessed: " + guess);
    System.out.println("Is this (H)igh, (L)ow, or (C)orrect? Enter H/L/C: ");
    String feedback = scanner.nextLine().toUpperCase();
    return feedback;
  }

  // Determine next guess based on feedback
  static int determineNextGuess(int low, int high, String feedback, int currentGuess) {
    if (feedback.equals("H")) {
      high = currentGuess - 1;
    } else if (feedback.equals("L")) {
      low = currentGuess + 1;
    }
    return (low + high) / 2;
  }

  // Main game loop
  static void playGame() {
    Scanner scanner = new Scanner(System.in);
    targetNumber = generateRandomNumber();

    int low = 1, high = 100;
    int guess = 50;
    boolean found = false;

    System.out.println("=== Number Guessing Game ===");
    System.out.println("Think of a number between 1 and 100.");
    System.out.println("I will try to guess it!\n");

    while (!found) {
      guessCount++;
      String feedback = getUserFeedback(guess, targetNumber);

      if (feedback.equals("C")) {
        System.out.println("\nYay! I guessed your number in " + guessCount + " attempts!");
        found = true;
      } else if (feedback.equals("H")) {
        high = guess - 1;
        guess = (low + high) / 2;
      } else if (feedback.equals("L")) {
        low = guess + 1;
        guess = (low + high) / 2;
      } else {
        System.out.println("Invalid input! Please enter H, L, or C.");
        guessCount--;
      }
    }
  }

  public static void main(String[] args) {
    playGame();
  }
}
