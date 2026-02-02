import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CountRowsInCSV {

    public static void main(String[] args) {
        String filePath = "io-programming\\\\gcr-codebase\\\\csv-file-handling\\\\student_details.csv";
        countRecords(filePath);
    }

    static void countRecords(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            
            if (lines.isEmpty()) {
                System.out.println("File is empty");
                return;
            }

            int recordCount = lines.size() - 1;
            System.out.println("Total number of records (excluding header): " + recordCount);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}