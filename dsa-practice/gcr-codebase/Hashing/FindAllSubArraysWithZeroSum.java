import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

// This class must be in a file named FindAllSubArraysWithZeroSum.java
public class FindAllSubArraysWithZeroSum {
    
    public static List<List<Integer>> findZeroSumSubarrays(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        HashMap<Integer, List<Integer>> sumIndexMap = new HashMap<>();
        int cumulativeSum = 0;
        
        sumIndexMap.put(0, new ArrayList<>());
        sumIndexMap.get(0).add(-1);
        
        for (int i = 0; i < arr.length; i++) {
            cumulativeSum += arr[i];
            
            if (sumIndexMap.containsKey(cumulativeSum)) {
                List<Integer> indices = sumIndexMap.get(cumulativeSum);
                for (int j : indices) {
                    List<Integer> subarray = new ArrayList<>();
                    for (int k = j + 1; k <= i; k++) {
                        subarray.add(arr[k]);
                    }
                    result.add(subarray);
                }
            }
            
            sumIndexMap.putIfAbsent(cumulativeSum, new ArrayList<>());
            sumIndexMap.get(cumulativeSum).add(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] arr = {15, -2, 2, -8, 1, 7, -10, 8};
        List<List<Integer>> subarrays = findZeroSumSubarrays(arr);
        
        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        if (subarrays.isEmpty()) {
            System.out.println("No subarrays with zero sum found!");
        } else {
            System.out.println("\n========== Subarrays with Zero Sum ==========");
            for (List<Integer> subarray : subarrays) {
                System.out.println(subarray);
            }
        }
    }
}