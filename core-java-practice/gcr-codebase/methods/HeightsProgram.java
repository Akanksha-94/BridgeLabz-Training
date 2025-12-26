import java.util.Random;

public class HeightsProgram {
    public static int[] generateHeights(int n) {
        Random r = new Random();
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = 150 + r.nextInt(101); // 150..250
        }
        return heights;
    }

    public static int sum(int[] a) {
        int s = 0;
        for (int v : a) s += v;
        return s;
    }

    public static double mean(int[] a) {
        return (double) sum(a) / a.length;
    }

    public static int min(int[] a) {
        int m = Integer.MAX_VALUE;
        for (int v : a) if (v < m) m = v;
        return m;
    }

    public static int max(int[] a) {
        int m = Integer.MIN_VALUE;
        for (int v : a) if (v > m) m = v;
        return m;
    }

    public static void main(String[] args) {
        int[] heights = generateHeights(11);
        System.out.println("Heights (cms):");
        for (int h : heights) System.out.print(h + " ");
        System.out.println();
        System.out.println("Sum: " + sum(heights));
        System.out.printf("Mean: %.2f\n", mean(heights));
        System.out.println("Shortest: " + min(heights));
        System.out.println("Tallest: " + max(heights));
    }
}
