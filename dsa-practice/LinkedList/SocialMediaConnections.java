import java.util.*;

public class SocialMediaConnections {
  static class User {
    int userId;
    String name;
    int age;
    List<Integer> friends;
    User next;

    User(int id, String name, int age) {
      this.userId = id;
      this.name = name;
      this.age = age;
      this.friends = new ArrayList<>();
    }
  }

  private User head;

  public void addUser(int id, String name, int age) {
    User u = new User(id, name, age);
    u.next = head;
    head = u;
  }

  public User findById(int id) {
    for (User cur = head; cur != null; cur = cur.next)
      if (cur.userId == id)
        return cur;
    return null;
  }

  public User findByName(String name) {
    for (User cur = head; cur != null; cur = cur.next)
      if (cur.name.equalsIgnoreCase(name))
        return cur;
    return null;
  }

  public boolean addFriend(int a, int b) {
    User ua = findById(a), ub = findById(b);
    if (ua == null || ub == null)
      return false;
    if (!ua.friends.contains(b))
      ua.friends.add(b);
    if (!ub.friends.contains(a))
      ub.friends.add(a);
    return true;
  }

  public boolean removeFriend(int a, int b) {
    User ua = findById(a), ub = findById(b);
    if (ua == null || ub == null)
      return false;
    ua.friends.remove((Integer) b);
    ub.friends.remove((Integer) a);
    return true;
  }

  public List<Integer> mutualFriends(int a, int b) {
    User ua = findById(a), ub = findById(b);
    if (ua == null || ub == null)
      return Collections.emptyList();
    List<Integer> res = new ArrayList<>();
    for (int fid : ua.friends)
      if (ub.friends.contains(fid))
        res.add(fid);
    return res;
  }

  public List<Integer> getFriends(int id) {
    User u = findById(id);
    return u == null ? Collections.emptyList() : new ArrayList<>(u.friends);
  }

  public int countFriends(int id) {
    return getFriends(id).size();
  }

  public void displayAllUsers() {
    for (User cur = head; cur != null; cur = cur.next)
      System.out.println(cur.userId + " | " + cur.name + " | age=" + cur.age + " | friends=" + cur.friends);
  }

  public static void main(String[] args) {
    SocialMediaConnections sm = new SocialMediaConnections();
    sm.addUser(1, "Alice", 25);
    sm.addUser(2, "Bob", 24);
    sm.addUser(3, "Carol", 23);
    sm.addFriend(1, 2);
    sm.addFriend(1, 3);
    sm.addFriend(2, 3);
    sm.displayAllUsers();
    System.out.println("Mutual 1 & 2: " + sm.mutualFriends(1, 2));
  }
}
