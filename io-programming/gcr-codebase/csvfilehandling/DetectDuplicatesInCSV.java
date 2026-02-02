import java.io.*;
import java.nio.file.*;
import java.util.*;

public class DetectDuplicatesInCSV {

    static class DuplicateRecord {
        int id;
        String data;
        int occurrences;

        DuplicateRecord(int id, String data, int occurrences) {
            this.id = id;
            this.data = data;
            this.occurrences = occurrences;
        }
    }

    public static void main(String[] args) {
        String filePath = "io-programming\\gcr-codebase\\csv-file-handling\\students_with_duplicates.csv";
        detectDuplicates(filePath);
    }

    static void detectDuplicates(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            Map<String, Integer> idCount = new HashMap<>();
            Map<String, String> idData = new HashMap<>();

            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                String id = fields[0].trim();
                
                idCount.put(id, idCount.getOrDefault(id, 0) + 1);
                idData.put(id, lines.get(i));
            }

            List<String> duplicateIds = new ArrayList<>();
            for (String id : idCount.keySet()) {
                if (idCount.get(id) > 1) {
                    duplicateIds.add(id);
                }
            }

            if (duplicateIds.isEmpty()) {
                System.out.println("No duplicate records found.");
            } else {
                System.out.println("Duplicate Records Found:");
                System.out.println("================================================");
                
                for (String id : duplicateIds) {
                    System.out.println("ID: " + id + " (Found " + idCount.get(id) + " times)");
                    System.out.println("Record: " + idData.get(id));
                    System.out.println("----------------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}