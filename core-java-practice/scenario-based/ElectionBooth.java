import java.util.Scanner;

public class ElectionBooth {
    public static void main(String[] args) {

        Scanner sc =  new Scanner(System.in);

        int vote_1 = 0, vote_2 = 0, vote_3 = 0;

        while (true) {
            System.out.print("\nEnter Age (or -1 to stop voting): ");
            int age = sc.nextInt();

            if (age == -1) {
                System.out.println("\nVoting Stopped.");
                break;
            }

            if (age < 18) {
                System.out.println("Not eligible to vote!");

                continue;
            }

            System.out.print("Enter your vote (1 / 2 / 3): ");
            
            int vote = sc.nextInt();

            if (vote == 1)
                vote_1++;
            else if (vote == 2)
                vote_2++;
            else if (vote == 3)
                vote_3++;
            else
                System.out.println("Invalid Vote!");
        }

        System.out.println("\n--- Voting Result ---");
        System.out.println("Candidate 1 Votes: " + vote_1);
        System.out.println("Candidate 2 Votes: " + vote_2);
        System.out.println("Candidate 3 Votes: " + vote_3);

     
    }
}
