import java.util.HashSet;
import java.util.Set;

public class SetEqualityCheck {
    public static void main(String[] args) {
        Set<Integer> first = new HashSet<>();
        first.add(1);
        first.add(2);
        first.add(3);

        Set<Integer> second = new HashSet<>();
        second.add(3);
        second.add(2);
        second.add(1);

        System.out.println(areSame(first, second));
    }

    public static boolean areSame(Set<Integer> a, Set<Integer> b) {
        if (a.size() != b.size()) return false;

        for (Integer value : a) {
            if (!b.contains(value)) return false;
        }
        return true;
    }
}
