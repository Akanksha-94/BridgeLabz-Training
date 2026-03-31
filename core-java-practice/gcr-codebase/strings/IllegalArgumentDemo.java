import java.util.Scanner;

public class IllegalArgumentDemo {

    //Generates IllegalArgumentException
    static void generateException(String text) {
        System.out.println(text.substring(5, 2));  
        // start index > end index --------IllegalArgumentException
    }

    //Handles IllegalArgumentException
    static void handleException(String text) {
        try {
            System.out.println(text.substring(5, 2));
        } 
        catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException handled successfully!");
        }
        catch (Exception e) {
            System.out.println("Other Exception handled: " + e);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str = sc.nextLine();

        System.out.println("\nGenerating Exception:");
        try {
            generateException(str);  
        } 
        catch (Exception e) {
            System.out.println("Exception occurred: " + e);
        }

        System.out.println("\nHandling Exception:");
        handleException(str);    
    }
}
