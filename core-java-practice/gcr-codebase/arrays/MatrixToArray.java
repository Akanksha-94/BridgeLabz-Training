import java.util.Scanner;

public class MatrixToArray {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter number of rows: ");
    int rows = sc.nextInt();
    System.out.print("Enter number of columns: ");
    int cols = sc.nextInt();

    int[][] mat = new int[rows][cols];
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        System.out.print("Enter element [" + r + "][" + c + "]: ");
        mat[r][c] = sc.nextInt();
      }
    }

    int[] arr = new int[rows * cols];
    int idx = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        arr[idx++] = mat[r][c];
      }
    }

    System.out.println("1D array contents:");
    for (int i = 0; i < arr.length; i++)
      System.out.print(arr[i] + (i + 1 < arr.length ? ", " : "\n"));
    
  }
}
