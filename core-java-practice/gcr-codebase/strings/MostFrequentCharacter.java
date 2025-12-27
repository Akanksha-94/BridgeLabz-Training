import java.util.Scanner;

public class MostFrequentCharacter {

  public static char findMostFrequent(String str) {
    str = str.toLowerCase().replaceAll("[^a-z0-9]", "");

    char mostFrequent = ' ';
    int maxCount = 0;

    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      int count = 0;

      for (int j = 0; j < str.length(); j++) {
        if (str.charAt(j) == ch) {
          count++;
        }
      }

      if (count > maxCount) {
        maxCount = count;
        mostFrequent = ch;
      }
    }

    return mostFrequent;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String str = sc.nextLine();

    char mostFrequent = findMostFrequent(str);
    System.out.println("Most Frequent Character: '" + mostFrequent + "'");
  }
}
