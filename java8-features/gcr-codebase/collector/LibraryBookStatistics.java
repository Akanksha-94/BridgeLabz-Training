import java.util.*;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String genre;
    private int pages;

    public Book(String title, String genre, int pages) {
        this.title = title;
        this.genre = genre;
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }
}

public class LibraryBookStatistics {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
            new Book("Java Programming", "Technology", 450),
            new Book("Data Structures", "Technology", 520),
            new Book("The Great Gatsby", "Fiction", 180),
            new Book("1984", "Fiction", 328),
            new Book("Sapiens", "History", 443),
            new Book("Educated", "History", 334)
        );

        Map<String, IntSummaryStatistics> statsByGenre = books.stream()
            .collect(Collectors.groupingBy(
                Book::getGenre,
                Collectors.summarizingInt(Book::getPages)
            ));

        System.out.println("Book Statistics by Genre:");
        statsByGenre.forEach((genre, stats) -> {
            System.out.println("\n" + genre + ":");
            System.out.println("  Total Pages: " + stats.getSum());
            System.out.println("  Average Pages: " + stats.getAverage());
            System.out.println("  Maximum Pages: " + stats.getMax());
            System.out.println("  Minimum Pages: " + stats.getMin());
            System.out.println("  Book Count: " + stats.getCount());
        });
    }
}
