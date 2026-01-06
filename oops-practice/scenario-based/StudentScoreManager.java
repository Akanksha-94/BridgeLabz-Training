import java.util.Scanner;
public class StudentScoreManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("EnterNumberOfStudents:");
        int n = scanner.nextInt();
        int[] scores = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("EnterScoreForStudent" + (i + 1) + ":");
            if (!scanner.hasNextInt()) {
                System.out.println("InvalidInputNonNumeric");
                return;
            }
            int score = scanner.nextInt();
            if (score < 0) {
                System.out.println("InvalidInputNegativeScore");
                return;
            }
            scores[i] = score;
            sum += score;
        }
        double average = (double) sum / n;
        int highest = scores[0];
        int lowest = scores[0];
        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
            if (score < lowest) {
                lowest = score;
            }
        }
        System.out.println("\nAverageScore:" + average);
        System.out.println("HighestScore:" + highest);
        System.out.println("LowestScore:" + lowest);
        System.out.println("ScoresAboveAverage:");
        for (int score : scores) {
            if (score > average) {
                System.out.println(score);
            }
        }
    }
}