import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class InvoiceGenerator {

  static class Transaction {
    long id;
    double amount;

    Transaction(long id, double amount) {
      this.id = id;
      this.amount = amount;
    }

    public long getId() {
      return id;
    }

    public double getAmount() {
      return amount;
    }
  }

  static class Invoice {
    long invoiceId;
    double total;

    // constructor reference target
    Invoice(long invoiceId, double total) {
      this.invoiceId = invoiceId;
      this.total = total;
    }

    @Override
    public String toString() {
      return String.format("Invoice{id=%d, total=%.2f}", invoiceId, total);
    }
  }

  public static void main(String[] args) {
    List<Transaction> txs = Arrays.asList(
        new Transaction(2001, 150.0),
        new Transaction(2002, 275.5),
        new Transaction(2003, 49.99));

    // BiFunction constructor reference: (id, amount) -> new Invoice(id, amount)
    BiFunction<Long, Double, Invoice> invoiceCreator = Invoice::new;

    List<Invoice> invoices = txs.stream()
        .map(t -> invoiceCreator.apply(t.getId(), t.getAmount()))
        .collect(Collectors.toList());

    System.out.println("Generated invoices:");
    invoices.forEach(System.out::println);
  }
}