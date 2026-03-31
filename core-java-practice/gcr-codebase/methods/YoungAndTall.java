import java.util.Scanner;

public class YoungAndTall {

  /**
   * Method to find the youngest of the 3 friends
   * 
   * @param ages array containing ages of 3 friends
   * @return the minimum age (youngest)
   */
  public static int findYoungest(int[] ages) {
    return Math.min(Math.min(ages[0], ages[1]), ages[2]);
  }

  /**
   * Method to find the tallest of the 3 friends
   * 
   * @param heights array containing heights of 3 friends
   * @return the maximum height (tallest)
   */
  public static double findTallest(double[] heights) {
    return Math.max(Math.max(heights[0], heights[1]), heights[2]);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    String[] friendNames = { "Amar", "Akbar", "Anthony" };
    int[] ages = new int[3];
    double[] heights = new double[3];

    // Input ages and heights for 3 friends
    System.out.println("Enter information for 3 friends:");

    for (int i = 0; i < 3; i++) {
      System.out.print("\nEnter age of " + friendNames[i] + ": ");
      ages[i] = scanner.nextInt();

      System.out.print("Enter height of " + friendNames[i] + " (in cm): ");
      heights[i] = scanner.nextDouble();
    }

    // Find youngest and tallest
    int youngestAge = findYoungest(ages);
    double tallestHeight = findTallest(heights);

    // Find names of youngest and tallest
    String youngestName = "";
    String tallestName = "";

    for (int i = 0; i < 3; i++) {
      if (ages[i] == youngestAge) {
        youngestName = friendNames[i];
      }
      if (heights[i] == tallestHeight) {
        tallestName = friendNames[i];
      }
    }

    // Display results
    System.out.println("\n===== Results =====");
    System.out.println("Youngest Friend: " + youngestName + " (Age: " + youngestAge + " years)");
    System.out.println("Tallest Friend: " + tallestName + " (Height: " + tallestHeight + " cm)");

    scanner.close();
  }
}
