import java.util.Scanner;
public class DoVoteOrNot {
  
  
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter your age: "); 
    int age =sc.nextInt();
    if(18<=age){
      System.out.println("The person's age is "+ age+"and can vote.");
    }else{
       System.out.println("Otherwise the person's age is "+ age+"and can not vote.");
    }

  }

}
