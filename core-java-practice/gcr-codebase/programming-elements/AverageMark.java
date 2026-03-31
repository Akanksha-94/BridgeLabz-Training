public class AverageMark {
  public static void main(String[] args) {
    // Marks for each subject out of 100
    int mathMarks = 94;
    int physicsMarks = 95;
    int chemistryMarks = 96;
    
    // average
    double average = (mathMarks + physicsMarks + chemistryMarks) / 3.0;
    

    System.out.println("Sam's average mark in PCM is " + average + "%");
  }
}