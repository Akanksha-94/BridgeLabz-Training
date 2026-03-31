import java.util.HashMap;

public class CheckPairWithGivenSum {
    
    public static boolean findPair(int[] arr, int targetSum) {
        HashMap<Integer, Boolean> visited = new HashMap<>();
        
        for (int num : arr) {
            int complement = targetSum - num;
            if (visited.containsKey(complement)) {
                return true;
            }
            visited.put(num, true);
        }
        
        return false;
    }
    
    public static int[] findPairIndices(int[] arr, int targetSum) {
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int complement = targetSum - arr[i];
            if (indexMap.containsKey(complement)) {
                return new int[]{indexMap.get(complement), i};
            }
            indexMap.put(arr[i], i);
        }
        
        return new int[]{-1, -1};
    }
    
    public static void main(String[] args) {
        int[] arr = {3, 5, 9, 2, 8, 10};
        int targetSum = 17;
        
        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Target Sum: " + targetSum);
        
        boolean found = findPair(arr, targetSum);
        
        if (found) {
            int[] indices = findPairIndices(arr, targetSum);
            System.out.println("\nPair found!");
            System.out.println("Elements: " + arr[indices[0]] + " + " + arr[indices[1]] + " = " + targetSum);
            System.out.println("Indices: [" + indices[0] + ", " + indices[1] + "]");
        } else {
            System.out.println("\nNo pair found with sum " + targetSum);
        }
    }
}