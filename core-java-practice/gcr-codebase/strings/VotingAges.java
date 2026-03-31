import java.util.Random;
import java.util.Scanner;

public class VotingAges {
  static int[] randomAges(int n) {
    Random r = new Random();
    int[] ages = new int[n];
    for (int i = 0; i < n; i++)
      ages[i] = 10 + r.nextInt(90); // 10..99
    return ages;
  }

  static String[][] canVoteTable(int[] ages) {
    String[][] out = new String[ages.length][2];
    for (int i = 0; i < ages.length; i++) {
      int a = ages[i];
      out[i][0] = String.valueOf(a);
      if (a < 0)
        out[i][1] = "false";
      else
        out[i][1] = (a >= 18) ? "true" : "false";
    }
    return out;
  }

  static void printTable(String[][] table) {
    System.out.println("Age\tCanVote");
    for (String[] row : table)
      System.out.println(row[0] + "\t" + row[1]);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number of students:");
    int n = sc.nextInt();
    int[] ages = randomAges(n);
    String[][] table = canVoteTable(ages);
    printTable(table);

  }
}
