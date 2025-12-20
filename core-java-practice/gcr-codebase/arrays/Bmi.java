  import java.util.Scanner;

public class Bmi {



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take number of persons
        System.out.print("Enter number of persons: ");
        int number = sc.nextInt();

        // personData[row][0] = weight
        // personData[row][1] = height
        // personData[row][2] = BMI
        double[][] personData = new double[number][3];
        String[] weightStatus = new String[number];

        // Input data
        for (int i = 0; i < number; i++) {
            System.out.println("\nPerson " + (i + 1));

            // Input positive weight
            double weight;
            do {
                System.out.print("Enter weight (kg): ");
                weight = sc.nextDouble();
                if (weight <= 0)
                    System.out.println("Weight must be positive!");
            } while (weight <= 0);

            // Input positive height
            double height;
            do {
                System.out.print("Enter height (meters): ");
                height = sc.nextDouble();
                if (height <= 0)
                    System.out.println("Height must be positive!");
            } while (height <= 0);

            personData[i][0] = weight;
            personData[i][1] = height;

            // Calculate BMI
            double bmi = weight / (height * height);
            personData[i][2] = bmi;

            // Determine Weight Status
            if (bmi < 18.5)
                weightStatus[i] = "Underweight";
            else if (bmi < 25)
                weightStatus[i] = "Normal Weight";
            else if (bmi < 30)
                weightStatus[i] = "Overweight";
            else
                weightStatus[i] = "Obese";
        }

        // Display Data
        System.out.println("\n------ Person Health Report ------");
        for (int i = 0; i < number; i++) {
            System.out.println("\nPerson " + (i + 1));
            System.out.println("Weight : " + personData[i][0] + " kg");
            System.out.println("Height : " + personData[i][1] + " m");
            System.out.printf("BMI    : %.2f\n", personData[i][2]);
            System.out.println("Status : " + weightStatus[i]);
        }

      
    }
}


