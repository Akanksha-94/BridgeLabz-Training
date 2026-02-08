import java.util.*;

public class EventAttendeeWelcome {
    public static void main(String[] args) {
        List<String> attendees = Arrays.asList(
            "John Smith",
            "Alice Johnson",
            "Bob Brown",
            "Carol Davis",
            "David Wilson"
        );
        
        System.out.println("Welcome Messages for Event Attendees:");
        attendees.forEach(attendee -> 
            System.out.println("Welcome to the conference, " + attendee + "! We're glad you're here."));
    }
}