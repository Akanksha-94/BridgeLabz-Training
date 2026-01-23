import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/**
 * Word Frequency Counter
 * Read a text file and count the frequency of each word using a HashMap.
 * Ignore case and punctuation.
 * Example: Input: "Hello world, hello Java!" → Output: {hello=2, world=1,
 * java=1}
 */
public class WordFrequencyCounter {
  public static Map<String, Integer> countWordFrequency(String text) {
    Map<String, Integer> frequency = new HashMap<>();

    // Convert to lowercase and remove punctuation
    String cleanText = text.toLowerCase()
        .replaceAll("[^a-z0-9\\s]", " ");

    // Split into words
    String[] words = cleanText.split("\\s+");

    // Count frequency
    for (String word : words) {
      if (!word.isEmpty()) {
        frequency.put(word, frequency.getOrDefault(word, 0) + 1);
      }
    }

    return frequency;
  }

  public static Map<String, Integer> countWordFrequencyFromFile(String filePath) throws IOException {
    StringBuilder content = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        content.append(line).append(" ");
      }
    }
    return countWordFrequency(content.toString());
  }

  public static void displayFrequency(Map<String, Integer> frequency) {
    // Sort by frequency (descending)
    frequency.entrySet()
        .stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        .forEach(entry -> System.out.println(entry.getKey() + " = " + entry.getValue()));
  }

  public static void main(String[] args) {
    // Example 1: Direct string
    System.out.println("=== Word Frequency Counter ===\n");

    String text1 = "Hello world, hello Java!";
    System.out.println("Input: \"" + text1 + "\"");
    Map<String, Integer> frequency1 = countWordFrequency(text1);
    System.out.println("Output: " + frequency1);

    // Example 2: Longer text
    System.out.println("\n\nInput: \"Java is great. Java programming is fun. Programming in Java is awesome!\"");
    String text2 = "Java is great. Java programming is fun. Programming in Java is awesome!";
    Map<String, Integer> frequency2 = countWordFrequency(text2);
    System.out.println("Word frequencies (sorted by count):");
    displayFrequency(frequency2);

    // Example 3: Text with numbers and special characters
    System.out.println("\n\nInput: \"The quick brown fox jumps over the lazy dog. The fox is quick!\"");
    String text3 = "The quick brown fox jumps over the lazy dog. The fox is quick!";
    Map<String, Integer> frequency3 = countWordFrequency(text3);
    System.out.println("Word frequencies (sorted by count):");
    displayFrequency(frequency3);

    // Example 4: Sample with numbers
    System.out.println("\n\nInput: \"Java 8, Java 11, Java 17. Java is version controlled. 8 is old.\"");
    String text4 = "Java 8, Java 11, Java 17. Java is version controlled. 8 is old.";
    Map<String, Integer> frequency4 = countWordFrequency(text4);
    System.out.println("Word frequencies (sorted by count):");
    displayFrequency(frequency4);

    // Example 5: File reading (create a sample file)
    System.out.println("\n\n=== Reading from File ===");
    String filePath = "sample.txt";
    try {
      // Create a sample file
      PrintWriter writer = new PrintWriter(filePath);
      writer.println("Hello world, hello Java!");
      writer.println("Java is excellent. Java programming is fun.");
      writer.close();

      System.out.println("Reading from: " + filePath);
      Map<String, Integer> frequency5 = countWordFrequencyFromFile(filePath);
      System.out.println("Word frequencies (sorted by count):");
      displayFrequency(frequency5);

      // Clean up
      new File(filePath).delete();
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }
  }
}
