import java.util.HashMap;

public class TwoSumProblem {
    
    public static int[] findTwoSum(int[] arr, int targetSum) {
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
        int[] arr = {2, 7, 11, 15, 3, 6};
        int targetSum = 9;
        
        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("Target Sum: " + targetSum);
        
        int[] result = findTwoSum(arr, targetSum);
        
        if (result[0] == -1) {
            System.out.println("\nNo pair found!");
        } else {
            System.out.println("\n========== Two Sum Result ==========");
            System.out.println("Index 1: " + result[0] + ", Value: " + arr[result[0]]);
            System.out.println("Index 2: " + result[1] + ", Value: " + arr[result[1]]);
            System.out.println("Sum: " + arr[result[0]] + " + " + arr[result[1]] + " = " + targetSum);
        }
        
        System.out.println("\n========== Multiple Test Cases ==========");
        
        int[][] testArrays = {
            {2, 7, 11, 15, 3, 6},
            {10, 20, 30, 40, 50},
            {1, 2, 3, 4, 5}
        };
        
        int[] targetSums = {9, 50, 10};
        
        for (int i = 0; i < testArrays.length; i++) {
            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.print("Array: ");
            for (int num : testArrays[i]) {
                System.out.print(num + " ");
            }
            System.out.println();
            System.out.println("Target: " + targetSums[i]);
            
            int[] res = findTwoSum(testArrays[i], targetSums[i]);
            
            if (res[0] == -1) {
                System.out.println("Result: No pair found");
            } else {
                System.out.println("Result: Indices [" + res[0] + ", " + res[1] + "] -> " + 
                                 testArrays[i][res[0]] + " + " + testArrays[i][res[1]] + " = " + targetSums[i]);
            }
        }
    }
}