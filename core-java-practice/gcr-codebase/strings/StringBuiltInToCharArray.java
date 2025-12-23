import java.util.Scanner;
public class StringBuiltInToCharArray {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str1 = sc.nextLine();
    String str2 = sc.nextLine();  
        // user-defined conversion
        char[] manualArray = convertToCharArray(str1);

        // built-in toCharArray()
        char[] builtInArray = str1.toCharArray();

        // compare both
        boolean result = compareArrays(manualArray, builtInArray);

        // display results
        System.out.println("Manual Char Array: " + new String(manualArray));
        System.out.println("Built-in Char Array: " + new String(builtInArray));

        if(result)
            System.out.println("Both arrays are SAME");
        else
            System.out.println("Arrays are DIFFERENT");
    }

    // Method 1: Convert String to char array manually
    static char[] convertToCharArray(String s) {
        char[] arr = new char[s.length()];
        for(int i = 0; i < s.length(); i++) {
            arr[i] = s.charAt(i);
        }
        return arr;
    }

    // Method 2: Compare two char arrays
    static boolean compareArrays(char[] a, char[] b) {
        if(a.length != b.length)
            return false;

        for(int i = 0; i < a.length; i++) {
            if(a[i] != b[i])
                return false;
        }
        return true;
    }
}


    
  
  

