import java.util.LinkedList;
import java.util.Queue;

class TrafficManager {

    private static class Vehicle {
        String id;
        Vehicle next;

        Vehicle(String id) {
            this.id = id;
        }
    }

    private Vehicle tail;
    private Queue<String> waitingQueue;
    private int queueCapacity;

    public TrafficManager(int queueCapacity) {
        this.queueCapacity = queueCapacity;
        this.waitingQueue = new LinkedList<>();
    }

    public void addToQueue(String vehicleId) {
        if (waitingQueue.size() == queueCapacity) {
            System.out.println("Queue Overflow: Vehicle " + vehicleId + " cannot enter");
            return;
        }
        waitingQueue.offer(vehicleId);
    }

    public void enterRoundabout() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Queue Underflow: No vehicles waiting");
            return;
        }
        String vehicleId = waitingQueue.poll();
        Vehicle newVehicle = new Vehicle(vehicleId);

        if (tail == null) {
            tail = newVehicle;
            tail.next = tail;
        } else {
            newVehicle.next = tail.next;
            tail.next = newVehicle;
            tail = newVehicle;
        }
    }

    public void exitRoundabout(String vehicleId) {
        if (tail == null) return;

        Vehicle curr = tail.next;
        Vehicle prev = tail;

        do {
            if (curr.id.equals(vehicleId)) {
                if (curr == tail && curr.next == tail) {
                    tail = null;
                } else {
                    prev.next = curr.next;
                    if (curr == tail) {
                        tail = prev;
                    }
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != tail.next);
    }

    public void printRoundabout() {
        if (tail == null) {
            System.out.println("Roundabout Empty");
            return;
        }

        Vehicle temp = tail.next;
        do {
            System.out.print(temp.id + " ");
            temp = temp.next;
        } while (temp != tail.next);
        System.out.println();
    }

    public static void main(String[] args) {
        TrafficManager manager = new TrafficManager(3);

        manager.addToQueue("Car1");
        manager.addToQueue("Car2");
        manager.addToQueue("Car3");
        manager.addToQueue("Car4");

        manager.enterRoundabout();
        manager.enterRoundabout();
        manager.enterRoundabout();
        manager.enterRoundabout();

        manager.printRoundabout();

        manager.exitRoundabout("Car2");
        manager.printRoundabout();

        manager.addToQueue("Car5");
        manager.enterRoundabout();
        manager.printRoundabout();
    }
}