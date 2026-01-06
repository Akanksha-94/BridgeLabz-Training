import java.util.ArrayList;
import java.util.List;

// Interface for medical records-
interface MedicalRecord {
    void addRecord(String record);
    void viewRecords();
}

// Abstract class-
abstract class Patient {

    // Encapsulated fields-
    private int patientId;
    private String name;
    private int age;

    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    // Abstract method-
    public abstract double calculateBill();

    // Concrete method-
    public void getPatientDetails() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }

    // Getters for encapsulation-
    public int getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
}

// InPatient class-
class InPatient extends Patient implements MedicalRecord {

    private int daysAdmitted;
    private double dailyRate;
    private List<String> medicalHistory = new ArrayList<>();

    public InPatient(int patientId, String name, int age, int daysAdmitted, double dailyRate) {
        super(patientId, name, age);
        this.daysAdmitted = daysAdmitted;
        this.dailyRate = dailyRate;
    }

    @Override
    public double calculateBill() {
        return daysAdmitted * dailyRate; // billing logic for in-patient
    }

    @Override
    public void addRecord(String record) {
        medicalHistory.add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical History:");
        for (String record : medicalHistory) {
            System.out.println("- " + record);
        }
    }
}

// OutPatient class-
class OutPatient extends Patient implements MedicalRecord {

    private double consultationFee;
    private List<String> medicalHistory = new ArrayList<>();

    public OutPatient(int patientId, String name, int age, double consultationFee) {
        super(patientId, name, age);
        this.consultationFee = consultationFee;
    }

    @Override
    public double calculateBill() {
        return consultationFee; // billing logic for out-patient
    }

    @Override
    public void addRecord(String record) {
        medicalHistory.add(record);
    }

    @Override
    public void viewRecords() {
        System.out.println("Medical History:");
        for (String record : medicalHistory) {
            System.out.println("- " + record);
        }
    }
}

// Main class-
public class HospitalPatientManagement {

    public static void main(String[] args) {

        // List of patients (polymorphism)
        List<Patient> patients = new ArrayList<>();

        InPatient inPatient = new InPatient(101, "Amit", 30, 5, 2000);
        inPatient.addRecord("Appendectomy");
        inPatient.addRecord("Blood Test");

        OutPatient outPatient = new OutPatient(102, "Riya", 25, 500);
        outPatient.addRecord("General Checkup");

        patients.add(inPatient);
        patients.add(outPatient);

        // Iterate and process all patients
        for (Patient patient : patients) {
            patient.getPatientDetails();
            System.out.println("Bill Amount: " + patient.calculateBill());

            if (patient instanceof MedicalRecord) {
                MedicalRecord recordPatient = (MedicalRecord) patient;
                recordPatient.viewRecords();
            }

            System.out.println("--------------------------------");
        }
    }
}
