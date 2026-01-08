import java.util.*;

public class InventoryManagementSystem {
  static class Item {
    String name;
    String id;
    int qty;
    double price;
    Item next;

    Item(String name, String id, int q, double p) {
      this.name = name;
      this.id = id;
      this.qty = q;
      this.price = p;
    }
  }

  private Item head;

  public void addAtBeginning(String name, String id, int q, double p) {
    Item n = new Item(name, id, q, p);
    n.next = head;
    head = n;
  }

  public void addAtEnd(String name, String id, int q, double p) {
    Item n = new Item(name, id, q, p);
    if (head == null) {
      head = n;
      return;
    }
    Item cur = head;
    while (cur.next != null)
      cur = cur.next;
    cur.next = n;
  }

  public void addAtPosition(String name, String id, int q, double p, int pos) {
    if (pos <= 0) {
      addAtBeginning(name, id, q, p);
      return;
    }
    Item cur = head;
    int idx = 0;
    while (cur != null && idx < pos - 1) {
      cur = cur.next;
      idx++;
    }
    if (cur == null) {
      addAtEnd(name, id, q, p);
      return;
    }
    Item n = new Item(name, id, q, p);
    n.next = cur.next;
    cur.next = n;
  }

  public boolean removeById(String id) {
    Item cur = head, prev = null;
    while (cur != null) {
      if (cur.id.equals(id)) {
        if (prev == null)
          head = cur.next;
        else
          prev.next = cur.next;
        return true;
      }
      prev = cur;
      cur = cur.next;
    }
    return false;
  }

  public boolean updateQuantity(String id, int newQty) {
    for (Item cur = head; cur != null; cur = cur.next)
      if (cur.id.equals(id)) {
        cur.qty = newQty;
        return true;
      }
    return false;
  }

  public Item searchById(String id) {
    for (Item cur = head; cur != null; cur = cur.next)
      if (cur.id.equals(id))
        return cur;
    return null;
  }

  public List<Item> searchByName(String name) {
    List<Item> res = new ArrayList<>();
    for (Item cur = head; cur != null; cur = cur.next)
      if (cur.name.equalsIgnoreCase(name))
        res.add(cur);
    return res;
  }

  public double totalValue() {
    double s = 0;
    for (Item cur = head; cur != null; cur = cur.next)
      s += cur.qty * cur.price;
    return s;
  }

  // simple sort by name or price: convert to list, sort, rebuild
  public void sortByName(boolean asc) {
    List<Item> arr = toList();
    arr.sort((a, b) -> asc ? a.name.compareToIgnoreCase(b.name) : b.name.compareToIgnoreCase(a.name));
    rebuild(arr);
  }

  public void sortByPrice(boolean asc) {
    List<Item> arr = toList();
    arr.sort((a, b) -> Double.compare(asc ? a.price : b.price, asc ? b.price : a.price));
    rebuild(arr);
  }

  private List<Item> toList() {
    List<Item> a = new ArrayList<>();
    for (Item cur = head; cur != null; cur = cur.next)
      a.add(cur);
    return a;
  }

  private void rebuild(List<Item> arr) {
    head = null;
    for (Item it : arr) {
      it.next = null;
      if (head == null)
        head = it;
      else {
        Item cur = head;
        while (cur.next != null)
          cur = cur.next;
        cur.next = it;
      }
    }
  }

  public void display() {
    for (Item cur = head; cur != null; cur = cur.next)
      System.out.println(cur.id + " | " + cur.name + " | qty=" + cur.qty + " | price=" + cur.price);
  }

  public static void main(String[] args) {
    InventoryManagementSystem inv = new InventoryManagementSystem();
    inv.addAtEnd("Pen", "I001", 100, 0.5);
    inv.addAtEnd("Notebook", "I002", 50, 2.0);
    inv.addAtBeginning("Eraser", "I003", 200, 0.2);
    System.out.println("Inventory:");
    inv.display();
    System.out.println("Total value: " + inv.totalValue());
    inv.sortByName(true);
    System.out.println("After sort by name:");
    inv.display();
  }
}
