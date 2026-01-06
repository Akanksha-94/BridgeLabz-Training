import java.util.ArrayList;
// Custom Exception
class InvalidMarkException extends Exception {
    public InvalidMarkException(String message) {
        super(message);
    }
}
class Student {
    String name;
    int[] marks;
    Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }
}
public class StudentReportGenerator {
    // Validate marks
    public static void validateMarks(int[] marks) throws InvalidMarkException {
        for (int m : marks) {
            if (m < 0 || m > 100) {
                throw new InvalidMarkException("Marks must be between 0 and 100.");
            }
        }
    }
    // Calculate average
    public static double calculateAverage(int[] marks) {
        int sum = 0;
        for (int m : marks) sum += m;
        return (double) sum / marks.length;
    }
    // Assign grade
    public static char assignGrade(double avg) {
        if (avg >= 90) return 'A';
        if (avg >= 75) return 'B';
        if (avg >= 60) return 'C';
        return 'D';
    }
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            int[] marks = {85, 90, 78, 88};
            validateMarks(marks);
            students.add(new Student("Rahul", marks));
            for (Student s : students) {
                double avg = calculateAverage(s.marks);
                char grade = assignGrade(avg);
                System.out.println("----- Report Card -----");
                System.out.println("Name   : " + s.name);
                System.out.println("Average: " + avg);
                System.out.println("Grade  : " + grade);
            }
        } catch (InvalidMarkException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}