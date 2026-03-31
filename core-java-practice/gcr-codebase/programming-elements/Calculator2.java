
 import java.util.Scanner;
 public class Calculator2 {
 
  public static void main(String [] args){
    Scanner sc = new Scanner(System.in);
    float number1= sc.nextFloat();
    float number2= sc.nextFloat();
    float add =number1+number2;
    float sub=number1-number2;
    float mul=number1-number2;
    float div=number1-number2;

    System.out.println("The addition, subtraction, multiplication and division value of 2 numbers  "+ number1 +"and"+ number2+" is "+add+","+sub+","+mul+","+div );

  }

  


}
