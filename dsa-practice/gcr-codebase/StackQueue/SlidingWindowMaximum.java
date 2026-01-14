import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    
    public static int[] findMaxInSlidingWindow(int[] arr, int k) {
        int[] result = new int[arr.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i < arr.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.pollLast();
            }
            
            deque.addLast(i);
            
            if (i >= k - 1) {
                result[i - k + 1] = arr[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] arr = {1, 3, 1, 2, 0, 5, 3, 3};
        int k = 3;
        int[] result = findMaxInSlidingWindow(arr, k);
        
        System.out.println("Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        System.out.println("Window Size: " + k);
        System.out.println("Maximum in each window: ");
        for (int max : result) {
            System.out.print(max + " ");
        }
        System.out.println();
        
        System.out.println("\n========== Window Details ==========");
        for (int i = 0; i <= arr.length - k; i++) {
            System.out.print("Window [");
            for (int j = i; j < i + k; j++) {
                System.out.print(arr[j]);
                if (j < i + k - 1) System.out.print(", ");
            }
            System.out.println("] -> Max: " + result[i]);
        }
    }
}