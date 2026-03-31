/**
 * Linear Search Problem 2: Search for a Specific Word in a List of Sentences
 * 
 * Problem:
 * You are given an array of sentences (strings). Write a program that performs
 * Linear Search to find the first sentence containing a specific word.
 * If the word is found, return the sentence. If no sentence contains the word,
 * return "Not Found".
 * 
 * Approach:
 * 1. Iterate through the list of sentences.
 * 2. For each sentence, check if it contains the specific word.
 * 3. If the word is found, return the current sentence.
 * 4. If no sentence contains the word, return "Not Found".
 */

public class SearchWordInSentences {

  /**
   * Searches for the first sentence containing a specific word
   * 
   * @param sentences array of sentences
   * @param word      the word to search for
   * @return the first sentence containing the word, or "Not Found"
   */
  public static String findSentenceWithWord(String[] sentences, String word) {
    if (sentences == null || sentences.length == 0 || word == null) {
      return "Not Found";
    }

    String lowerWord = word.toLowerCase();

    for (String sentence : sentences) {
      if (sentence != null) {
        String lowerSentence = sentence.toLowerCase();
        // Check if sentence contains the word as a whole word
        if (containsWord(lowerSentence, lowerWord)) {
          return sentence;
        }
      }
    }

    return "Not Found";
  }

  /**
   * Checks if a sentence contains a specific word (as a whole word, not
   * substring)
   * 
   * @param sentence the sentence to check
   * @param word     the word to search for
   * @return true if the word is found as a complete word, false otherwise
   */
  private static boolean containsWord(String sentence, String word) {
    // Split sentence into words, removing punctuation
    String[] words = sentence.split("\\s+|[,;.!?:'\"-]+");

    for (String w : words) {
      if (w.equalsIgnoreCase(word)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Searches and returns the index and sentence containing the word
   * 
   * @param sentences array of sentences
   * @param word      the word to search for
   * @return an array with [index, sentence] or null if not found
   */
  public static Object[] findSentenceWithIndex(String[] sentences, String word) {
    if (sentences == null || sentences.length == 0 || word == null) {
      return null;
    }

    String lowerWord = word.toLowerCase();

    for (int i = 0; i < sentences.length; i++) {
      if (sentences[i] != null) {
        String lowerSentence = sentences[i].toLowerCase();
        if (containsWord(lowerSentence, lowerWord)) {
          return new Object[] { i, sentences[i] };
        }
      }
    }

    return null;
  }

  /**
   * Finds all sentences containing a specific word
   * 
   * @param sentences array of sentences
   * @param word      the word to search for
   * @return array of sentences containing the word
   */
  public static String[] findAllSentencesWithWord(String[] sentences, String word) {
    if (sentences == null || sentences.length == 0 || word == null) {
      return new String[0];
    }

    String lowerWord = word.toLowerCase();
    java.util.List<String> results = new java.util.ArrayList<>();

    for (String sentence : sentences) {
      if (sentence != null) {
        String lowerSentence = sentence.toLowerCase();
        if (containsWord(lowerSentence, lowerWord)) {
          results.add(sentence);
        }
      }
    }

    return results.toArray(new String[0]);
  }

  /**
   * Displays search results
   * 
   * @param sentences array of sentences
   * @param word      the word to search for
   */
  public static void searchAndDisplay(String[] sentences, String word) {
    System.out.println("Searching for word: \"" + word + "\"\n");

    Object[] result = findSentenceWithIndex(sentences, word);

    if (result != null) {
      int index = (int) result[0];
      String sentence = (String) result[1];
      System.out.println("✓ Found at sentence #" + (index + 1) + ":");
      System.out.println("  \"" + sentence + "\"");
    } else {
      System.out.println("✗ Word not found in any sentence");
    }

    System.out.println();
  }

  /**
   * Main method to test the search functionality
   */
  public static void main(String[] args) {
    System.out.println("╔════════════════════════════════════════════════════════════╗");
    System.out.println("║  Linear Search: Find Word in Sentences                     ║");
    System.out.println("╚════════════════════════════════════════════════════════════╝\n");

    String[] sentences = {
        "Java is a powerful programming language.",
        "Python is known for its simplicity and readability.",
        "C++ is used for system programming and game development.",
        "JavaScript runs in web browsers and servers.",
        "The Java Virtual Machine executes bytecode efficiently."
    };

    System.out.println("Sentences in the array:");
    for (int i = 0; i < sentences.length; i++) {
      System.out.println((i + 1) + ". " + sentences[i]);
    }
    System.out.println();
    System.out.println("═".repeat(60));
    System.out.println();

    // Test case 1: Search for "Java"
    searchAndDisplay(sentences, "Java");

    // Test case 2: Search for "Python"
    searchAndDisplay(sentences, "Python");

    // Test case 3: Search for "language"
    searchAndDisplay(sentences, "language");

    // Test case 4: Search for a word that doesn't exist
    searchAndDisplay(sentences, "Ruby");

    // Test case 5: Case-insensitive search
    searchAndDisplay(sentences, "JAVASCRIPT");

    // Test case 6: Find all sentences with a word
    System.out.println("═".repeat(60));
    System.out.println("\nFinding ALL sentences with \"programming\":\n");
    String[] allMatches = findAllSentencesWithWord(sentences, "programming");
    if (allMatches.length > 0) {
      System.out.println("✓ Found " + allMatches.length + " sentence(s):");
      for (String match : allMatches) {
        System.out.println("  - " + match);
      }
    } else {
      System.out.println("✗ No sentences found");
    }

    // Performance analysis
    System.out.println("\n" + "═".repeat(60));
    System.out.println("\nPerformance Analysis:\n");
    System.out.println("Time Complexity: O(n * m)");
    System.out.println("  where n = number of sentences, m = average sentence length");
    System.out.println();
    System.out.println("Space Complexity: O(1) - for single search");
    System.out.println("                  O(k) - for finding all matches (k = results)");
    System.out.println();
    System.out.println("Best Case: O(m) - word found in first sentence");
    System.out.println("Worst Case: O(n * m) - word not found or at last position");
    System.out.println("Average Case: O(n * m / 2) ≈ O(n * m)");
  }
}
