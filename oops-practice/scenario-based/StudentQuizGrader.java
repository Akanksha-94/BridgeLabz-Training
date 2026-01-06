public class StudentQuizGrader {
    //Method to calculate score
    public static int calculateScore(String[] correctAnswers, String[] studentAnswers) {
        int score = 0;
        for (int i = 0; i < correctAnswers.length; i++) {
            if (studentAnswers[i].equalsIgnoreCase(correctAnswers[i])) {
                score++;
            }
        }
        return score;
    }
    public static void main(String[] args) {
        String[] correctAnswers = {
                "A","B","C","D","A","B","C","D","A","B"
        };
        String[] studentAnswers = {
                "a","B","D","D","A","c","C","D","b","B"
        };
        int score = calculateScore(correctAnswers, studentAnswers);
        System.out.println("QuizFeedback:");
        for (int i = 0; i < correctAnswers.length; i++) {
            if (studentAnswers[i].equalsIgnoreCase(correctAnswers[i])) {
                System.out.println("Question" + (i + 1) + ":Correct");
            } else {
                System.out.println("Question" + (i + 1) + ":Incorrect");
            }
        }
        double percentage = (score * 100.0) / correctAnswers.length;
        System.out.println("\nTotalScore:" + score + "/" + correctAnswers.length);
        System.out.println("Percentage:" + percentage + "%");
        if (percentage >= 40) {
            System.out.println("Result:Pass");
        } else {
            System.out.println("Result:Fail");
        }
    }
}