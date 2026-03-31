import java.util.Scanner;

public class StudentGradesArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Take input for number of students
        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        int[] physics = new int[n];
        int[] chemistry = new int[n];
        int[] maths = new int[n];

        double[] percentage = new double[n];
        char[] grade = new char[n];

        // b + c. Take input marks with validation
        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));

            // Physics Marks
            do {
                System.out.print("Enter Physics marks: ");
                physics[i] = sc.nextInt();
                if (physics[i] < 0) System.out.println("Enter positive marks!");
            } while (physics[i] < 0);

            // Chemistry Marks
            do {
                System.out.print("Enter Chemistry marks: ");
                chemistry[i] = sc.nextInt();
                if (chemistry[i] < 0) System.out.println("Enter positive marks!");
            } while (chemistry[i] < 0);

            // Maths Marks
            do {
                System.out.print("Enter Maths marks: ");
                maths[i] = sc.nextInt();
                if (maths[i] < 0) System.out.println("Enter positive marks!");
            } while (maths[i] < 0);

            // d. Calculate Percentage
            percentage[i] = (physics[i] + chemistry[i] + maths[i]) / 3.0;

            // Grade Decision
            if (percentage[i] >= 80)
                grade[i] = 'A';
            else if (percentage[i] >= 70)
                grade[i] = 'B';
            else if (percentage[i] >= 60)
                grade[i] = 'C';
            else if (percentage[i] >= 50)
                grade[i] = 'D';
            else if (percentage[i] >= 40)
                grade[i] = 'E';
            else
                grade[i] = 'R';
        }

        // e. Display Result
        System.out.println("\n===== STUDENT RESULT REPORT =====");
        for (int i = 0; i < n; i++) {
            System.out.println("\nStudent " + (i + 1));
            System.out.println("Physics Marks   : " + physics[i]);
            System.out.println("Chemistry Marks : " + chemistry[i]);
            System.out.println("Maths Marks     : " + maths[i]);
            System.out.printf("Percentage      : %.2f%%\n", percentage[i]);
            System.out.println("Grade           : " + grade[i]);
        }

    
    }
}
