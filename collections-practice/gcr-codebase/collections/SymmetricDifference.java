import java.util.HashSet;
import java.util.Set;

public class SymmetricDifference {
    public static void main(String[] args) {
        Set<Integer> left = new HashSet<>();
        left.add(1);
        left.add(2);
        left.add(3);

        Set<Integer> right = new HashSet<>();
        right.add(3);
        right.add(4);
        right.add(5);

        Set<Integer> result = findSymmetric(left, right);
        System.out.println(result);
    }

    public static Set<Integer> findSymmetric(Set<Integer> a, Set<Integer> b) {
        Set<Integer> output = new HashSet<>();

        for (Integer x : a) {
            if (!b.contains(x)) {
                output.add(x);
            }
        }

        for (Integer y : b) {
            if (!a.contains(y)) {
                output.add(y);
            }
        }

        return output;
    }
}
