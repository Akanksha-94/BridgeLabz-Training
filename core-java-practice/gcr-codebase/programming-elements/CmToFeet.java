import java.util.Scanner;

public class CmToFeet {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double hCm = sc.nextDouble();
        double totalInches = hCm / 2.54;
        int feet = (int) (totalInches / 12);
        double inche = totalInches % 12;

        System.out.println(
            "Your Height in cm is " + hCm +" while in feet is " + feet +" and inches is " + inche);

        
    }
}
