import java.util.Stack;

public class StockSpanProblem {
    
    public static int[] calculateStockSpan(int[] prices) {
        int[] span = new int[prices.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        
        return span;
    }
    
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        int[] span = calculateStockSpan(prices);
        
        System.out.println("Stock Prices: ");
        for (int price : prices) {
            System.out.print(price + " ");
        }
        System.out.println();
        
        System.out.println("Stock Span: ");
        for (int s : span) {
            System.out.print(s + " ");
        }
        System.out.println();
        
        System.out.println("\n========== Day-wise Span ==========");
        for (int i = 0; i < prices.length; i++) {
            System.out.println("Day " + (i + 1) + " - Price: " + prices[i] + ", Span: " + span[i]);
        }
    }
}