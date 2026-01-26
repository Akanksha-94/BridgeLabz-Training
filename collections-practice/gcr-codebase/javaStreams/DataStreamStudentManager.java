import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

class StudentData {
  public int rollNumber;
  public String name;
  public double gpa;

  public StudentData(int rollNumber, String name, double gpa) {
    this.rollNumber = rollNumber;
    this.name = name;
    this.gpa = gpa;
  }

  public StudentData() {
    this(0, "", 0.0);
  }

  @Override
  public String toString() {
    return "Roll: " + rollNumber + ", Name: " + name + ", GPA: " + gpa;
  }
}

public class DataStreamStudentManager {

  public static void saveStudents(StudentData[] students, String filename) {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
      dos.writeInt(students.length);

      for (StudentData student : students) {
        dos.writeInt(student.rollNumber);
        dos.writeUTF(student.name);
        dos.writeDouble(student.gpa);
      }

      System.out.println("Students saved successfully to " + filename);

    } catch (IOException e) {
      System.out.println("Error saving students: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static StudentData[] loadStudents(String filename) {
    StudentData[] students = null;

    try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
      int count = dis.readInt();
      students = new StudentData[count];

      for (int i = 0; i < count; i++) {
        int rollNumber = dis.readInt();
        String name = dis.readUTF();
        double gpa = dis.readDouble();
        students[i] = new StudentData(rollNumber, name, gpa);
      }

      System.out.println("Students loaded successfully from " + filename);

    } catch (IOException e) {
      System.out.println("Error loading students: " + e.getMessage());
      e.printStackTrace();
    }

    return students;
  }

  public static void main(String[] args) {
    StudentData[] students = {
        new StudentData(1001, "Alice Brown", 3.85),
        new StudentData(1002, "Bob Green", 3.72),
        new StudentData(1003, "Carol White", 3.95),
        new StudentData(1004, "David Black", 3.68)
    };

    String filename = "students.dat";

    saveStudents(students, filename);

    StudentData[] loadedStudents = loadStudents(filename);
    System.out.println("\nLoaded Students:");
    if (loadedStudents != null) {
      for (StudentData student : loadedStudents) {
        System.out.println(student);
      }
    }
  }
}
