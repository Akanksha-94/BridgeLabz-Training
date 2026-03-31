import java.util.*;

/**
 * Reverse a Queue
 * Reverse the elements of a queue using only queue operations (e.g., add,
 * remove, isEmpty).
 * Example: Input: [10, 20, 30] → Output: [30, 20, 10]
 */
public class ReverseQueue {
  public static Queue<Integer> reverseQueue(Queue<Integer> queue) {
    Stack<Integer> stack = new Stack<>();

    // Transfer all elements from queue to stack
    while (!queue.isEmpty()) {
      stack.push(queue.remove());
    }

    // Transfer all elements from stack back to queue
    while (!stack.isEmpty()) {
      queue.add(stack.pop());
    }

    return queue;
  }

  public static void printQueue(Queue<Integer> queue) {
    System.out.print("[");
    Queue<Integer> temp = new LinkedList<>(queue);
    while (!temp.isEmpty()) {
      System.out.print(temp.remove());
      if (!temp.isEmpty())
        System.out.print(", ");
    }
    System.out.println("]");
  }

  public static void main(String[] args) {
    // Example 1
    Queue<Integer> queue1 = new LinkedList<>(Arrays.asList(10, 20, 30));
    System.out.println("Original Queue:");
    printQueue(queue1);
    reverseQueue(queue1);
    System.out.println("Reversed Queue:");
    printQueue(queue1);

    // Example 2
    Queue<Integer> queue2 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
    System.out.println("\nOriginal Queue:");
    printQueue(queue2);
    reverseQueue(queue2);
    System.out.println("Reversed Queue:");
    printQueue(queue2);

    // Example 3
    Queue<Integer> queue3 = new LinkedList<>(Arrays.asList(100));
    System.out.println("\nOriginal Queue:");
    printQueue(queue3);
    reverseQueue(queue3);
    System.out.println("Reversed Queue:");
    printQueue(queue3);
  }
}
