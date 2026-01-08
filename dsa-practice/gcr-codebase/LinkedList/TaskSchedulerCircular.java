import java.util.*;

public class TaskSchedulerCircular {
  static class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    Task(int id, String name, int p, String d) {
      this.id = id;
      this.name = name;
      this.priority = p;
      this.dueDate = d;
    }

    Task(int id, String name, int p, String d, Task next) {
      this(id, name, p, d);
      this.next = next;
    }
  }

  private Task tail; // tail.next is head
  private Task current;

  public void addAtBeginning(int id, String name, int p, String due) {
    Task n = new Task(id, name, p, due);
    if (tail == null) {
      tail = n;
      n.next = n;
      current = n;
    } else {
      n.next = tail.next;
      tail.next = n;
    }
  }

  public void addAtEnd(int id, String name, int p, String due) {
    addAtBeginning(id, name, p, due);
    tail = tail.next;
    if (current == null)
      current = tail;
  }

  public void addAtPosition(int id, String name, int p, String due, int pos) {
    if (tail == null || pos <= 0) {
      addAtBeginning(id, name, p, due);
      return;
    }
    Task cur = tail.next;
    int idx = 0;
    while (cur != tail && idx < pos - 1) {
      cur = cur.next;
      idx++;
    }
    Task n = new Task(id, name, p, due, nex(cur));
    n.next = cur.next;
    cur.next = n;
    if (cur == tail)
      tail = n;
  }

  private Task nex(Task t) {
    return t == null ? null : t.next;
  }

  public boolean removeById(int id) {
    if (tail == null)
      return false;
    Task prev = tail, cur = tail.next;
    do {
      if (cur.id == id) {
        if (cur == tail && cur.next == tail) {
          tail = null;
          current = null;
          return true;
        }
        prev.next = cur.next;
        if (cur == tail)
          tail = prev;
        if (current == cur)
          current = cur.next;
        return true;
      }
      prev = cur;
      cur = cur.next;
    } while (cur != tail.next);
    return false;
  }

  public Task viewCurrent() {
    return current;
  }

  public void moveToNext() {
    if (current != null)
      current = current.next;
  }

  public void displayAll() {
    if (tail == null)
      return;
    Task cur = tail.next;
    do {
      System.out.println(format(cur));
      cur = cur.next;
    } while (cur != tail.next);
  }

  public List<Task> searchByPriority(int p) {
    List<Task> res = new ArrayList<>();
    if (tail == null)
      return res;
    Task cur = tail.next;
    do {
      if (cur.priority == p)
        res.add(cur);
      cur = cur.next;
    } while (cur != tail.next);
    return res;
  }

  private String format(Task t) {
    return String.format("%d | %s | priority=%d | due=%s", t.id, t.name, t.priority, t.dueDate);
  }

  public static void main(String[] args) {
    TaskSchedulerCircular ts = new TaskSchedulerCircular();
    ts.addAtEnd(1, "Task A", 1, "2026-01-10");
    ts.addAtEnd(2, "Task B", 2, "2026-01-11");
    ts.addAtBeginning(0, "Urgent", 0, "2026-01-09");
    System.out.println("All tasks:");
    ts.displayAll();
    System.out.println("Current: " + ts.format(ts.viewCurrent()));
    ts.moveToNext();
    System.out.println("After move current: " + ts.format(ts.viewCurrent()));
  }
}
