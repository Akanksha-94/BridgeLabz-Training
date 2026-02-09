import java.util.*;

public class HealthClinicApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        PatientManagement patientMgmt = new PatientManagement();
        DoctorManagement doctorMgmt = new DoctorManagement();
        AppointmentScheduling appointmentMgmt = new AppointmentScheduling();
        VisitManagement visitMgmt = new VisitManagement();
        BillingManagement billingMgmt = new BillingManagement();
        SystemAdministration adminMgmt = new SystemAdministration();
        
        while (true) {
            System.out.println("\n=== Health Clinic Management System ===");
            System.out.println("1. Patient Management");
            System.out.println("2. Doctor Management");
            System.out.println("3. Appointment Scheduling");
            System.out.println("4. Visit Management");
            System.out.println("5. Billing & Payments");
            System.out.println("6. System Administration");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    handlePatientManagement(sc, patientMgmt);
                    break;
                case 2:
                    handleDoctorManagement(sc, doctorMgmt);
                    break;
                case 3:
                    handleAppointmentScheduling(sc, appointmentMgmt);
                    break;
                case 4:
                    handleVisitManagement(sc, visitMgmt);
                    break;
                case 5:
                    handleBillingManagement(sc, billingMgmt);
                    break;
                case 6:
                    handleSystemAdministration(sc, adminMgmt);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
    
    static void handlePatientManagement(Scanner sc, PatientManagement mgmt) {
        System.out.println("\n1. Register Patient\n2. Update Patient\n3. Search Patient\n4. View Visit History");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("DOB (YYYY-MM-DD): ");
                String dob = sc.nextLine();
                System.out.print("Contact: ");
                String contact = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Address: ");
                String address = sc.nextLine();
                System.out.print("Blood Group: ");
                String bloodGroup = sc.nextLine();
                mgmt.registerPatient(name, dob, contact, email, address, bloodGroup);
                break;
            case 2:
                System.out.print("Patient ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("New Name: ");
                name = sc.nextLine();
                System.out.print("New Contact: ");
                contact = sc.nextLine();
                System.out.print("New Address: ");
                address = sc.nextLine();
                mgmt.updatePatient(id, name, contact, address);
                break;
            case 3:
                System.out.print("Search term: ");
                String term = sc.nextLine();
                mgmt.searchPatients(term);
                break;
            case 4:
                System.out.print("Patient ID: ");
                id = sc.nextInt();
                mgmt.viewVisitHistory(id);
                break;
        }
    }
    
    static void handleDoctorManagement(Scanner sc, DoctorManagement mgmt) {
        System.out.println("\n1. Add Doctor\n2. Update Specialty\n3. View by Specialty\n4. Deactivate Doctor");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Specialty ID: ");
                int specId = sc.nextInt();
                sc.nextLine();
                System.out.print("Contact: ");
                String contact = sc.nextLine();
                System.out.print("Consultation Fee: ");
                double fee = sc.nextDouble();
                mgmt.addDoctor(name, specId, contact, fee);
                break;
            case 2:
                System.out.print("Doctor ID: ");
                int docId = sc.nextInt();
                System.out.print("New Specialty ID: ");
                specId = sc.nextInt();
                mgmt.updateDoctorSpecialty(docId, specId);
                break;
            case 3:
                System.out.print("Specialty Name: ");
                String specName = sc.nextLine();
                mgmt.viewDoctorsBySpecialty(specName);
                break;
            case 4:
                System.out.print("Doctor ID: ");
                docId = sc.nextInt();
                mgmt.deactivateDoctor(docId);
                break;
        }
    }
    
    static void handleAppointmentScheduling(Scanner sc, AppointmentScheduling mgmt) {
        System.out.println("\n1. Book Appointment\n2. Cancel Appointment\n3. Reschedule\n4. View Daily Schedule");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Patient ID: ");
                int patId = sc.nextInt();
                System.out.print("Doctor ID: ");
                int docId = sc.nextInt();
                sc.nextLine();
                System.out.print("Date (YYYY-MM-DD): ");
                String date = sc.nextLine();
                System.out.print("Time (HH:MM:SS): ");
                String time = sc.nextLine();
                mgmt.bookAppointment(patId, docId, date, time);
                break;
            case 2:
                System.out.print("Appointment ID: ");
                int appId = sc.nextInt();
                mgmt.cancelAppointment(appId);
                break;
            case 3:
                System.out.print("Appointment ID: ");
                appId = sc.nextInt();
                sc.nextLine();
                System.out.print("New Date (YYYY-MM-DD): ");
                date = sc.nextLine();
                System.out.print("New Time (HH:MM:SS): ");
                time = sc.nextLine();
                System.out.print("New Doctor ID: ");
                docId = sc.nextInt();
                mgmt.rescheduleAppointment(appId, date, time, docId);
                break;
            case 4:
                System.out.print("Date (YYYY-MM-DD): ");
                date = sc.nextLine();
                mgmt.viewDailySchedule(date);
                break;
        }
    }
    
    static void handleVisitManagement(Scanner sc, VisitManagement mgmt) {
        System.out.println("\n1. Record Visit\n2. View Medical History\n3. Add Prescriptions");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Appointment ID: ");
                int appId = sc.nextInt();
                System.out.print("Patient ID: ");
                int patId = sc.nextInt();
                System.out.print("Doctor ID: ");
                int docId = sc.nextInt();
                sc.nextLine();
                System.out.print("Diagnosis: ");
                String diagnosis = sc.nextLine();
                System.out.print("Notes: ");
                String notes = sc.nextLine();
                mgmt.recordVisit(appId, patId, docId, diagnosis, notes);
                break;
            case 2:
                System.out.print("Patient ID: ");
                patId = sc.nextInt();
                mgmt.viewMedicalHistory(patId);
                break;
            case 3:
                System.out.print("Visit ID: ");
                int visitId = sc.nextInt();
                System.out.print("Number of medicines: ");
                int count = sc.nextInt();
                sc.nextLine();
                List<String[]> medicines = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    System.out.print("Medicine " + (i+1) + " Name: ");
                    String name = sc.nextLine();
                    System.out.print("Dosage: ");
                    String dosage = sc.nextLine();
                    System.out.print("Duration: ");
                    String duration = sc.nextLine();
                    medicines.add(new String[]{name, dosage, duration});
                }
                mgmt.addPrescriptions(visitId, medicines);
                break;
        }
    }
    
    static void handleBillingManagement(Scanner sc, BillingManagement mgmt) {
        System.out.println("\n1. Generate Bill\n2. Record Payment\n3. View Outstanding Bills\n4. Revenue Report");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Visit ID: ");
                int visitId = sc.nextInt();
                System.out.print("Patient ID: ");
                int patId = sc.nextInt();
                System.out.print("Consultation Fee: ");
                double fee = sc.nextDouble();
                System.out.print("Additional Charges: ");
                double charges = sc.nextDouble();
                mgmt.generateBill(visitId, patId, fee, charges);
                break;
            case 2:
                System.out.print("Bill ID: ");
                int billId = sc.nextInt();
                System.out.print("Amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();
                System.out.print("Payment Mode: ");
                String mode = sc.nextLine();
                mgmt.recordPayment(billId, amount, mode);
                break;
            case 3:
                mgmt.viewOutstandingBills();
                break;
            case 4:
                System.out.print("Start Date (YYYY-MM-DD): ");
                String start = sc.nextLine();
                System.out.print("End Date (YYYY-MM-DD): ");
                String end = sc.nextLine();
                mgmt.generateRevenueReport(start, end);
                break;
        }
    }
    
    static void handleSystemAdministration(Scanner sc, SystemAdministration mgmt) {
        System.out.println("\n1. Add Specialty\n2. Delete Specialty\n3. List Specialties\n4. View Audit Logs\n5. Database Metadata");
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Specialty Name: ");
                String name = sc.nextLine();
                mgmt.addSpecialty(name);
                break;
            case 2:
                System.out.print("Specialty ID: ");
                int id = sc.nextInt();
                mgmt.deleteSpecialty(id);
                break;
            case 3:
                mgmt.listSpecialties();
                break;
            case 4:
                System.out.print("Table Name: ");
                String table = sc.nextLine();
                System.out.print("Start Date (YYYY-MM-DD): ");
                String start = sc.nextLine();
                System.out.print("End Date (YYYY-MM-DD): ");
                String end = sc.nextLine();
                mgmt.viewAuditLogs(table, start, end);
                break;
            case 5:
                mgmt.viewDatabaseMetadata();
                break;
        }
    }
}
