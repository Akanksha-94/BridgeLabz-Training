import java.util.Stack;

public class SortStackUsingRecursion {
    
    public static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        
        int element = stack.pop();
        sortStack(stack);
        insertElementInSortedStack(stack, element);
    }
    
    public static void insertElementInSortedStack(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }
        
        int top = stack.pop();
        insertElementInSortedStack(stack, element);
        stack.push(top);
    }
    
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(34);
        stack.push(3);
        stack.push(31);
        stack.push(5);
        stack.push(17);
        
        System.out.println("Original Stack: " + stack);
        sortStack(stack);
        System.out.println("Sorted Stack: " + stack);
    }
}