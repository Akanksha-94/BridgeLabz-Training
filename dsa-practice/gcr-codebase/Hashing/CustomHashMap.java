public class CustomHashMap {
    
    static class Entry {
        String key;
        String value;
        
        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
    
    static class Node {
        Entry entry;
        Node next;
        
        Node(Entry entry) {
            this.entry = entry;
        }
    }
    
    private static final int CAPACITY = 10;
    private Node[] table;
    
    public CustomHashMap() {
        table = new Node[CAPACITY];
    }
    
    private int hash(String key) {
        return Math.abs(key.hashCode()) % CAPACITY;
    }
    
    public void put(String key, String value) {
        if (key == null) {
            return;
        }
        
        int index = hash(key);
        Node node = table[index];
        
        while (node != null) {
            if (node.entry.key.equals(key)) {
                node.entry.value = value;
                return;
            }
            node = node.next;
        }
        
        Entry entry = new Entry(key, value);
        Node newNode = new Node(entry);
        newNode.next = table[index];
        table[index] = newNode;
    }
    
    public String get(String key) {
        if (key == null) {
            return null;
        }
        
        int index = hash(key);
        Node node = table[index];
        
        while (node != null) {
            if (node.entry.key.equals(key)) {
                return node.entry.value;
            }
            node = node.next;
        }
        
        return null;
    }
    
    public void remove(String key) {
        if (key == null) {
            return;
        }
        
        int index = hash(key);
        Node node = table[index];
        
        if (node != null && node.entry.key.equals(key)) {
            table[index] = node.next;
            return;
        }
        
        while (node != null) {
            if (node.next != null && node.next.entry.key.equals(key)) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }
    }
    
    public void displayAll() {
        System.out.println("\n========== Hash Map Contents ==========");
        for (int i = 0; i < CAPACITY; i++) {
            System.out.print("Bucket " + i + ": ");
            Node node = table[i];
            while (node != null) {
                System.out.print("[" + node.entry.key + " -> " + node.entry.value + "] ");
                node = node.next;
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        
        System.out.println("========== Custom Hash Map Operations ==========");
        
        System.out.println("\n--- Insertion ---");
        map.put("name", "Raj Kumar");
        map.put("age", "25");
        map.put("city", "Delhi");
        map.put("country", "India");
        map.put("email", "raj@example.com");
        System.out.println("5 entries inserted");
        
        map.displayAll();
        
        System.out.println("\n--- Retrieval ---");
        System.out.println("Get 'name': " + map.get("name"));
        System.out.println("Get 'city': " + map.get("city"));
        System.out.println("Get 'email': " + map.get("email"));
        System.out.println("Get 'phone': " + map.get("phone"));
        
        System.out.println("\n--- Deletion ---");
        map.remove("age");
        System.out.println("Removed 'age'");
        map.displayAll();
        
        System.out.println("\n--- Update ---");
        map.put("name", "Raj Kumar Singh");
        System.out.println("Updated 'name'");
        System.out.println("Get 'name': " + map.get("name"));
    }
}