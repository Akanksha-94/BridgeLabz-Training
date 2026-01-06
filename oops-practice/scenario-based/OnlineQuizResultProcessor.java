import java.util.ArrayList;
// Custom Exception
class InvalidQuizSubmissionException extends Exception {
    public InvalidQuizSubmissionException(String message) {
        super(message);
    }
}
public class OnlineQuizResultProcessor {
    // Compare answers and calculate score
    public static int calculateScore(String[] correct, String[] user)
            throws InvalidQuizSubmissionException {
        if (correct.length != user.length) {
            throw new InvalidQuizSubmissionException("Answer length mismatch.");
        }
        int score = 0;
        for (int i = 0; i < correct.length; i++) {
            if (correct[i].equalsIgnoreCase(user[i])) {
                score++;
            }
        }
        return score;
    }
    public static void main(String[] args) {
        String[] correctAnswers = {"A", "B", "C", "D"};
        String[] userAnswers = {"A", "B", "D", "D"};
        ArrayList<Integer> scores = new ArrayList<>();
        try {
            int score = calculateScore(correctAnswers, userAnswers);
            scores.add(score);
            System.out.println("User Score: " + score);
            System.out.println("All Scores: " + scores);
        } catch (InvalidQuizSubmissionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}