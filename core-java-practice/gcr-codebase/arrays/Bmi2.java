  import java.util.Scanner;

public class Bmi2 {



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // a. Take number of persons
        System.out.print("Enter number of persons: ");
        int n = sc.nextInt();

        double[] weight = new double[n];
        double[] height = new double[n];
        double[] bmi = new double[n];
        String[] status = new String[n];

        // b + c + d : Take input and calculate BMI + status
        for (int i = 0; i < n; i++) {
            System.out.println("\nPerson " + (i + 1));

            // Take valid weight
            do {
                System.out.print("Enter weight (kg): ");
                weight[i] = sc.nextDouble();
                if (weight[i] <= 0)
                    System.out.println("Please enter positive weight!");
            } while (weight[i] <= 0);

            // Take valid height
            do {
                System.out.print("Enter height (meters): ");
                height[i] = sc.nextDouble();
                if (height[i] <= 0)
                    System.out.println("Please enter positive height!");
            } while (height[i] <= 0);

            // Calculate BMI
            bmi[i] = weight[i] / (height[i] * height[i]);

            // Determine status
            if (bmi[i] <= 18.4)
                status[i] = "Underweight";
            else if (bmi[i] <= 24.9)
                status[i] = "Normal";
            else if (bmi[i] <= 39.9)
                status[i] = "Overweight";
            else
                status[i] = "Obese";
        }

        // e. Display results
        System.out.println("\n===== BMI REPORT =====");
        for (int i = 0; i < n; i++) {
            System.out.println("\nPerson " + (i + 1));
            System.out.println("Height : " + height[i] + " m");
            System.out.println("Weight : " + weight[i] + " kg");
            System.out.printf("BMI    : %.2f\n", bmi[i]);
            System.out.println("Status : " + status[i]);
        }

        
    }
}


