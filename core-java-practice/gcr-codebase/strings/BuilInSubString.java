import java.util.Scanner;

public class BuilInSubString{
  public static void main(String[] args) {
    Scanner sc =new Scanner (System.in);
    String str1=sc.nextLine();
    int start =sc.nextInt();
    int end =sc.nextInt();

   String manual = "";
    for(int i = start; i < end; i++)
      manual +=str1.charAt(i);

    String builtin =str1.substring(start, end);

    System.out.println("charAt substring: " + manual);
    System.out.println("substring(): " + builtin);
    System.out.println(manual.equals(builtin) ? "Same" : "Different");
  }
}