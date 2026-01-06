public class LibraryManagementSystem {
    //Arrays to store book details
    static String[] titles = {
            "JavaProgramming",
            "DataStructures",
            "OperatingSystems",
            "ComputerNetworks"
    };
    static String[] authors = {
            "JamesGosling",
            "MarkAllen",
            "AbrahamSilberschatz",
            "AndrewTanenbaum"
    };
    static boolean[] isAvailable = {
            true,
            true,
            false,
            true
    };
    //Method to display all books
    public static void displayBooks() {
        System.out.println("BookList:");
        for (int i = 0; i < titles.length; i++) {
            String status = isAvailable[i] ? "Available" : "CheckedOut";
            System.out.println((i + 1) + "." + titles[i] + "-" + authors[i] + "-" + status);
        }
    }
    //Method to search book by partial title
    public static void searchBookByTitle(String keyword) {
        System.out.println("SearchResults:");
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(titles[i] + " By " + authors[i]);
            }
        }
    }
    //Method to update book status
    public static void updateBookStatus(int index, boolean status) {
        if (index >= 0 && index < titles.length) {
            isAvailable[index] = status;
            System.out.println("BookStatusUpdatedSuccessfully");
        } else {
            System.out.println("InvalidBookIndex");
        }
    }
    public static void main(String[] args) {
        displayBooks();
        System.out.println("\nSearchingFor:Java");
        searchBookByTitle("Java");
        System.out.println("\nCheckingOutBook1");
        updateBookStatus(0, false);
        System.out.println("\nUpdatedBookList:");
        displayBooks();
    }
}