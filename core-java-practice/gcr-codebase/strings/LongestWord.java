import java.util.Scanner;

public class LongestWord {

  public static String findLongestWord(String sentence) {
    String[] words = sentence.split(" ");
    String longest = "";

    for (int i = 0; i < words.length; i++) {
      if (words[i].length() > longest.length()) {
        longest = words[i];
      }
    }

    return longest;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a sentence: ");
    String sentence = sc.nextLine();

    String longestWord = findLongestWord(sentence);
    System.out.println("Longest word: \"" + longestWord + "\" (Length: " + longestWord.length() + ")");
  }
}
