import java.util.Arrays;
import java.util.Scanner;

public class NumberCheckerDemo {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number to analyze: ");
    long n = sc.nextLong();
    int[] digits = NumberChecker.storeDigits(n);
    System.out.println("Digits: " + Arrays.toString(digits));
    System.out.println("Count: " + NumberChecker.countDigits(n));
    System.out.println("Is Duck? " + NumberChecker.isDuck(digits));
    System.out.println("Is Armstrong? " + NumberChecker.isArmstrong(digits));
    System.out.println("Largest & 2nd Largest: " + Arrays.toString(NumberChecker.largestAndSecondLargest(digits)));
    System.out.println("Smallest & 2nd Smallest: " + Arrays.toString(NumberChecker.smallestAndSecondSmallest(digits)));
    System.out.println("Sum digits: " + NumberChecker.sumDigits(digits));
    System.out.println("Sum squares: " + NumberChecker.sumSquares(digits));
    System.out.println("Is Harshad? " + NumberChecker.isHarshad(digits));
    System.out.println("Digit frequency:");
    int[][] freq = NumberChecker.digitFrequency(digits);
    for (int[] row : freq)
      if (row[1] > 0)
        System.out.println(row[0] + " -> " + row[1]);
    System.out.println("Reversed digits: " + Arrays.toString(NumberChecker.reverse(digits)));
    System.out.println("Is Palindrome? " + NumberChecker.isPalindrome(digits));
    System.out.println("Is Prime? " + NumberChecker.isPrime(n));
    System.out.println("Is Neon? " + NumberChecker.isNeon(n));
    System.out.println("Is Spy? " + NumberChecker.isSpy(digits));
    System.out.println("Is Automorphic? " + NumberChecker.isAutomorphic(n));
    System.out.println("Is Buzz? " + NumberChecker.isBuzz(n));
    System.out.println("Is Perfect? " + NumberChecker.isPerfect((int) n));
    System.out.println("Is Abundant? " + NumberChecker.isAbundant((int) n));
    System.out.println("Is Deficient? " + NumberChecker.isDeficient((int) n));
    System.out.println("Is Strong? " + NumberChecker.isStrong(digits));
    
  }
}
