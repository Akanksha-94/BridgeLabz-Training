/**
 * StringBuffer Problem 1: Concatenate Strings Efficiently Using StringBuffer
 * 
 * Problem:
 * You are given an array of strings. Write a program that uses StringBuffer
 * to concatenate all the strings in the array efficiently.
 * 
 * Approach:
 * 1. Create a new StringBuffer object.
 * 2. Iterate through each string in the array and append it to the
 * StringBuffer.
 * 3. Return the concatenated string after the loop finishes.
 * Using StringBuffer ensures efficient string concatenation due to its mutable
 * nature.
 */

public class ConcatenateStringsUsingStringBuffer {

  /**
   * Concatenates an array of strings using StringBuffer
   * 
   * @param strings array of strings to concatenate
   * @return concatenated string
   */
  public static String concatenateStrings(String[] strings) {
    if (strings == null || strings.length == 0) {
      return "";
    }

    StringBuffer sb = new StringBuffer();

    for (String str : strings) {
      if (str != null) {
        sb.append(str);
      }
    }

    return sb.toString();
  }

  /**
   * Concatenates an array of strings with a separator using StringBuffer
   * 
   * @param strings   array of strings to concatenate
   * @param separator the separator to use between strings
   * @return concatenated string with separator
   */
  public static String concatenateStringsWithSeparator(String[] strings, String separator) {
    if (strings == null || strings.length == 0) {
      return "";
    }

    StringBuffer sb = new StringBuffer();

    for (int i = 0; i < strings.length; i++) {
      if (strings[i] != null) {
        sb.append(strings[i]);
        if (i < strings.length - 1) {
          sb.append(separator);
        }
      }
    }

    return sb.toString();
  }

  /**
   * Main method to test the string concatenation functionality
   */
  public static void main(String[] args) {
    // Test case 1: Simple string array
    String[] arr1 = { "Hello", " ", "World", "!" };
    System.out.println("Input: [\"Hello\", \" \", \"World\", \"!\"]");
    System.out.println("Output: " + concatenateStrings(arr1));
    System.out.println();

    // Test case 2: String array with empty strings
    String[] arr2 = { "Java", "", "is", "", "great" };
    System.out.println("Input: [\"Java\", \"\", \"is\", \"\", \"great\"]");
    System.out.println("Output: " + concatenateStrings(arr2));
    System.out.println();

    // Test case 3: Single string
    String[] arr3 = { "OnlyOne" };
    System.out.println("Input: [\"OnlyOne\"]");
    System.out.println("Output: " + concatenateStrings(arr3));
    System.out.println();

    // Test case 4: Concatenation with separator (comma)
    String[] arr4 = { "Apple", "Banana", "Cherry", "Date" };
    System.out.println("Input: [\"Apple\", \"Banana\", \"Cherry\", \"Date\"]");
    System.out.println("Output with comma separator: " + concatenateStringsWithSeparator(arr4, ", "));
    System.out.println();

    // Test case 5: Concatenation with separator (hyphen)
    String[] arr5 = { "2024", "01", "17" };
    System.out.println("Input: [\"2024\", \"01\", \"17\"]");
    System.out.println("Output with hyphen separator: " + concatenateStringsWithSeparator(arr5, "-"));
    System.out.println();

    // Test case 6: Empty array
    String[] arr6 = {};
    System.out.println("Input: []");
    System.out.println("Output: \"" + concatenateStrings(arr6) + "\"");
  }
}
