import java.util.Scanner;
public class KilometersToMiles {

  public static void main(String[] args) {
    double km;
    double miles=1.6;
    Scanner sc =new Scanner(System.in);
     double kmInput=sc.nextDouble();
      double milesOutput=kmInput * miles;

    System.out.println("The distance in miles is: " + milesOutput);
  }
}
