import java.util.Random;
import java.util.Scanner;

public class MatrixOperations {
  public static int[][] randomMatrix(int r, int c, int maxVal) {
    Random rnd = new Random();
    int[][] a = new int[r][c];
    for (int i = 0; i < r; i++)
      for (int j = 0; j < c; j++)
        a[i][j] = rnd.nextInt(maxVal + 1);
    return a;
  }

  public static void printMatrix(int[][] a) {
    for (int i = 0; i < a.length; i++) {
      for (int j = 0; j < a[0].length; j++)
        System.out.printf("%6d", a[i][j]);
      System.out.println();
    }
  }

  public static int[][] add(int[][] A, int[][] B) {
    int r = A.length, c = A[0].length;
    int[][] C = new int[r][c];
    for (int i = 0; i < r; i++)
      for (int j = 0; j < c; j++)
        C[i][j] = A[i][j] + B[i][j];
    return C;
  }

  public static int[][] subtract(int[][] A, int[][] B) {
    int r = A.length, c = A[0].length;
    int[][] C = new int[r][c];
    for (int i = 0; i < r; i++)
      for (int j = 0; j < c; j++)
        C[i][j] = A[i][j] - B[i][j];
    return C;
  }

  public static int[][] multiply(int[][] A, int[][] B) {
    int r = A.length, common = A[0].length, c = B[0].length;
    int[][] C = new int[r][c];
    for (int i = 0; i < r; i++)
      for (int j = 0; j < c; j++) {
        int s = 0;
        for (int k = 0; k < common; k++)
          s += A[i][k] * B[k][j];
        C[i][j] = s;
      }
    return C;
  }

  public static int[][] transpose(int[][] A) {
    int r = A.length, c = A[0].length;
    int[][] T = new int[c][r];
    for (int i = 0; i < r; i++)
      for (int j = 0; j < c; j++)
        T[j][i] = A[i][j];
    return T;
  }

  public static int det2x2(int[][] M) {
    return M[0][0] * M[1][1] - M[0][1] * M[1][0];
  }

  public static int det3x3(int[][] M) {
    int a = M[0][0], b = M[0][1], c = M[0][2];
    int d = M[1][0], e = M[1][1], f = M[1][2];
    int g = M[2][0], h = M[2][1], i = M[2][2];
    return a * (e * i - f * h) - b * (d * i - f * g) + c * (d * h - e * g);
  }

  public static double[][] inverse2x2(int[][] M) {
    double det = det2x2(M);
    if (det == 0)
      return null;
    double[][] inv = new double[2][2];
    inv[0][0] = M[1][1] / det;
    inv[0][1] = -M[0][1] / det;
    inv[1][0] = -M[1][0] / det;
    inv[1][1] = M[0][0] / det;
    return inv;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter rows for matrix A: ");
    int r1 = sc.nextInt();
    System.out.print("Enter cols for matrix A: ");
    int c1 = sc.nextInt();
    System.out.print("Enter cols for matrix B: ");
    int c2 = sc.nextInt();
    int[][] A = randomMatrix(r1, c1, 9);
    int[][] B = randomMatrix(c1, c2, 9);
    System.out.println("Matrix A:");
    printMatrix(A);
    System.out.println("Matrix B:");
    printMatrix(B);
    System.out.println("A x B:");
    printMatrix(multiply(A, B));
    System.out.println("Transpose of A:");
    printMatrix(transpose(A));
    if (r1 == 2 && c1 == 2) {
      System.out.println("Determinant of A (2x2): " + det2x2(A));
      double[][] inv = inverse2x2(A);
      if (inv != null) {
        System.out.println("Inverse of A:");
        for (int i = 0; i < 2; i++) {
          for (int j = 0; j < 2; j++)
            System.out.printf("%8.4f", inv[i][j]);
          System.out.println();
        }
      } else
        System.out.println("A is singular, no inverse");
    }
    if (r1 == 3 && c1 == 3)
      System.out.println("Determinant of A (3x3): " + det3x3(A));
  
  }
}
