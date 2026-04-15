import java.io.*;
import java.util.*;

abstract class Festival {
  String name, location, date;

  Festival(String n, String l, String d) {
    name = n;
    location = l;
    date = d;
  }

  abstract void display();
}

class MusicFestival extends Festival {
  String headliner, genre;
  double price;

  MusicFestival(String n, String l, String d, String h, String g, double p) {
    super(n, l, d);
    headliner = h;
    genre = g;
    price = p;
  }

  void display() {
    System.out.println("Festival Name: " + name);
    System.out.println("Location: " + location);
    System.out.println("Date: " + date);
    System.out.println("Headliner: " + headliner);
    System.out.println("Music Genre: " + genre);
    System.out.println("Ticket Price: " + price);
  }
}

class FoodFestival extends Festival {
  String cuisine;
  int stalls;
  double fee;

  FoodFestival(String n, String l, String d, String c, int s, double f) {
    super(n, l, d);
    cuisine = c;
    stalls = s;
    fee = f;
  }

  void display() {
    System.out.println("Festival Name: " + name);
    System.out.println("Location: " + location);
    System.out.println("Date: " + date);
    System.out.println("Cuisine: " + cuisine);
    System.out.println("Number of Stalls: " + stalls);
    System.out.println("Entry Fee: " + fee);
  }
}

class ArtFestival extends Festival {
  String artType;
  int artists;
  double fee;

  ArtFestival(String n, String l, String d, String a, int ar, double f) {
    super(n, l, d);
    artType = a;
    artists = ar;
    fee = f;
  }

  void display() {
    System.out.println("Festival Name: " + name);
    System.out.println("Location: " + location);
    System.out.println("Date: " + date);
    System.out.println("Art Type: " + artType);
    System.out.println("Number of Artists: " + artists);
    System.out.println("Exhibition Fee: " + fee);
  }
}

public class FestivalManagement {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Map<String, Festival> festivals = new HashMap<>();
    String line;
    while ((line = br.readLine()) != null) {
      line = line.trim();
      if (line.equals("EXIT"))
        break;
      String[] parts = line.split(" ");
      if (parts[0].equals("ADD_FESTIVAL")) {
        String type = parts[1];
        if (type.equals("MUSIC")) {
          String name = parts[2];
          String loc = parts[3];
          String date = parts[4];
          String head = parts[5];
          String genre = parts[6];
          double price = Double.parseDouble(parts[7]);
          festivals.put(name, new MusicFestival(name, loc, date, head, genre, price));
        } else if (type.equals("FOOD")) {
          String name = parts[2];
          String loc = parts[3];
          String date = parts[4];
          String cui = parts[5];
          int stalls = Integer.parseInt(parts[6]);
          double fee = Double.parseDouble(parts[7]);
          festivals.put(name, new FoodFestival(name, loc, date, cui, stalls, fee));
        } else if (type.equals("ART")) {
          String name = parts[2];
          String loc = parts[3];
          String date = parts[4];
          String art = parts[5];
          int artnum = Integer.parseInt(parts[6]);
          double fee = Double.parseDouble(parts[7]);
          festivals.put(name, new ArtFestival(name, loc, date, art, artnum, fee));
        }
      } else if (parts[0].equals("DISPLAY_DETAILS")) {
        String name = parts[1];
        if (festivals.containsKey(name)) {
          festivals.get(name).display();
        }
      }
    }
  }
}