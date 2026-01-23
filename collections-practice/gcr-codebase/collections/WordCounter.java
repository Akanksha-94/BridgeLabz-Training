import java.util.*;

public class WordCounter {

    public static void main(String[] args) {

        List<String> items = new ArrayList<>();
        items.add("apple");
        items.add("banana");
        items.add("apple");
        items.add("orange");

        Map<String, Integer> counter = new HashMap<>();

        for (int i = 0; i < items.size(); i++) {
            String value = items.get(i);

            Integer oldCount = counter.get(value);

            if (oldCount == null) {
                counter.put(value, 1);
            } else {
                counter.put(value, oldCount + 1);
            }
        }

        System.out.println("Frequency Result: " + counter);
    }
}
