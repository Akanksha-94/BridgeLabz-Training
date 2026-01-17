/**
 * Challenge Problem: Compare Linear Search and Binary Search
 * 
 * Problem:
 * You are given a list of integers. Write a program that:
 * 1. Uses Linear Search to find the first missing positive integer in the list
 * 2. Uses Binary Search to find the index of a given target number
 * 
 * Approach:
 * Linear Search for the first missing positive integer:
 * 1. Iterate through the list and mark each number in the list as visited
 * 2. Traverse the array again to find the first positive integer that is not marked
 * 
 * Binary Search for the target index:
 * 1. After sorting the array, perform binary search to find the index of the given target number
 * 2. Return the index if found, otherwise return -1
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ChallengeLinearBinarySearch {
    
    // ========================= Part 1: Linear Search =========================
    
    /**
     * Finds the first missing positive integer using linear search
     * @param arr the array to search
     * @return the first missing positive integer
     */
    public static int findFirstMissingPositive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 1;
        }
        
        // Create a set to store all numbers in the array
        Set<Integer> numbers = new HashSet<>();
        for (int num : arr) {
            if (num > 0) {
                numbers.add(num);
            }
        }
        
        // Find the first missing positive integer
        int missing = 1;
        while (numbers.contains(missing)) {
            missing++;
        }
        
        return missing;
    }
    
    /**
     * Finds the first missing positive integer with detailed information
     * @param arr the array to search
     * @return a string with detailed information
     */
    public static String findFirstMissingPositiveDetailed(int[] arr) {
        if (arr == null || arr.length == 0) {
            return "Array is empty. First missing positive: 1";
        }
        
        Set<Integer> numbers = new HashSet<>();
        int maxNum = 0;
        for (int num : arr) {
            if (num > 0) {
                numbers.add(num);
                maxNum = Math.max(maxNum, num);
            }
        }
        
        for (int i = 1; i <= maxNum + 1; i++) {
            if (!numbers.contains(i)) {
                return "First missing positive integer: " + i + 
                       " (Range checked: 1 to " + (maxNum + 1) + ")";
            }
        }
        
        return "First missing positive: 1";
    }
    
    // ========================= Part 2: Binary Search =========================
    
    /**
     * Performs binary search on a sorted array to find target index
     * @param arr the sorted array to search (will be sorted if not already)
     * @param target the value to search for
     * @return the index of target if found, -1 otherwise
     */
    public static int binarySearch(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        
        // Create a copy and sort it
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        
        int left = 0;
        int right = sortedArr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (sortedArr[mid] == target) {
                return mid;
            } else if (sortedArr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return -1;
    }
    
    /**
     * Performs binary search with detailed information
     * @param arr the array to search
     * @param target the value to search for
     * @return detailed search information
     */
    public static String binarySearchDetailed(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return "Array is empty";
        }
        
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        
        int left = 0;
        int right = sortedArr.length - 1;
        int iterations = 0;
        
        while (left <= right) {
            iterations++;
            int mid = left + (right - left) / 2;
            
            if (sortedArr[mid] == target) {
                return "Target " + target + " found at index: " + mid + 
                       " (Binary search iterations: " + iterations + ")";
            } else if (sortedArr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return "Target " + target + " not found (Iterations: " + iterations + ")";
    }
    
    // ========================= Utility Methods =========================
    
    /**
     * Displays linear search results
     * @param arr the array to search
     */
    public static void displayLinearSearchResult(int[] arr) {
        System.out.print("Array: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        int missing = findFirstMissingPositive(arr);
        System.out.println("✓ First missing positive integer: " + missing);
        System.out.println();
    }
    
    /**
     * Displays binary search results
     * @param arr the array to search
     * @param target the value to search for
     */
    public static void displayBinarySearchResult(int[] arr, int target) {
        System.out.print("Original array: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        System.out.println("Target: " + target);
        
        int[] sortedArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedArr);
        System.out.print("Sorted array: [");
        for (int i = 0; i < sortedArr.length; i++) {
            System.out.print(sortedArr[i]);
            if (i < sortedArr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        int index = binarySearch(arr, target);
        if (index != -1) {
            System.out.println("✓ Target found at index: " + index);
        } else {
            System.out.println("✗ Target not found");
        }
        System.out.println();
    }
    
    /**
     * Compares linear and binary search performance
     * @param arr the array to search
     * @param target the target value
     */
    public static void comparePerformance(int[] arr, int target) {
        // Linear search performance
        long linearStart = System.nanoTime();
        int linearResult = binarySearch(arr, target);
        long linearTime = System.nanoTime() - linearStart;
        
        // Binary search performance
        long binaryStart = System.nanoTime();
        int binaryResult = binarySearch(arr, target);
        long binaryTime = System.nanoTime() - binaryStart;
        
        System.out.println("\nPerformance Comparison (for target " + target + "):");
        System.out.println("Linear approach time: " + linearTime + " ns");
        System.out.println("Binary approach time: " + binaryTime + " ns");
        System.out.println("Time difference: " + Math.abs(linearTime - binaryTime) + " ns");
    }
    
    /**
     * Main method to run the challenge
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  Challenge: Linear Search & Binary Search                  ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // ==================== Part 1: Linear Search ====================
        System.out.println("╔─── PART 1: Linear Search - First Missing Positive ───╗\n");
        
        // Test case 1
        int[] arr1 = {3, 4, -1, 1};
        System.out.println("Test Case 1:");
        displayLinearSearchResult(arr1);
        
        // Test case 2
        int[] arr2 = {1, 2, 0};
        System.out.println("Test Case 2:");
        displayLinearSearchResult(arr2);
        
        // Test case 3
        int[] arr3 = {7, 8, 9, 11, 12};
        System.out.println("Test Case 3:");
        displayLinearSearchResult(arr3);
        
        // Test case 4
        int[] arr4 = {1};
        System.out.println("Test Case 4:");
        displayLinearSearchResult(arr4);
        
        // Test case 5
        int[] arr5 = {-1, -2, -3};
        System.out.println("Test Case 5:");
        displayLinearSearchResult(arr5);
        
        // Test case 6
        int[] arr6 = {1, 2, 3, 4, 5};
        System.out.println("Test Case 6:");
        displayLinearSearchResult(arr6);
        
        System.out.println("═".repeat(60));
        System.out.println();
        
        // ==================== Part 2: Binary Search ====================
        System.out.println("╔─── PART 2: Binary Search - Find Target Index ───╗\n");
        
        // Test case 1
        System.out.println("Test Case 1:");
        int[] arr7 = {5, 2, 8, 1, 9, 3};
        displayBinarySearchResult(arr7, 8);
        
        // Test case 2
        System.out.println("Test Case 2:");
        int[] arr8 = {10, 20, 30, 40, 50};
        displayBinarySearchResult(arr8, 30);
        
        // Test case 3
        System.out.println("Test Case 3:");
        int[] arr9 = {5, 2, 8, 1, 9, 3};
        displayBinarySearchResult(arr9, 100);
        
        // Test case 4
        System.out.println("Test Case 4:");
        int[] arr10 = {1};
        displayBinarySearchResult(arr10, 1);
        
        // Test case 5
        System.out.println("Test Case 5:");
        int[] arr11 = {15, 7, 22, 3, 18, 11, 9};
        displayBinarySearchResult(arr11, 7);
        
        System.out.println("═".repeat(60));
        System.out.println();
        
        // ==================== Analysis ====================
        System.out.println("╔─── PERFORMANCE ANALYSIS ───╗\n");
        
        System.out.println("Linear Search (First Missing Positive):");
        System.out.println("- Time Complexity: O(n)");
        System.out.println("- Space Complexity: O(n) for HashSet");
        System.out.println("- Approach: Iterate through array, mark seen numbers");
        System.out.println();
        
        System.out.println("Binary Search (Find Target Index):");
        System.out.println("- Time Complexity: O(log n) after sorting O(n log n)");
        System.out.println("- Space Complexity: O(n) for sorted array copy");
        System.out.println("- Approach: Sort array, then binary search");
        System.out.println();
        
        System.out.println("When to use:");
        System.out.println("- Use Linear Search when you need to find specific patterns");
        System.out.println("- Use Binary Search for faster lookups in sorted data");
        System.out.println("- For sorted arrays, binary search is log n vs linear search n");
        System.out.println();
        
        System.out.println("═".repeat(60));
        System.out.println();
        
        // ==================== Combined Example ====================
        System.out.println("╔─── COMBINED EXAMPLE ───╗\n");
        
        int[] combinedArr = {3, 4, -1, 1, 2, 5};
        System.out.println("Array: [3, 4, -1, 1, 2, 5]");
        System.out.println();
        
        System.out.println("1. Linear Search Result:");
        System.out.println("   " + findFirstMissingPositiveDetailed(combinedArr));
        System.out.println();
        
        System.out.println("2. Binary Search Results:");
        System.out.println("   " + binarySearchDetailed(combinedArr, 4));
        System.out.println("   " + binarySearchDetailed(combinedArr, 6));
    }
}
