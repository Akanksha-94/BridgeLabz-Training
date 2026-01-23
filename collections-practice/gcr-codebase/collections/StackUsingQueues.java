import java.util.*;

/**
 * Implement a Stack Using Queues
 * Implement a stack data structure using two queues and support push, pop, and
 * top operations.
 * Example: Push 1, 2, 3 → Pop → Output: 3
 */
public class StackUsingQueues {
  static class Stack<T> {
    Queue<T> queue1 = new LinkedList<>();
    Queue<T> queue2 = new LinkedList<>();

    // Push element onto stack
    public void push(T element) {
      // Add element to queue2
      queue2.add(element);

      // Transfer all elements from queue1 to queue2
      while (!queue1.isEmpty()) {
        queue2.add(queue1.remove());
      }

      // Swap queue1 and queue2
      Queue<T> temp = queue1;
      queue1 = queue2;
      queue2 = temp;
    }

    // Pop element from stack
    public T pop() {
      if (queue1.isEmpty()) {
        throw new EmptyStackException();
      }
      return queue1.remove();
    }

    // Get top element without removing
    public T top() {
      if (queue1.isEmpty()) {
        throw new EmptyStackException();
      }
      return queue1.peek();
    }

    // Check if stack is empty
    public boolean isEmpty() {
      return queue1.isEmpty();
    }

    // Get size
    public int size() {
      return queue1.size();
    }

    // Print stack elements (top to bottom)
    public void print() {
      System.out.print("[");
      Queue<T> temp = new LinkedList<>(queue1);
      while (!temp.isEmpty()) {
        System.out.print(temp.remove());
        if (!temp.isEmpty())
          System.out.print(", ");
      }
      System.out.println("]");
    }
  }

  public static void main(String[] args) {
    System.out.println("=== Stack Using Queues ===\n");

    // Example 1: Basic operations
    Stack<Integer> stack1 = new Stack<>();
    System.out.println("Push 1, 2, 3");
    stack1.push(1);
    stack1.push(2);
    stack1.push(3);
    System.out.print("Stack: ");
    stack1.print();
    System.out.println("Top: " + stack1.top());

    System.out.println("\nPop: " + stack1.pop());
    System.out.print("Stack after pop: ");
    stack1.print();

    System.out.println("\nPop: " + stack1.pop());
    System.out.print("Stack after pop: ");
    stack1.print();

    // Example 2: More operations
    System.out.println("\n\nPush 10, 20, 30, 40, 50");
    Stack<Integer> stack2 = new Stack<>();
    stack2.push(10);
    stack2.push(20);
    stack2.push(30);
    stack2.push(40);
    stack2.push(50);
    System.out.print("Stack: ");
    stack2.print();

    System.out.println("Size: " + stack2.size());
    System.out.println("Top: " + stack2.top());

    System.out.println("\nPopping all elements:");
    while (!stack2.isEmpty()) {
      System.out.println("Pop: " + stack2.pop());
    }
    System.out.println("Stack is empty: " + stack2.isEmpty());

    // Example 3: String stack
    System.out.println("\n\nString Stack - Push A, B, C");
    Stack<String> stack3 = new Stack<>();
    stack3.push("A");
    stack3.push("B");
    stack3.push("C");
    System.out.print("Stack: ");
    stack3.print();
    System.out.println("Top: " + stack3.top());
    System.out.println("Pop: " + stack3.pop());
  }
}
