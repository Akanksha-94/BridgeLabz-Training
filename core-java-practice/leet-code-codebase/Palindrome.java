public class Palindrome {

    public static boolean isPalindrome(int x) {

        int original = x;
        int rev = 0;

        while (x > 0) {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x = x / 10;
        }

        return original == rev;
    }

    public static void main(String[] args) {

        int num = 121;

        if (isPalindrome(num)) {
            System.out.println("Palindrome");
        } else {
            System.out.println("Not Palindrome");
        }
    }
}
