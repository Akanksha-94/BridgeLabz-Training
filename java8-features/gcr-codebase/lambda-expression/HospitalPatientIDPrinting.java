import java.util.*;

public class HospitalPatientIDPrinting {
    
    static class Patient {
        String id;
        String name;
        String department;
        
        Patient(String id, String name, String department) {
            this.id = id;
            this.name = name;
            this.department = department;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public String getDepartment() { return department; }
    }
    
    public static void main(String[] args) {
        List<Patient> patients = Arrays.asList(
            new Patient("P001", "John Doe", "Cardiology"),
            new Patient("P002", "Jane Smith", "Neurology"),
            new Patient("P003", "Mike Johnson", "Orthopedics"),
            new Patient("P004", "Sarah Wilson", "Pediatrics"),
            new Patient("P005", "David Brown", "Emergency")
        );
        
        System.out.println("Patient IDs for Admin Verification:");
        System.out.println("===================================");
        
        // Using method reference instead of lambda
        patients.stream()
                .map(Patient::getId)
                .forEach(System.out::println);
        
        System.out.println("\nPatient Names:");
        System.out.println("==============");
        
        // Method reference for getting names
        patients.stream()
                .map(Patient::getName)
                .forEach(System.out::println);
        
        System.out.println("\nDepartments:");
        System.out.println("============");
        
        // Method reference for getting departments
        patients.stream()
                .map(Patient::getDepartment)
                .distinct()
                .forEach(System.out::println);
    }
}