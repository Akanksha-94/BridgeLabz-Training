import java.util.Scanner;

public class NumberSignCheck {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int[] nums = new int[5];
    for (int i = 0; i < nums.length; i++) {
      System.out.print("Enter number " + (i + 1) + ": ");
      nums[i] = sc.nextInt();
    }

    for (int n : nums) {
      if (n > 0) {
        System.out.print(n + " is positive and ");
        if (n % 2 == 0)
          System.out.println("even.");
        else
          System.out.println("odd.");
      } else if (n < 0)
        System.out.println(n + " is negative.");
      else
        System.out.println(n + " is zero.");
    }

    int first = nums[0];
    int last = nums[nums.length - 1];
    if (first == last)
      System.out.println("First and last elements are equal.");
    else if (first > last)
      System.out.println("First element is greater than last element.");
    else
      System.out.println("First element is less than last element.");
   
  }
}
