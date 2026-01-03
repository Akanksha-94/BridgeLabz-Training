class HandleBookDetails {
    String title;
    String author;
    double price;

    // Method to display book details
    void displayDetails() {
        System.out.println("Title of the book: " + title);
        System.out.println("Author of the book: " + author);
        System.out.println("Price of the book: " + price);
        System.out.println();
    }

    public static void main(String[] args) {

        // First book
        HandleBookDetails book1 = new HandleBookDetails();
        book1.title = "2 States";
        book1.author = "Chetan Bhagat";
        book1.price = 500.0;

        // Second book
        HandleBookDetails book2 = new HandleBookDetails();
        book2.title = "Wings of Fire";
        book2.author = "A. P. J. Abdul Kalam";
        book2.price = 500.0;

        // Display details
        book1.displayDetails();
        book2.displayDetails();
    }
}
