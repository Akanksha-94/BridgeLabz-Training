import java.util.Scanner;

public class StudentVoteChecker {

  /**
   * Method to check if a student can vote based on their age
   * 
   * @param age the age of the student
   * @return true if student can vote (age >= 18), false otherwise
   */
  public boolean canStudentVote(int age) {
    // Validate age: negative numbers cannot vote
    if (age < 0) {
      return false;
    }

    // Check if age is 18 or above
    return age >= 18;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StudentVoteChecker checker = new StudentVoteChecker();

    // Array to store ages of 10 students
    int[] studentAges = new int[10];

    System.out.println("Enter ages of 10 students:");

    // Input ages and check voting eligibility
    for (int i = 0; i < 10; i++) {
      System.out.print("Enter age of student " + (i + 1) + ": ");
      studentAges[i] = scanner.nextInt();

      boolean canVote = checker.canStudentVote(studentAges[i]);

      if (studentAges[i] < 0) {
        System.out.println("  Invalid age! (Age cannot be negative)");
      } else if (canVote) {
        System.out.println("  ✓ Student " + (i + 1) + " can vote");
      } else {
        System.out.println("  ✗ Student " + (i + 1) + " cannot vote (age < 18)");
      }
    }

    // Display summary
    System.out.println("\n===== Summary =====");
    int canVoteCount = 0;
    int cannotVoteCount = 0;

    for (int i = 0; i < 10; i++) {
      if (checker.canStudentVote(studentAges[i])) {
        canVoteCount++;
      } else {
        cannotVoteCount++;
      }
    }

    System.out.println("Students who can vote: " + canVoteCount);
    System.out.println("Students who cannot vote: " + cannotVoteCount);

    scanner.close();
  }
}
