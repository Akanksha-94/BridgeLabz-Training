import java.util.*;

public class SensitiveDataTagging {
    
    // Custom marker interface
    interface SensitiveData {}
    
    static class CreditCard implements SensitiveData {
        String cardNumber;
        String cvv;
        String expiryDate;
        
        CreditCard(String cardNumber, String cvv, String expiryDate) {
            this.cardNumber = cardNumber;
            this.cvv = cvv;
            this.expiryDate = expiryDate;
        }
        
        @Override
        public String toString() {
            return "CreditCard{cardNumber='" + cardNumber + "', cvv='" + cvv + "', expiry='" + expiryDate + "'}";
        }
    }
    
    static class MedicalRecord implements SensitiveData {
        String patientId;
        String diagnosis;
        String treatment;
        
        MedicalRecord(String patientId, String diagnosis, String treatment) {
            this.patientId = patientId;
            this.diagnosis = diagnosis;
            this.treatment = treatment;
        }
        
        @Override
        public String toString() {
            return "MedicalRecord{patientId='" + patientId + "', diagnosis='" + diagnosis + "', treatment='" + treatment + "'}";
        }
    }
    
    static class PublicProfile {
        String username;
        String bio;
        
        PublicProfile(String username, String bio) {
            this.username = username;
            this.bio = bio;
        }
        
        @Override
        public String toString() {
            return "PublicProfile{username='" + username + "', bio='" + bio + "'}";
        }
    }
    
    static class EncryptionService {
        public void processData(Object data) {
            if (data instanceof SensitiveData) {
                System.out.println("🔒 ENCRYPTING: " + data);
                encrypt(data);
            } else {
                System.out.println("✓ PUBLIC DATA: " + data);
            }
        }
        
        private void encrypt(Object data) {
            System.out.println("   → Applying AES-256 encryption");
            System.out.println("   → Data secured for storage");
        }
    }
    
    public static void main(String[] args) {
        EncryptionService service = new EncryptionService();
        
        CreditCard card = new CreditCard("1234-5678-9012-3456", "123", "12/25");
        MedicalRecord record = new MedicalRecord("P001", "Hypertension", "Medication prescribed");
        PublicProfile profile = new PublicProfile("john_doe", "Software Developer");
        
        System.out.println("Processing data objects:\n");
        service.processData(card);
        System.out.println();
        service.processData(record);
        System.out.println();
        service.processData(profile);
    }
}