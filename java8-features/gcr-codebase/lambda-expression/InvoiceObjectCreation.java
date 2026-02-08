import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InvoiceObjectCreation {
    
    static class Invoice {
        String transactionId;
        double amount;
        String customerName;
        String date;
        
        // Constructor for method reference
        public Invoice(String transactionId) {
            this.transactionId = transactionId;
            this.amount = Math.random() * 1000 + 100; // Random amount for demo
            this.customerName = "Customer_" + transactionId.substring(1);
            this.date = "2024-01-" + (Math.random() * 28 + 1);
        }
        
        public Invoice(String transactionId, double amount, String customerName, String date) {
            this.transactionId = transactionId;
            this.amount = amount;
            this.customerName = customerName;
            this.date = date;
        }
        
        @Override
        public String toString() {
            return String.format("Invoice{id='%s', amount=$%.2f, customer='%s', date='%s'}", 
                               transactionId, amount, customerName, date);
        }
    }
    
    public static void main(String[] args) {
        List<String> transactionIds = Arrays.asList(
            "T001", "T002", "T003", "T004", "T005", "T006"
        );
        
        System.out.println("Transaction IDs:");
        transactionIds.forEach(System.out::println);
        
        System.out.println("\nGenerated Invoices using Constructor Reference:");
        System.out.println("===============================================");
        
        // Using constructor reference Invoice::new
        List<Invoice> invoices = transactionIds.stream()
                                              .map(Invoice::new)
                                              .collect(Collectors.toList());
        
        invoices.forEach(System.out::println);
        
        System.out.println("\nInvoice Summary:");
        System.out.println("===============");
        
        double totalAmount = invoices.stream()
                                   .mapToDouble(invoice -> invoice.amount)
                                   .sum();
        
        System.out.printf("Total Invoices: %d%n", invoices.size());
        System.out.printf("Total Amount: $%.2f%n", totalAmount);
        System.out.printf("Average Amount: $%.2f%n", totalAmount / invoices.size());
        
        // Alternative: Using method reference with a factory method
        System.out.println("\nUsing Factory Method Reference:");
        System.out.println("==============================");
        
        Function<String, Invoice> invoiceFactory = InvoiceObjectCreation::createDetailedInvoice;
        
        List<Invoice> detailedInvoices = transactionIds.stream()
                                                      .map(invoiceFactory)
                                                      .collect(Collectors.toList());
        
        detailedInvoices.forEach(System.out::println);
    }
    
    // Factory method for method reference
    public static Invoice createDetailedInvoice(String transactionId) {
        String[] customers = {"Alice Johnson", "Bob Smith", "Carol Davis", "David Wilson", "Emma Brown", "Frank Miller"};
        double[] amounts = {299.99, 149.50, 89.99, 199.99, 349.99, 79.99};
        
        int index = Math.abs(transactionId.hashCode()) % customers.length;
        
        return new Invoice(
            transactionId,
            amounts[index],
            customers[index],
            "2024-01-15"
        );
    }
}