import java.util.*;
import java.util.stream.Collectors;
public class WordFrequencyCounter {
    public static void main(String[] args) {
        String paragraph="Ram Ram bhai sabhi ko this side hodophile express";
        Map<String,Long> wordCount=Arrays.stream(paragraph.toLowerCase().split("\\s+"))
        .collect(Collectors.groupingBy(
            word->word,
            Collectors.counting()
        ));
        System.out.println(wordCount);
    }
}
