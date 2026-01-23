import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static void main(String[] args) {
        Set<Integer> one = new HashSet<>();
        one.add(1);
        one.add(2);
        one.add(3);

        Set<Integer> two = new HashSet<>();
        two.add(3);
        two.add(4);
        two.add(5);

        Set<Integer> unionSet = buildUnion(one, two);
        Set<Integer> commonSet = buildIntersection(one, two);

        System.out.println("Union: " + unionSet);
        System.out.println("Intersection: " + commonSet);
    }

    public static Set<Integer> buildUnion(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        for (Integer item : b) {
            result.add(item);
        }
        return result;
    }

    public static Set<Integer> buildIntersection(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>();
        for (Integer item : a) {
            if (b.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }
}
