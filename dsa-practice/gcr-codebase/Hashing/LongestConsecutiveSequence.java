import java.util.HashMap;

public class LongestConsecutiveSequence {
    
    public static int findLongestConsecutiveSequence(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        
        HashMap<Integer, Boolean> elementMap = new HashMap<>();
        
        for (int num : arr) {
            elementMap.put(num, true);
        }
        
        int maxLength = 1;
        
        for (int num : arr) {
            if (elementMap.get(num)) {
                int currentNum = num;
                int currentLength = 1;
                
                while (elementMap.containsKey(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }
                
                maxLength = Math.max(maxLength, currentLength);
            }
        }
        
        return maxLength;
    }
    
    public static void displayConsecutiveSequence(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        
        HashMap<Integer, Boolean> elementMap = new HashMap<>();
        for (int num : arr) {
            elementMap.put(num, true);
        }
        
        System.out.println("\n========== Consecutive Sequences ==========");
        for (int num : arr) {
            if (elementMap.get(num)) {
                int currentNum = num;
                System.out.print("Sequence starting at " + num + ": ");
                
                while (elementMap.containsKey(currentNum)) {
                    System.out.print(currentNum + " ");
                    elementMap.put(currentNum, false);
                    currentNum++;
                }
                System.out.println();
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr = {100, 4, 200, 1, 3, 2, 200, 104, 102, 103};
        
        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        int longestLength = findLongestConsecutiveSequence(arr);
        System.out.println("Length of Longest Consecutive Sequence: " + longestLength);
        
        displayConsecutiveSequence(arr);
    }
}