import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ConvertCSVToObjects {

    static class Student {
        int id;
        String name;
        int age;
        int marks;

        Student(int id, String name, int age, int marks) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return String.format("Student{id=%d, name='%s', age=%d, marks=%d}", 
                id, name, age, marks);
        }
    }

    public static void main(String[] args) {
       String filePath = "io-programming\\gcr-codebase\\csv-file-handling\\student_details.csv";


        List<Student> students = convertCSVToStudentObjects(filePath);
        
        System.out.println("Student Objects:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    static List<Student> convertCSVToStudentObjects(String filePath) {
        List<Student> students = new ArrayList<>();
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                Student student = new Student(
                    Integer.parseInt(fields[0].trim()),
                    fields[1].trim(),
                    Integer.parseInt(fields[2].trim()),
                    Integer.parseInt(fields[3].trim())
                );
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return students;
    }
}