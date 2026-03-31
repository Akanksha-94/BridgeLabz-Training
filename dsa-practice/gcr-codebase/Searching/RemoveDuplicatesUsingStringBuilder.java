
/**
 * StringBuilder Problem 2: Remove Duplicates from a String Using StringBuilder
 * 
 * Problem:
 * Write a program that uses StringBuilder to remove all duplicate characters 
 * from a given string while maintaining the original order.
 * 
 * Approach:
 * 1. Initialize an empty StringBuilder and a HashSet to keep track of characters.
 * 2. Iterate over each character in the string:
 *    - If the character is not in the HashSet, append it to the StringBuilder 
 *      and add it to the HashSet.
 * 3. Return the StringBuilder as a string without duplicates.
 */

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesUsingStringBuilder {

  /**
   * Removes duplicate characters from a string using StringBuilder
   * Maintains the original order of first appearance
   * 
   * @param str the string with possible duplicates
   * @return string without duplicates
   */
  public static String removeDuplicates(String str) {
    if (str == null || str.isEmpty()) {
      return str;
    }

    StringBuilder sb = new StringBuilder();
    Set<Character> seen = new HashSet<>();

    for (char ch : str.toCharArray()) {
      if (!seen.contains(ch)) {
        sb.append(ch);
        seen.add(ch);
      }
    }

    return sb.toString();
  }

  /**
   * Main method to test the remove duplicates functionality
   */
  public static void main(String[] args) {
    // Test case 1: String with repeated characters
    String input1 = "hello";
    System.out.println("Input: " + input1);
    System.out.println("Output: " + removeDuplicates(input1));
    System.out.println();

    // Test case 2: String with all duplicates
    String input2 = "aaaaaa";
    System.out.println("Input: " + input2);
    System.out.println("Output: " + removeDuplicates(input2));
    System.out.println();

    // Test case 3: String with no duplicates
    String input3 = "abcdef";
    System.out.println("Input: " + input3);
    System.out.println("Output: " + removeDuplicates(input3));
    System.out.println();

    // Test case 4: String with mixed case
    String input4 = "HeLLo WoRLd";
    System.out.println("Input: " + input4);
    System.out.println("Output: " + removeDuplicates(input4));
    System.out.println();

    // Test case 5: String with special characters
    String input5 = "aabbcc##@@!!";
    System.out.println("Input: " + input5);
    System.out.println("Output: " + removeDuplicates(input5));
    System.out.println();

    // Test case 6: Empty string
    String input6 = "";
    System.out.println("Input: " + input6);
    System.out.println("Output: " + removeDuplicates(input6));
  }
}
