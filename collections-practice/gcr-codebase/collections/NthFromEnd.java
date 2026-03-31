import java.util.LinkedList;
import java.util.Iterator;

public class NthFromEnd {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        int n = 2;
        System.out.println(findFromEnd(list, n));
    }

    public static String findFromEnd(LinkedList<String> list, int n) {
        Iterator<String> lead = list.iterator();
        Iterator<String> follow = list.iterator();

        for (int i = 0; i < n; i++) {
            if (!lead.hasNext()) return null;
            lead.next();
        }

        while (lead.hasNext()) {
            lead.next();
            follow.next();
        }

        return follow.hasNext() ? follow.next() : null;
    }
}
