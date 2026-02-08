import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LoggingTransactions {
    public static void main(String[] args) {
        List<String> transactionIds = Arrays.asList(
            "TXN001",
            "TXN002",
            "TXN003",
            "TXN004",
            "TXN005"
        );
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        System.out.println("Transaction Logging:");
        transactionIds.forEach(id -> 
            System.out.println(LocalDateTime.now().format(formatter) + " - Transaction: " + id));
    }
}