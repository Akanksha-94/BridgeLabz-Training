import java.util.*;
import java.util.stream.Collectors;

public class TransformingNamesForDisplay {
    public static void main(String[] args) {
        List<String> customerNames = Arrays.asList(
            "john doe",
            "alice smith",
            "bob johnson",
            "carol brown",
            "david wilson"
        );
        
        List<String> transformedNames = customerNames.stream()
            .map(String::toUpperCase)
            .sorted()
            .collect(Collectors.toList());
        
        System.out.println("Customer names (uppercase, alphabetically sorted):");
        transformedNames.forEach(System.out::println);
    }
}