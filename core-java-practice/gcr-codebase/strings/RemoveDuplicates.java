import java.util.Scanner;

public class RemoveDuplicates {

  public static String removeDuplicates(String str) {
    String result = "";
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (result.indexOf(ch) == -1) {
        result += ch;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a string: ");
    String str = sc.nextLine();
    String modified = removeDuplicates(str);
    System.out.println("Original string: " + str);
    System.out.println("String after removing duplicates: " + modified);
  }
}
