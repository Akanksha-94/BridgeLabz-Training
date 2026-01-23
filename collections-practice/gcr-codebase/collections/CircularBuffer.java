import java.util.*;

/**
 * Circular Buffer Simulation
 * Implement a circular buffer (fixed-size queue) using an array-based queue.
 * When full, overwrite the oldest element.
 * Example: Buffer size=3: Insert 1, 2, 3 → Insert 4 → Buffer: [2, 3, 4]
 */
public class CircularBuffer {
  static class CircularBufferQueue<T> {
    private T[] buffer;
    private int size;
    private int capacity;
    private int front;
    private int rear;

    @SuppressWarnings("unchecked")
    public CircularBufferQueue(int capacity) {
      this.capacity = capacity;
      this.buffer = (T[]) new Object[capacity];
      this.size = 0;
      this.front = 0;
      this.rear = -1;
    }

    // Add element to buffer
    public void add(T element) {
      if (size == capacity) {
        // Buffer is full, overwrite the oldest element
        front = (front + 1) % capacity;
        rear = (rear + 1) % capacity;
        buffer[rear] = element;
        System.out.println("Buffer full. Overwriting: " + element);
      } else {
        rear = (rear + 1) % capacity;
        buffer[rear] = element;
        size++;
      }
    }

    // Remove element from buffer
    public T remove() {
      if (size == 0) {
        throw new EmptyStackException();
      }
      T element = buffer[front];
      front = (front + 1) % capacity;
      size--;
      return element;
    }

    // Get front element without removing
    public T peek() {
      if (size == 0) {
        throw new EmptyStackException();
      }
      return buffer[front];
    }

    // Check if buffer is empty
    public boolean isEmpty() {
      return size == 0;
    }

    // Check if buffer is full
    public boolean isFull() {
      return size == capacity;
    }

    // Get current size
    public int getSize() {
      return size;
    }

    // Get capacity
    public int getCapacity() {
      return capacity;
    }

    // Print buffer elements
    public void print() {
      System.out.print("[");
      if (size > 0) {
        for (int i = 0; i < size; i++) {
          System.out.print(buffer[(front + i) % capacity]);
          if (i < size - 1)
            System.out.print(", ");
        }
      }
      System.out.println("]");
    }
  }

  public static void main(String[] args) {
    System.out.println("=== Circular Buffer Simulation ===\n");

    // Example 1: Basic overflow
    System.out.println("Buffer capacity: 3");
    CircularBufferQueue<Integer> buffer1 = new CircularBufferQueue<>(3);

    System.out.println("Insert 1, 2, 3:");
    buffer1.add(1);
    buffer1.add(2);
    buffer1.add(3);
    System.out.print("Buffer: ");
    buffer1.print();

    System.out.println("\nInsert 4 (overwrites oldest):");
    buffer1.add(4);
    System.out.print("Buffer: ");
    buffer1.print();

    System.out.println("\nInsert 5:");
    buffer1.add(5);
    System.out.print("Buffer: ");
    buffer1.print();

    System.out.println("\nInsert 6:");
    buffer1.add(6);
    System.out.print("Buffer: ");
    buffer1.print();

    // Example 2: Remove and add
    System.out.println("\n\nRemove: " + buffer1.remove());
    System.out.print("Buffer: ");
    buffer1.print();

    System.out.println("\nInsert 7:");
    buffer1.add(7);
    System.out.print("Buffer: ");
    buffer1.print();

    // Example 3: Larger buffer
    System.out.println("\n\nBuffer capacity: 5");
    CircularBufferQueue<String> buffer2 = new CircularBufferQueue<>(5);

    String[] elements = { "A", "B", "C", "D", "E", "F", "G" };
    for (String element : elements) {
      System.out.println("Insert: " + element);
      buffer2.add(element);
      System.out.print("Buffer: ");
      buffer2.print();
    }

    // Example 4: Integer buffer with size 4
    System.out.println("\n\nBuffer capacity: 4");
    CircularBufferQueue<Integer> buffer3 = new CircularBufferQueue<>(4);

    System.out.println("Insert 10, 20, 30, 40, 50, 60:");
    buffer3.add(10);
    buffer3.add(20);
    buffer3.add(30);
    buffer3.add(40);
    System.out.print("After inserting 10-40: ");
    buffer3.print();

    buffer3.add(50);
    System.out.print("After inserting 50: ");
    buffer3.print();

    buffer3.add(60);
    System.out.print("After inserting 60: ");
    buffer3.print();
  }
}
