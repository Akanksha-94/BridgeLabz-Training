import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

class BookShelf {

    private static class Book {
        String title;
        String author;

        Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Book)) return false;
            Book b = (Book) o;
            return title.equals(b.title) && author.equals(b.author);
        }

        @Override
        public int hashCode() {
            return title.hashCode() * 31 + author.hashCode();
        }
    }

    private Map<String, LinkedList<Book>> catalog = new HashMap<>();
    private Set<Book> uniqueBooks = new HashSet<>();

    public void addBook(String genre, String title, String author) {
        Book book = new Book(title, author);
        if (!uniqueBooks.add(book)) return;

        catalog.computeIfAbsent(genre, g -> new LinkedList<>()).add(book);
    }

    public void borrowBook(String genre, String title, String author) {
        Book book = new Book(title, author);
        LinkedList<Book> list = catalog.get(genre);
        if (list == null) return;

        if (list.remove(book)) {
            uniqueBooks.remove(book);
        }
    }

    public void returnBook(String genre, String title, String author) {
        addBook(genre, title, author);
    }

    public void printCatalog() {
        for (Map.Entry<String, LinkedList<Book>> entry : catalog.entrySet()) {
            System.out.println("Genre: " + entry.getKey());
            for (Book b : entry.getValue()) {
                System.out.println(b.title + " - " + b.author);
            }
        }
    }

    public static void main(String[] args) {
        BookShelf shelf = new BookShelf();

        shelf.addBook("Fiction", "1984", "George Orwell");
        shelf.addBook("Fiction", "Animal Farm", "George Orwell");
        shelf.addBook("Science", "Brief History of Time", "Stephen Hawking");

        shelf.borrowBook("Fiction", "1984", "George Orwell");
        shelf.returnBook("Fiction", "1984", "George Orwell");

        shelf.printCatalog();
    }
}