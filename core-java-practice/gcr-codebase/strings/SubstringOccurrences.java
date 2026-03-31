import java.util.Scanner;

public class SubstringOccurrences {

  public static int countOccurrences(String str, String substring) {
    if (substring.length() == 0)
      return 0;

    int count = 0;
    int index = 0;

    while ((index = str.indexOf(substring, index)) != -1) {
      count++;
      index += substring.length();
    }

    return count;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String str = sc.nextLine();
    System.out.print("Enter substring to find: ");
    String substring = sc.nextLine();

    int count = countOccurrences(str, substring);
    System.out.println("\"" + substring + "\" occurs " + count + " time(s) in \"" + str + "\"");
  }
}
