class PalindromeChecker {
    String text;

    // Method to check palindrome
    boolean isPalindrome() {
        // Remove spaces and convert to lowercase
        String cleanText = text.replaceAll("\\s+", "").toLowerCase();

        int start = 0;
        int end = cleanText.length() - 1;

        while (start < end) {
            if (cleanText.charAt(start) != cleanText.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // Method to display result
    void displayResult() {
        if (isPalindrome()) {
            System.out.println(text + " is palindrome");
        } else {
            System.out.println(text + " is not Palindrome");
        }
    }

    public static void main(String[] args) {

        PalindromeChecker p1 = new PalindromeChecker();
        p1.text = "A man a plan a canal Panama";
        p1.displayResult();

        PalindromeChecker p2 = new PalindromeChecker();
        p2.text = "Hello";
        p2.displayResult();
    }
}
