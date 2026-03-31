import java.util.Scanner;
public class DistanceCalculation2 {
  public static void main(String[]args){
    Scanner sc = new Scanner(System.in);

    double distanceInFeet=sc.nextDouble();
    double distanceInYards = distanceInFeet / 3;
    double distanceInMiles = distanceInYards / 1760;

    System.out.print("The distance in yards is "+distanceInYards+" while the distance in miles is "+distanceInMiles);

  }
  
}
