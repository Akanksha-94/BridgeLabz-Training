import java.util.*;
import java.util.stream.Collectors;

class InsuranceClaim {
    String claimType;
    double amount;
    
    InsuranceClaim(String claimType, double amount) {
        this.claimType = claimType;
        this.amount = amount;
    }
}

public class InsuranceClaimAnalysis {
    public static void main(String[] args) {
        List<InsuranceClaim> claims = Arrays.asList(
            new InsuranceClaim("Health", 1500.0),
            new InsuranceClaim("Auto", 2500.0),
            new InsuranceClaim("Health", 800.0),
            new InsuranceClaim("Home", 3200.0),
            new InsuranceClaim("Auto", 1800.0),
            new InsuranceClaim("Health", 1200.0),
            new InsuranceClaim("Home", 2800.0)
        );
        
        Map<String, Double> averageClaimByType = claims.stream()
            .collect(Collectors.groupingBy(
                claim -> claim.claimType,
                Collectors.averagingDouble(claim -> claim.amount)
            ));
        
        System.out.println("Average claim amount by type:");
        averageClaimByType.forEach((type, avg) -> 
            System.out.println(type + ": $" + String.format("%.2f", avg)));
    }
}