import java.util.Scanner;

public class StringIndexOutOfBoundDemo {

    //Generates Exception
    static void generateException(String text) {
        System.out.println(text.charAt(50));   // Accessing invalid index
    }

    //Handles Exception
    static void handleException(String text) {
        try {
            System.out.println(text.charAt(50));  
        } 
        catch (StringIndexOutOfBoundsException e) {
            System.out.println("StringIndexOutOfBoundsException handled successfully!");
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
