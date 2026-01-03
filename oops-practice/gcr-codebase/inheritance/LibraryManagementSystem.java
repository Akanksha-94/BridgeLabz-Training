class Book {
    String title;
    int publicationYear;

    void displayInfo() {
        System.out.println("Book Title: " + title);
        System.out.println("Publication Year: " + publicationYear);
    }
}

class Author extends Book {
    String name;
    String bio;

    @Override
    void displayInfo() {
        super.displayInfo(); // call Book class method
        System.out.println("Author Name: " + name);
        System.out.println("Author Bio: " + bio);
        System.out.println("-------------------------");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {

        Author book1 = new Author();
        book1.title = "Wings of Fire";
        book1.publicationYear = 1999;
        book1.name = "A. P. J. Abdul Kalam";
        book1.bio = "Indian aerospace scientist and former President of India";

        Author book2 = new Author();
        book2.title = "2 States";
        book2.publicationYear = 2009;
        book2.name = "Chetan Bhagat";
        book2.bio = "Indian author and motivational speaker";

        book1.displayInfo();
        book2.displayInfo();
    }
}
