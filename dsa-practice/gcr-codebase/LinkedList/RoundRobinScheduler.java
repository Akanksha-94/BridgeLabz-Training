import java.util.*;

public class RoundRobinScheduler {
  static class Process {
    int pid;
    int burst;
    int rem;
    int priority;
    Process next;

    Process(int pid, int burst, int pr) {
      this.pid = pid;
      this.burst = burst;
      this.rem = burst;
      this.priority = pr;
    }
  }

  private Process tail; // circular

  public void addProcess(int pid, int burst, int priority) {
    Process p = new Process(pid, burst, priority);
    if (tail == null) {
      tail = p;
      p.next = p;
    } else {
      p.next = tail.next;
      tail.next = p;
      tail = p;
    }
  }

  public boolean removeById(int pid) {
    if (tail == null)
      return false;
    Process prev = tail, cur = tail.next;
    do {
      if (cur.pid == pid) {
        if (cur == tail && cur.next == tail) {
          tail = null;
          return true;
        }
        prev.next = cur.next;
        if (cur == tail)
          tail = prev;
        return true;
      }
      prev = cur;
      cur = cur.next;
    } while (cur != tail.next);
    return false;
  }

  public void simulate(int timeQuantum) {
    if (tail == null)
      return;
    List<Integer> finishedOrder = new ArrayList<>();
    Map<Integer, Integer> completion = new HashMap<>();
    int time = 0;
    Process cur = tail.next;
    while (tail != null) {
      int exec = Math.min(timeQuantum, cur.rem);
      cur.rem -= exec;
      time += exec;
      if (cur.rem == 0) {
        finishedOrder.add(cur.pid);
        completion.put(cur.pid, time);
        Process prev = findPrev(cur);
        if (cur == tail && cur.next == tail) {
          tail = null;
          break;
        }
        prev.next = cur.next;
        if (cur == tail)
          tail = prev;
        cur = prev.next;
      } else
        cur = cur.next;
    }
    // compute waiting and turnaround
    System.out.println("Completion times: " + completion);
  }

  private Process findPrev(Process node) {
    if (tail == null)
      return null;
    Process cur = tail;
    while (cur.next != node)
      cur = cur.next;
    return cur;
  }

  public void display() {
    if (tail == null)
      return;
    Process cur = tail.next;
    do {
      System.out.println(cur.pid + " | burst=" + cur.burst + " | rem=" + cur.rem + " | pr=" + cur.priority);
      cur = cur.next;
    } while (cur != tail.next);
  }

  public static void main(String[] args) {
    RoundRobinScheduler rr = new RoundRobinScheduler();
    rr.addProcess(1, 10, 1);
    rr.addProcess(2, 4, 2);
    rr.addProcess(3, 6, 1);
    System.out.println("Before simulate:");
    rr.display();
    rr.simulate(3);
    System.out.println("After simulate (list may be empty):");
    rr.display();
  }
}
