import java.util.Scanner;

public class CalculateMarks {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int physics=sc.nextInt();
    int chemistry=sc.nextInt();   
    int maths=sc.nextInt();

    int total=physics + chemistry+maths;
    double avg=total/3.0;

    String grade;
    String remarks;

    if(avg>=80){
      grade="A";
      remarks="Level 4, above-normalized standaeds";

    }else if(avg>=70){
      grade="B";
      remarks="Level 3,at agency-normalized standards";

    }else if(avg>=60){
      grade="C";
      remarks="Level 2,below,but approaching agency-normalized standards";  
  }else if(avg>=50){
      grade="D";
      remarks="Level 1,well below agency-normalized standards";  
    }else if(avg>=40){
      grade="E";
      remarks="Level 1,too below agency-normalized standards";  
    }else{
      grade="R";
      remarks="Remedial standards";
    }

        System.out.println("\n----- RESULT -----");
        System.out.println("Average / Percentage : " + avg + "%");
        System.out.println("Grade : " + grade);
        System.out.println("Remarks : " + remarks);
}
}
