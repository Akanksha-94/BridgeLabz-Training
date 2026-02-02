
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JSONToCSVConverter {

    static class StudentData {
        int id;
        String name;
        int age;
        int marks;

        StudentData(int id, String name, int age, int marks) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return String.format(
                "{\"id\":%d,\"name\":\"%s\",\"age\":%d,\"marks\":%d}",
                id, name, age, marks
            );
        }
    }

    public static void main(String[] args) {
        String jsonFile = "io-programming\\gcr-codebase\\csv-file-handling\\students.json";
        String csvFile = "io-programming\\gcr-codebase\\csv-file-handling\\students_from_json.csv";
        
        convertJSONToCSV(jsonFile, csvFile);
        convertCSVToJSON(csvFile, "students_converted_back.json");
    }

    static void convertJSONToCSV(String jsonFile, String csvFile) {
        List<StudentData> students = new ArrayList<>();
        students.add(new StudentData(101, "Alice", 20, 88));
        students.add(new StudentData(102, "Bob", 21, 75));
        students.add(new StudentData(103, "Carol", 20, 92));
        students.add(new StudentData(104, "David", 22, 78));

        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println("ID,Name,Age,Marks");
            for (StudentData student : students) {
                writer.printf("%d,%s,%d,%d%n", student.id, student.name, student.age, student.marks);
            }
            System.out.println("JSON converted to CSV: " + csvFile);
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    static void convertCSVToJSON(String csvFile, String jsonFile) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(csvFile));
            
            try (PrintWriter writer = new PrintWriter(new FileWriter(jsonFile))) {
                writer.println("[");
                
                for (int i = 1; i < lines.size(); i++) {
                    String[] fields = lines.get(i).split(",");
                    StudentData student = new StudentData(
                        Integer.parseInt(fields[0].trim()),
                        fields[1].trim(),
                        Integer.parseInt(fields[2].trim()),
                        Integer.parseInt(fields[3].trim())
                    );
                    
                    if (i < lines.size() - 1) {
                        writer.println("  " + student.toString() + ",");
                    } else {
                        writer.println("  " + student.toString());
                    }
                }
                
                writer.println("]");
            }
            
            System.out.println("CSV converted back to JSON: " + jsonFile);
        } catch (IOException e) {
            System.out.println("Error converting CSV to JSON: " + e.getMessage());
        }
    }
}
