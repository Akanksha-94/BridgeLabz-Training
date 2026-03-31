import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FilterRecordsFromCSV {

    public static void main(String[] args) {
        String filePath = "io-programming\\gcr-codebase\\csv-file-handling\\student_details.csv";
        filterStudentsByMarks(filePath, 80);
    }

    static void filterStudentsByMarks(String filePath, int threshold) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            
            if (lines.isEmpty()) {
                System.out.println("File is empty");
                return;
            }

            System.out.println("Students with marks > " + threshold + ":");
            System.out.println("================================================");
            System.out.printf("%-8s %-20s %-8s %-10s%n", "ID", "Name", "Age", "Marks");
            System.out.println("================================================");

            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                int marks = Integer.parseInt(fields[3].trim());
                
                if (marks > threshold) {
                    System.out.printf("%-8s %-20s %-8s %-10s%n", 
                        fields[0], fields[1], fields[2], fields[3]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}