import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class GymMember {
    String name;
    LocalDate membershipExpiry;
    
    GymMember(String name, LocalDate membershipExpiry) {
        this.name = name;
        this.membershipExpiry = membershipExpiry;
    }
    
    @Override
    public String toString() {
        return name + " (expires: " + membershipExpiry + ")";
    }
}

public class FilteringExpiringMemberships {
    public static void main(String[] args) {
        List<GymMember> members = Arrays.asList(
            new GymMember("John", LocalDate.now().plusDays(15)),
            new GymMember("Alice", LocalDate.now().plusDays(45)),
            new GymMember("Bob", LocalDate.now().plusDays(10)),
            new GymMember("Carol", LocalDate.now().plusDays(60)),
            new GymMember("David", LocalDate.now().plusDays(25))
        );
        
        LocalDate thirtyDaysFromNow = LocalDate.now().plusDays(30);
        
        List<GymMember> expiringMembers = members.stream()
            .filter(member -> member.membershipExpiry.isBefore(thirtyDaysFromNow) || 
                             member.membershipExpiry.isEqual(thirtyDaysFromNow))
            .collect(Collectors.toList());
        
        System.out.println("Members with expiring memberships (within 30 days):");
        expiringMembers.forEach(System.out::println);
    }
}