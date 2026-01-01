class Patient {
    static String hospitalName = "City Hospital";   // Static variable shared by all
    static int totalPatients = 0;                    // To count total patients

    String name;
    int age;
    String ailment;
    final String patientID;                          // Final → cannot be changed

    // Constructor using 'this'
    Patient(String name, int age, String ailment, String patientID) {
        this.name = name;
        this.age = age;
        this.ailment = ailment;
        this.patientID = patientID;
        totalPatients++;                             // Count patient
    }

    // Static method to get total patients
    static void getTotalPatients() {
        System.out.println("Total Patients Admitted: " + totalPatients);
    }

    // Method to display details with instanceof
    void displayDetails(Object obj) {
        if (obj instanceof Patient) {
            Patient p = (Patient) obj;
            System.out.println("Hospital Name: " + hospitalName);
            System.out.println("Patient ID: " + p.patientID);
            System.out.println("Name: " + p.name);
            System.out.println("Age: " + p.age);
            System.out.println("Ailment: " + p.ailment);
        } else {
            System.out.println("Invalid Object");
        }
    }
}

public class HospitalManagementSystem {
    public static void main(String[] args) {

        Patient p1 = new Patient("Lathika", 30, "Flu", "P001");
        Patient p2 = new Patient("Lidiya", 45, "Fracture", "P002");

        Patient.getTotalPatients();  // Static method call

        p1.displayDetails(p1);
        System.out.println();
        p2.displayDetails(p2);
    }
}
