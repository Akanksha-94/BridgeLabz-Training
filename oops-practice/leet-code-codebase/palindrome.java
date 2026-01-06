class Solution {

 
    public boolean isPalindrome(int x) {
        String s = Integer.toString(x);
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

  
    public static void main(String[] args) {
        Solution sol = new Solution();

        int num1 = 121;
        int num2 = -121;
        int num3 = 12321;

        System.out.println(num1 + " is palindrome? " + sol.isPalindrome(num1));
        System.out.println(num2 + " is palindrome? " + sol.isPalindrome(num2));
        System.out.println(num3 + " is palindrome? " + sol.isPalindrome(num3));
    }
}
