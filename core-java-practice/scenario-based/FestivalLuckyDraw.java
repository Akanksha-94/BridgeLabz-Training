import java.util.Scanner;

public class FestivalLuckyDraw {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter num of visitors: ");
        int visitors = sc.nextInt();

        for (int i = 1; i <= visitors; i++) {

            System.out.print("Visitor " + i + " - Enter your lucky num: ");
            int num = sc.nextInt();

            if (num <= 0) {
              
                System.out.println("Invalid num! Try again.");

                continue;   // Skip this visitor
            }

            if (num % 3 == 0 && num % 5 == 0) {

                System.out.println("Congratulations! 🎁 You won a gift!");

            } 
            else {
                System.out.println("Better  luck next time!");
            }
        }

      
    }
}
