import java.util.Scanner;

import javax.sound.sampled.Line;

public class LineComparisonProblem {

  //ug1-
  static double LineLength(int x1, int x2, int y1, int y2) {

    double length= Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    return length;
  }

   //ug2
static boolean EqualityCheck(Double length1 , Double length2){ // double is considered as object here now so i can use .equals method
if(length1.equals(length2)){
  return true;
}else{
  return false;
}

}

//ug3
static boolean CompareTo(Double length1 , Double length2){
  return length1.compareTo(length2)==0;   //true   
}
  

   


  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int x1 = sc.nextInt();
    int x2 = sc.nextInt();
    int y1 = sc.nextInt();
    int y2 = sc.nextInt();

    int a1=sc.nextInt();
    int a2=sc.nextInt();
    int b1=sc.nextInt();
    int b2=sc.nextInt();


//ug1
    double length1= LineLength(x1, x2, y1, y2);
    System.out.println("length of line is:" + length1);

    double length2=LineLength(a1, a2, b1, b2);

    //ug2-
      System.out.println( "check length is equal or not :" +EqualityCheck(length1, length2));


      //ug3
           if(CompareTo(length1, length2)){
            System.out.println("lines are equals");
           }else if(length1>length2){
            System.out.println("line1 is greater than line2");
           }else{
            System.out.println("line2 is greater than line1");
           }



  }

}
