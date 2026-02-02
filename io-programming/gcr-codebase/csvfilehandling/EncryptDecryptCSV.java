import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptDecryptCSV {

    static class EmployeeSecure {
        String id;
        String name;
        String department;
        String salary;

        EmployeeSecure(String id, String name, String department, String salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
    }

    static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        return keyGenerator.generateKey();
    }

    static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    public static void main(String[] args) {
        try {
            SecretKey key = generateKey();
            
            String encryptedFile = "io-programming\\gcr-codebase\\csv-file-handling\\employees_encrypted.csv";
            encryptAndWriteCSV(encryptedFile, key);
            
            String decryptedFile = "io-programming\\gcr-codebase\\csv-file-handling\\employees_decrypted.csv";
            readAndDecryptCSV(encryptedFile, decryptedFile, key);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void encryptAndWriteCSV(String filePath, SecretKey key) throws Exception {
        List<EmployeeSecure> employees = new ArrayList<>();
        employees.add(new EmployeeSecure("101", "Alice Johnson", "IT", "75000"));
        employees.add(new EmployeeSecure("102", "Bob Smith", "Finance", "65000"));
        employees.add(new EmployeeSecure("103", "Carol White", "HR", "60000"));

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("ID,Name,Department,Salary(Encrypted)");
            
            for (EmployeeSecure emp : employees) {
                String encryptedSalary = encrypt(emp.salary, key);
                writer.printf("%s,%s,%s,%s%n", emp.id, emp.name, emp.department, encryptedSalary);
            }
            
            System.out.println("Encrypted CSV file created: " + filePath);
        }
    }

    static void readAndDecryptCSV(String encryptedFile, String decryptedFile, SecretKey key) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(encryptedFile));

        try (PrintWriter writer = new PrintWriter(new FileWriter(decryptedFile))) {
            writer.println("ID,Name,Department,Salary");
            
            for (int i = 1; i < lines.size(); i++) {
                String[] fields = lines.get(i).split(",");
                String decryptedSalary = decrypt(fields[3], key);
                writer.printf("%s,%s,%s,%s%n", fields[0], fields[1], fields[2], decryptedSalary);
            }
            
            System.out.println("Decrypted CSV file created: " + decryptedFile);
        }
    }
}