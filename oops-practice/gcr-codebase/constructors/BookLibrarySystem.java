public class Book {

    public String ISBN;        // public
    protected String title;    // protected
    private String author;     // private

    public Book(String ISBN, String title, String author) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
    }

    // Getter
    public String getAuthor() {
        return author;
    }

    // Setter
    public void setAuthor(String author) {
        this.author = author;
    }

    public void display() {
        System.out.println("ISBN: " + ISBN + ", Title: " + title + ", Author: " + author);
    }
}

// Subclass
class EBook extends Book {

    public EBook(String ISBN, String title, String author) {
        super(ISBN, title, author);
    }

    public void showInfo() {
        System.out.println("Accessing public ISBN: " + ISBN);
        System.out.println("Accessing protected Title: " + title);
    }
}
