/**
 * StringBuilder Problem 1: Reverse a String Using StringBuilder
 * 
 * Problem:
 * Write a program that uses StringBuilder to reverse a given string.
 * For example, if the input is "hello", the output should be "olleh".
 * 
 * Approach:
 * 1. Create a new StringBuilder object.
 * 2. Append the string to the StringBuilder.
 * 3. Use the reverse() method of StringBuilder to reverse the string.
 * 4. Convert the StringBuilder back to a string and return it.
 */

public class ReverseStringUsingStringBuilder {

  /**
   * Reverses a string using StringBuilder
   * 
   * @param str the string to reverse
   * @return the reversed string
   */
  public static String reverseString(String str) {
    if (str == null) {
      return null;
    }

    StringBuilder sb = new StringBuilder(str);
    return sb.reverse().toString();
  }

  /**
   * Main method to test the reverse string functionality
   */
  public static void main(String[] args) {
    // Test case 1: Simple string
    String input1 = "hello";
    System.out.println("Input: " + input1);
    System.out.println("Output: " + reverseString(input1));
    System.out.println();

    // Test case 2: String with spaces
    String input2 = "Hello World";
    System.out.println("Input: " + input2);
    System.out.println("Output: " + reverseString(input2));
    System.out.println();

    // Test case 3: Single character
    String input3 = "A";
    System.out.println("Input: " + input3);
    System.out.println("Output: " + reverseString(input3));
    System.out.println();

    // Test case 4: Empty string
    String input4 = "";
    System.out.println("Input: " + input4);
    System.out.println("Output: " + reverseString(input4));
    System.out.println();

    // Test case 5: String with numbers and special characters
    String input5 = "Test123!@#";
    System.out.println("Input: " + input5);
    System.out.println("Output: " + reverseString(input5));
  }
}
