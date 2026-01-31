public class CreditCardValidator {
    public static boolean isVisa(String s) {
        return s != null && s.matches("^4\\d{15}$");
    }

    public static boolean isMasterCard(String s) {
        return s != null && s.matches("^5\\d{15}$");
    }

    public static void main(String[] args) {
        System.out.println("Visa example 4123456789012345 -> " + (isVisa("4123456789012345") ? "Valid" : "Invalid"));
        System.out.println("MasterCard example 5123456789012345 -> " + (isMasterCard("5123456789012345") ? "Valid" : "Invalid"));
    }
}