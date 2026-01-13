import java.util.*;

/**
 * TrafficManager - Roundabout Vehicle Flow Management
 * Demonstrates Circular Linked List + Queue
 * 
 * Story: A smart city roundabout allows vehicles to enter and exit dynamically.
 * Each vehicle is a node in a Circular Linked List.
 * A Queue manages vehicles waiting to enter.
 */

class Vehicle {
  private String vehicleId;
  private String type; // CAR, TRUCK, BIKE
  private int priority;
  private long entryTime;

  public Vehicle(String vehicleId, String type, int priority) {
    this.vehicleId = vehicleId;
    this.type = type;
    this.priority = priority;
    this.entryTime = System.currentTimeMillis();
  }

  public String getVehicleId() {
    return vehicleId;
  }

  public String getType() {
    return type;
  }

  public int getPriority() {
    return priority;
  }

  public long getEntryTime() {
    return entryTime;
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "id='" + vehicleId + '\'' +
        ", type='" + type + '\'' +
        ", priority=" + priority +
        '}';
  }
}

class CircularLinkedListNode {
  public Vehicle vehicle;
  public CircularLinkedListNode next;

  public CircularLinkedListNode(Vehicle vehicle) {
    this.vehicle = vehicle;
    this.next = null;
  }
}

class RoundaboutCircularLinkedList {
  private CircularLinkedListNode head;
  private int size;

  public RoundaboutCircularLinkedList() {
    this.head = null;
    this.size = 0;
  }

  public void addVehicle(Vehicle vehicle) {
    CircularLinkedListNode newNode = new CircularLinkedListNode(vehicle);

    if (head == null) {
      head = newNode;
      head.next = head; // Circular
    } else {
      CircularLinkedListNode current = head;
      while (current.next != head) {
        current = current.next;
      }
      current.next = newNode;
      newNode.next = head;
    }
    size++;
    System.out.println("✓ " + vehicle.getVehicleId() + " added to roundabout");
  }

  public Vehicle removeVehicle() {
    if (isEmpty()) {
      System.out.println("✗ Roundabout is empty - underflow");
      return null;
    }

    Vehicle removedVehicle = head.vehicle;

    if (head.next == head) {
      // Only one vehicle
      head = null;
    } else {
      // Find the last node
      CircularLinkedListNode current = head;
      while (current.next != head) {
        current = current.next;
      }
      current.next = head.next;
      head = head.next;
    }
    size--;
    System.out.println("✓ " + removedVehicle.getVehicleId() + " exited roundabout");
    return removedVehicle;
  }

  public void removeVehicleById(String vehicleId) {
    if (isEmpty()) {
      System.out.println("✗ Roundabout is empty");
      return;
    }

    // If head is the vehicle to remove
    if (head.vehicle.getVehicleId().equals(vehicleId)) {
      removeVehicle();
      return;
    }

    CircularLinkedListNode current = head;
    CircularLinkedListNode prev = head;

    do {
      if (current.vehicle.getVehicleId().equals(vehicleId)) {
        prev.next = current.next;
        size--;
        System.out.println("✓ " + vehicleId + " removed from roundabout");
        return;
      }
      prev = current;
      current = current.next;
    } while (current != head);

    System.out.println("✗ Vehicle not found in roundabout");
  }

  public void displayRoundabout() {
    if (isEmpty()) {
      System.out.println("Roundabout is empty");
      return;
    }

    System.out.println("\n═══ ROUNDABOUT STATUS ═══");
    CircularLinkedListNode current = head;
    int position = 1;

    do {
      System.out.println(position + ". " + current.vehicle);
      current = current.next;
      position++;
    } while (current != head);

    System.out.println("Total vehicles: " + size);
    System.out.println("═════════════════════════\n");
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public List<Vehicle> getAllVehicles() {
    List<Vehicle> vehicles = new ArrayList<>();
    if (isEmpty()) {
      return vehicles;
    }

    CircularLinkedListNode current = head;
    do {
      vehicles.add(current.vehicle);
      current = current.next;
    } while (current != head);

    return vehicles;
  }
}

class WaitingQueue {
  private Queue<Vehicle> queue;
  private int maxCapacity;

  public WaitingQueue(int maxCapacity) {
    this.queue = new LinkedList<>();
    this.maxCapacity = maxCapacity;
  }

  public void enqueue(Vehicle vehicle) {
    if (queue.size() >= maxCapacity) {
      System.out.println("✗ QUEUE OVERFLOW: Cannot add " + vehicle.getVehicleId() +
          " - queue is full");
      return;
    }

    queue.add(vehicle);
    System.out.println("✓ " + vehicle.getVehicleId() + " added to waiting queue");
  }

  public Vehicle dequeue() {
    if (queue.isEmpty()) {
      System.out.println("✗ QUEUE UNDERFLOW: No vehicles waiting");
      return null;
    }

    Vehicle vehicle = queue.poll();
    System.out.println("✓ " + vehicle.getVehicleId() + " dequeued from waiting queue");
    return vehicle;
  }

  public void displayQueue() {
    if (queue.isEmpty()) {
      System.out.println("Waiting queue is empty");
      return;
    }

    System.out.println("\n═══ WAITING QUEUE STATUS ═══");
    int position = 1;
    for (Vehicle v : queue) {
      System.out.println(position + ". " + v);
      position++;
    }
    System.out.println("Queue size: " + queue.size() + "/" + maxCapacity);
    System.out.println("═══════════════════════════\n");
  }

  public int getSize() {
    return queue.size();
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public boolean isFull() {
    return queue.size() >= maxCapacity;
  }
}

public class TrafficManager {
  private RoundaboutCircularLinkedList roundabout;
  private WaitingQueue waitingQueue;
  private int vehicleCounter;

  public TrafficManager(int queueCapacity) {
    this.roundabout = new RoundaboutCircularLinkedList();
    this.waitingQueue = new WaitingQueue(queueCapacity);
    this.vehicleCounter = 0;
  }

  public void allowVehicleEnter() {
    if (waitingQueue.isEmpty()) {
      System.out.println("No vehicles in queue to enter roundabout");
      return;
    }

    Vehicle vehicle = waitingQueue.dequeue();
    roundabout.addVehicle(vehicle);
  }

  public void vehicleArrives(String type) {
    vehicleCounter++;
    Vehicle vehicle = new Vehicle("V" + vehicleCounter, type, vehicleCounter % 5);
    waitingQueue.enqueue(vehicle);
  }

  public void vehicleExits() {
    roundabout.removeVehicle();
  }

  public void removeSpecificVehicle(String vehicleId) {
    roundabout.removeVehicleById(vehicleId);
  }

  public void printStatus() {
    System.out.println("\n╔════════════════════════════════════════╗");
    System.out.println("║      SMART CITY ROUNDABOUT STATUS      ║");
    System.out.println("╚════════════════════════════════════════╝");
    roundabout.displayRoundabout();
    waitingQueue.displayQueue();
  }

  public static void main(String[] args) {
    TrafficManager manager = new TrafficManager(5);

    System.out.println("╔════════════════════════════════════════╗");
    System.out.println("║    TRAFFIC MANAGER - ROUNDABOUT FLOW   ║");
    System.out.println("╚════════════════════════════════════════╝\n");

    // Simulate vehicles arriving
    System.out.println("--- PHASE 1: Vehicles Arriving ---");
    manager.vehicleArrives("CAR");
    manager.vehicleArrives("TRUCK");
    manager.vehicleArrives("BIKE");
    manager.vehicleArrives("CAR");

    manager.printStatus();

    // Allow vehicles to enter roundabout
    System.out.println("--- PHASE 2: Vehicles Entering Roundabout ---");
    manager.allowVehicleEnter();
    manager.allowVehicleEnter();
    manager.allowVehicleEnter();

    manager.printStatus();

    // More vehicles arriving
    System.out.println("--- PHASE 3: More Vehicles Arriving ---");
    manager.vehicleArrives("TRUCK");
    manager.vehicleArrives("BIKE");

    manager.printStatus();

    // Test queue overflow
    System.out.println("--- PHASE 4: Testing Queue Overflow ---");
    manager.vehicleArrives("CAR");
    manager.vehicleArrives("CAR"); // This should overflow

    manager.printStatus();

    // Vehicle exits roundabout
    System.out.println("--- PHASE 5: Vehicle Exits Roundabout ---");
    manager.vehicleExits();

    manager.printStatus();

    // Remove specific vehicle
    System.out.println("--- PHASE 6: Remove Specific Vehicle ---");
    manager.removeSpecificVehicle("V1");

    manager.printStatus();

    // Continue operations
    System.out.println("--- PHASE 7: Continue Operations ---");
    manager.allowVehicleEnter();
    manager.allowVehicleEnter();

    manager.printStatus();

    System.out.println("╔════════════════════════════════════════╗");
    System.out.println("║         SIMULATION COMPLETED           ║");
    System.out.println("╚════════════════════════════════════════╝");
  }
}
