public class ParagraphAnalyzer {
    //Count number of words
    public static int countWords(String paragraph) {
        if (paragraph == null || paragraph.trim().isEmpty()) {
            return 0;
        }
        return paragraph.trim().split("\\s+").length;
    }
    //Find longest word
    public static String findLongestWord(String paragraph) {
        if (paragraph == null || paragraph.trim().isEmpty()) {
            return "";
        }
        String[] words = paragraph.trim().split("\\s+");
        String longest = "";
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }
    //Replace word case-insensitive
    public static String replaceWord(String paragraph, String oldWord, String newWord) {
        if (paragraph == null || paragraph.trim().isEmpty()) {
            return "";
        }
        return paragraph.replaceAll("(?i)\\b" + oldWord + "\\b", newWord);
    }
    public static void main(String[] args) {
        String paragraph = "Java is powerful.Java is easy to learn and JAVA is popular.";
        System.out.println("Paragraph:");
        System.out.println(paragraph);
        int wordCount = countWords(paragraph);
        System.out.println("\nWordCount:" + wordCount);
        String longestWord = findLongestWord(paragraph);
        System.out.println("LongestWord:" + longestWord);
        String replacedParagraph = replaceWord(paragraph, "java", "Python");
        System.out.println("\nAfterReplacement:");
        System.out.println(replacedParagraph);
    }
}