import java.util.Scanner;

public class YoungestTallest {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] names = { "Amar", "Akbar", "Anthony" };
    int[] ages = new int[3];
    double[] heights = new double[3];

    for (int i = 0; i < 3; i++) {
      System.out.print("Enter age of " + names[i] + ": ");
      if (!sc.hasNextInt())
        return;
      ages[i] = sc.nextInt();
      System.out.print("Enter height (in cm) of " + names[i] + ": ");
      if (!sc.hasNextDouble())
        return;
      heights[i] = sc.nextDouble();
    }

    int minIdx = 0;
    for (int i = 1; i < 3; i++)
      if (ages[i] < ages[minIdx])
        minIdx = i;
    int maxHtIdx = 0;
    for (int i = 1; i < 3; i++)
      if (heights[i] > heights[maxHtIdx])
        maxHtIdx = i;

    System.out.println("Youngest: " + names[minIdx] + " (age " + ages[minIdx] + ")");
    System.out.println("Tallest: " + names[maxHtIdx] + " (height " + heights[maxHtIdx] + ")");
  }
}
