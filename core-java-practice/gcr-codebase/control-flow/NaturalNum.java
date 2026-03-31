import java.util.Scanner;
public class NaturalNum {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a positive integer: ");
    int number= sc.nextInt();

    if (number> 0) {
     
           int sumNaturalNumbers = (number * (number + 1)) / 2;
           System.out.println("The sum of " + number+" natural numbers is" + sumNaturalNumbers);

      }
     else {
      System.out.println("Otherwise The number "+ number+" is not a natural number");

     
      
    }

  }
}
