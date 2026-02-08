import java.util.*;

public class EmailNotifications {
    
    public static void sendEmailNotification(String email) {
        System.out.println("Sending notification email to: " + email);
        // Simulate email sending
        System.out.println("Email sent successfully to " + email);
    }
    
    public static void main(String[] args) {
        List<String> emails = Arrays.asList(
            "john@example.com",
            "alice@company.com",
            "bob@domain.org",
            "carol@business.net",
            "david@startup.io"
        );
        
        System.out.println("Sending notification emails to all users:");
        emails.forEach(email -> sendEmailNotification(email));
        
        System.out.println("\\nAll notifications sent successfully!");
    }
}