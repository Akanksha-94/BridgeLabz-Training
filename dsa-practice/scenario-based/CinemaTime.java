import java.util.ArrayList;
import java.util.List;

class InvalidTimeFormatException extends Exception {
    public InvalidTimeFormatException(String message) {
        super(message);
    }
}

public class CinemaTime {
    private List<String> titles = new ArrayList<>();
    private List<String> times = new ArrayList<>();

    public void addMovie(String title, String time) throws InvalidTimeFormatException {
        if (!isValidTime(time)) {
            throw new InvalidTimeFormatException("Invalid time format: " + time);
        }
        titles.add(title);
        times.add(time);
    }

    public void searchMovie(String keyword) {
        try {
            int index = Integer.parseInt(keyword);
            String result = String.format("%s at %s", titles.get(index), times.get(index));
            System.out.println(result);
            return;
        } catch (NumberFormatException e) {
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid movie index");
            return;
        }

        for (int i = 0; i < titles.size(); i++) {
            if (titles.get(i).contains(keyword)) {
                String result = titles.get(i) + " at " + times.get(i);
                System.out.println(result);
            }
        }
    }

    public void displayAllMovies() {
        for (int i = 0; i < titles.size(); i++) {
            System.out.println(String.format("%s at %s", titles.get(i), times.get(i)));
        }
    }

    public String[] generateReport() {
        String[] report = new String[titles.size()];
        for (int i = 0; i < titles.size(); i++) {
            report[i] = titles.get(i) + " at " + times.get(i);
        }
        return report;
    }

    private boolean isValidTime(String time) {
        if (!time.matches("\\d{2}:\\d{2}")) return false;
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
    }

    public static void main(String[] args) {
        CinemaTime cinema = new CinemaTime();
        try {
            cinema.addMovie("Inception", "18:30");
            cinema.addMovie("Interstellar", "21:15");
        } catch (InvalidTimeFormatException e) {
            System.out.println(e.getMessage());
        }

        cinema.displayAllMovies();
        cinema.searchMovie("Inter");
        cinema.searchMovie("5");
    }
}