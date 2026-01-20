import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ExamProctor {
    private Stack<Integer> navigationStack = new Stack<>();
    private Map<Integer, String> answers = new HashMap<>();
    private Map<Integer, String> correctAnswers = new HashMap<>();

    public ExamProctor() {
        correctAnswers.put(1, "A");
        correctAnswers.put(2, "B");
        correctAnswers.put(3, "C");
        correctAnswers.put(4, "D");
    }

    public void visitQuestion(int questionId) {
        navigationStack.push(questionId);
    }

    public void submitAnswer(int questionId, String answer) {
        answers.put(questionId, answer);
    }

    public int calculateScore() {
        int score = 0;
        for (Map.Entry<Integer, String> entry : correctAnswers.entrySet()) {
            String given = answers.get(entry.getKey());
            if (entry.getValue().equals(given)) {
                score++;
            }
        }
        return score;
    }

    public void reviewNavigation() {
        while (!navigationStack.isEmpty()) {
            System.out.println("Visited Question: " + navigationStack.pop());
        }
    }

    public static void main(String[] args) {
        ExamProctor exam = new ExamProctor();

        exam.visitQuestion(1);
        exam.submitAnswer(1, "A");

        exam.visitQuestion(2);
        exam.submitAnswer(2, "C");

        exam.visitQuestion(3);
        exam.submitAnswer(3, "C");

        exam.visitQuestion(4);
        exam.submitAnswer(4, "D");

        int score = exam.calculateScore();
        System.out.println("Final Score: " + score);

        exam.reviewNavigation();
    }
}