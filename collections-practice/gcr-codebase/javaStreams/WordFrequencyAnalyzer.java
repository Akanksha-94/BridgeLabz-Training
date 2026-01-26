import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class WordFrequencyAnalyzer {

  public static void analyzeWordFrequency(String filename) {
    File file = new File(filename);

    if (!file.exists()) {
      System.out.println("File does not exist: " + filename);
      return;
    }

    Map<String, Integer> wordCount = new HashMap<>();
    int totalWords = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;

      while ((line = br.readLine()) != null) {
        String[] words = line.toLowerCase().split("[\\W]+");

        for (String word : words) {
          if (!word.isEmpty()) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            totalWords++;
          }
        }
      }

      System.out.println("Total words in file: " + totalWords);
      System.out.println("Unique words: " + wordCount.size());
      System.out.println("\nTop 5 Most Frequent Words:");
      System.out.println("================================");

      List<Map.Entry<String, Integer>> entryList = new ArrayList<>(wordCount.entrySet());
      Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
        @Override
        public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
          return e2.getValue().compareTo(e1.getValue());
        }
      });

      int count = 0;
      for (Map.Entry<String, Integer> entry : entryList) {
        if (count >= 5)
          break;
        System.out.println((count + 1) + ". \"" + entry.getKey() + "\" - " + entry.getValue() + " times");
        count++;
      }

    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    analyzeWordFrequency("input.txt");
  }
}
