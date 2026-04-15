import java.io.*;
import java.util.*;

class Transaction {
  String type;
  int amount;
  int remainingLimit;

  Transaction(String t, int a, int r) {
    type = t;
    amount = a;
    remainingLimit = r;
  }
}

class CreditCard {
  String cardNumber;
  String cardHolderName;
  int creditLimit;
  int availableLimit;
  List<Transaction> transactions;

  CreditCard(String cn, String chn, int cl) {
    cardNumber = cn;
    cardHolderName = chn;
    creditLimit = cl;
    availableLimit = cl;
    transactions = new ArrayList<>();
  }
}

public class CreditCardSystem {
  private static Map<String, CreditCard> cards = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String line = br.readLine().trim();
      String[] parts = line.split(" ");
      String cmd = parts[0];
      if (cmd.equals("ISSUE")) {
        String cn = parts[1];
        String chn = parts[2];
        int cl = Integer.parseInt(parts[3]);
        issueCard(cn, chn, cl);
      } else if (cmd.equals("SPEND")) {
        String cn = parts[1];
        int amt = Integer.parseInt(parts[2]);
        spendAmount(cn, amt);
      } else if (cmd.equals("PAYMENT")) {
        String cn = parts[1];
        int amt = Integer.parseInt(parts[2]);
        makePayment(cn, amt);
      } else if (cmd.equals("HOLDER")) {
        String chn = parts[1];
        List<CreditCard> holders = getCardsByHolder(chn);
        if (holders.isEmpty()) {
          System.out.println("No cards found");
        } else {
          for (CreditCard c : holders) {
            System.out.println(c.cardNumber + " " + c.availableLimit);
          }
        }
      }
    }
  }

  private static int issueCard(String cardNumber, String cardHolderName, int creditLimit) {
    if (!cards.containsKey(cardNumber)) {
      cards.put(cardNumber, new CreditCard(cardNumber, cardHolderName, creditLimit));
      return 1;
    }
    return 0;
  }

  private static int spendAmount(String cardNumber, int amount) {
    if (cards.containsKey(cardNumber)) {
      CreditCard c = cards.get(cardNumber);
      if (amount <= c.availableLimit) {
        c.availableLimit -= amount;
        c.transactions.add(new Transaction("SPEND", amount, c.availableLimit));
        System.out.println("SPENT " + cardNumber + " " + c.availableLimit);
        return 1;
      }
    }
    System.out.println("Transaction declined");
    return 0;
  }

  private static int makePayment(String cardNumber, int amount) {
    if (cards.containsKey(cardNumber)) {
      CreditCard c = cards.get(cardNumber);
      c.availableLimit = Math.min(c.creditLimit, c.availableLimit + amount);
      c.transactions.add(new Transaction("PAYMENT", amount, c.availableLimit));
      System.out.println("PAYMENT DONE " + cardNumber + " " + c.availableLimit);
      return 1;
    }
    System.out.println("Card not found");
    return 0;
  }

  private static List<CreditCard> getCardsByHolder(String cardHolderName) {
    List<CreditCard> list = new ArrayList<>();
    for (CreditCard c : cards.values()) {
      if (c.cardHolderName.equals(cardHolderName)) {
        list.add(c);
      }
    }
    list.sort(Comparator.comparing(c -> c.cardNumber));
    return list;
  }
}