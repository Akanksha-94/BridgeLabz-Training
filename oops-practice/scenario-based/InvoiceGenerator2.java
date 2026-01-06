class InvalidInvoiceFormatException extends Exception {
    public InvalidInvoiceFormatException(String message) {
        super(message);
    }
}
/*
 * InvoiceGenerator
 * Parses invoice input string and calculates total amount.
 */
public class InvoiceGenerator2 {
    // Parse invoice input
    public static String[] parseInvoice(String input) throws InvalidInvoiceFormatException {
        if (input == null || input.isEmpty()) {
            throw new InvalidInvoiceFormatException("Invoice input cannot be empty.");
        }
        String[] tasks = input.split(",");
        for (String task : tasks) {
            if (!task.contains("-")) {
                throw new InvalidInvoiceFormatException(
                        "Invalid format detected. Missing '-' in: " + task
                );
            }
            String[] parts = task.split("-");
            if (parts.length != 2 || !parts[1].matches(".*\\d+.*")) {
                throw new InvalidInvoiceFormatException(
                        "Invalid amount in task: " + task
                );
            }
        }
        return tasks;
    }
    // Calculate total amount
    public static int getTotalAmount(String[] tasks) {
        int total = 0;
        for (String task : tasks) {
            String amountPart = task.split("-")[1];
            String amount = amountPart.replaceAll("[^0-9]", "");
            total += Integer.parseInt(amount);
        }
        return total;
    }
    // Main method
    public static void main(String[] args) {
        String invoiceInput = "Logo Design - 3000 INR, Web Page - 4500 INR";
        System.out.println("===== Invoice Generator =====");
        try {
            String[] tasks = parseInvoice(invoiceInput);
            for (String task : tasks) {
                System.out.println(task.trim());
            }
            System.out.println("--------------------------------");
            System.out.println("Total Invoice Amount: ₹" + getTotalAmount(tasks));
        } catch (InvalidInvoiceFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}