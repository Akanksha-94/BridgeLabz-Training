class ParcelTracker {
    private static class Node {
        String stage;
        Node next;

        Node(String stage) {
            this.stage = stage;
        }
    }

    private Node head;

    public void addStage(String stage) {
        Node newNode = new Node(stage);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addCheckpointAfter(String existingStage, String newStage) {
        Node temp = head;
        while (temp != null && !temp.stage.equals(existingStage)) {
            temp = temp.next;
        }
        if (temp == null) {
            return;
        }
        Node newNode = new Node(newStage);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void trackParcel() {
        Node temp = head;
        if (temp == null) {
            System.out.println("Parcel missing");
            return;
        }
        while (temp != null) {
            System.out.println(temp.stage);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        ParcelTracker tracker = new ParcelTracker();
        tracker.addStage("Packed");
        tracker.addStage("Shipped");
        tracker.addStage("In Transit");
        tracker.addStage("Delivered");

        tracker.addCheckpointAfter("Shipped", "Custom Checkpoint");

        tracker.trackParcel();
    }
}