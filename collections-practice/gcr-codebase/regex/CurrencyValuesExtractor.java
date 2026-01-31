import java.util.regex.*;

public class CurrencyValuesExtractor {
    public static void extract(String text) {
        Pattern p = Pattern.compile("\\$?\\d+\\.\\d{2}");
        Matcher m = p.matcher(text);
        boolean first = true;
        while (m.find()) {
            if (!first) System.out.print(", ");
            System.out.print(m.group());
            first = false;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String text = "The price is $45.99, and the discount is 10.50.";
        extract(text);
    }
}