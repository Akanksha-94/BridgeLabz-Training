import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Movie {
    String name;
    int releaseYear;
    double rating;

    Movie(String name, int releaseYear, double rating) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public String toString() {
        return name + " (" + releaseYear + ") : " + rating;
    }
}

public class Top5TrendingMovies {
    public static void main(String[] args) {

        List<Movie> movies = Arrays.asList(
            new Movie("Inception", 2010, 8.8),
            new Movie("The Dark Knight", 2008, 9.0),
            new Movie("Interstellar", 2014, 8.6),
            new Movie("Parasite", 2019, 8.6),
            new Movie("Avengers: Endgame", 2019, 8.4),
            new Movie("Joker", 2019, 8.5),
            new Movie("1917", 2019, 8.3)
        );

        List<Movie> top5Trending = movies.stream()
            // optional filter (adjust year if needed)
            .filter(m -> m.releaseYear >= 2008)

            // sort by rating desc, then year desc
            .sorted(Comparator
                .comparingDouble((Movie m) -> m.rating).reversed()
                .thenComparingInt(m -> m.releaseYear).reversed())

            .limit(5)
            .collect(Collectors.toList());

        top5Trending.forEach(System.out::println);
    }
}
