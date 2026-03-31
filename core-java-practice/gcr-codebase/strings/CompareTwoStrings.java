import java.util.Scanner;

public class CompareTwoStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        boolean charAtResult = compareUsingCharAt(s1, s2);
        boolean equalsResult = s1.equals(s2);

        System.out.println(charAtResult);
        System.out.println(equalsResult);
    }

    static boolean compareUsingCharAt(String a, String b) {
        if(a.length() != b.length()) return false;
        for(int i = 0; i < a.length(); i++)
            if(a.charAt(i) != b.charAt(i)) return false;
        return true;
    }
}
