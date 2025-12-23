import java.util.Scanner;

public class DeckOfCards {
  static String[] initDeck() {
    String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
    String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace" };
    String[] deck = new String[suits.length * ranks.length];
    int idx = 0;
    for (String r : ranks)
      for (String s : suits)
        deck[idx++] = r + " of " + s;
    return deck;
  }

  static void shuffleDeck(String[] deck) {
    int n = deck.length;
    for (int i = 0; i < n; i++) {
      int rand = i + (int) (Math.random() * (n - i));
      String tmp = deck[i];
      deck[i] = deck[rand];
      deck[rand] = tmp;
    }
  }

  static String[][] distribute(String[] deck, int numCards, int players) {
    if (numCards > deck.length)
      return null;
    if (numCards % players != 0)
      return null;
    int per = numCards / players;
    String[][] out = new String[players][per];
    int idx = 0;
    for (int p = 0; p < players; p++)
      for (int c = 0; c < per; c++)
        out[p][c] = deck[idx++];
    return out;
  }

  static void printPlayers(String[][] playersCards) {
    for (int i = 0; i < playersCards.length; i++) {
      System.out.print("Player " + (i + 1) + ": ");
      for (int j = 0; j < playersCards[i].length; j++) {
        System.out.print(playersCards[i][j]);
        if (j < playersCards[i].length - 1)
          System.out.print(", ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String[] deck = initDeck();
    System.out.println("Deck initialized with " + deck.length + " cards.");
    shuffleDeck(deck);
    System.out.println("Enter number of cards to deal:");
    int num = Integer.parseInt(sc.nextLine().trim());
    System.out.println("Enter number of players:");
    int players = Integer.parseInt(sc.nextLine().trim());
    String[][] dealt = distribute(deck, num, players);
    if (dealt == null) {
      System.out.println("Cannot distribute: check num cards or players.");
    } else {
      printPlayers(dealt);
    }
    
  }
}
