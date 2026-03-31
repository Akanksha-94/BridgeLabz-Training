import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
  static String computerChoice() {
    int v = (int) (Math.random() * 3);
    return v == 0 ? "rock" : v == 1 ? "paper" : "scissors";
  }

  static String findWinner(String user, String comp) {
    if (user.equals(comp))
      return "Draw";
    if (user.equals("rock") && comp.equals("scissors"))
      return "User";
    if (user.equals("scissors") && comp.equals("paper"))
      return "User";
    if (user.equals("paper") && comp.equals("rock"))
      return "User";
    return "Computer";
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter number of games:");
    int n = sc.nextInt();
    sc.nextLine();
    String[][] rounds = new String[n][4]; // user, comp, winner, round
    int userWins = 0, compWins = 0, draws = 0;
    for (int i = 0; i < n; i++) {
      System.out.println("Enter your choice (rock/paper/scissors) for game " + (i + 1) + ":");
      String user = sc.nextLine().trim().toLowerCase();
      String comp = computerChoice();
      String winner = findWinner(user, comp);
      if (winner.equals("User"))
        userWins++;
      else if (winner.equals("Computer"))
        compWins++;
      else
        draws++;
      rounds[i][0] = String.valueOf(i + 1);
      rounds[i][1] = user;
      rounds[i][2] = comp;
      rounds[i][3] = winner;
    }
    System.out.println("Game\tUser\tComputer\tWinner");
    for (String[] r : rounds)
      System.out.println(r[0] + "\t" + r[1] + "\t" + r[2] + "\t" + r[3]);
    System.out.println();
    System.out.println("User wins: " + userWins);
    System.out.println("Computer wins: " + compWins);
    System.out.println("Draws: " + draws);
    double userPct = n > 0 ? (userWins * 100.0 / n) : 0;
    double compPct = n > 0 ? (compWins * 100.0 / n) : 0;
    System.out.printf("User win %%: %.2f%%\n", userPct);
    System.out.printf("Computer win %%: %.2f%%\n", compPct);

  }
}
