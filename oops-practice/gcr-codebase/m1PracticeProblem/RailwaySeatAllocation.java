import java.io.*;
import java.util.*;

class Booking {
  String passengerId;
  int seatCount;
  double bookingFare;

  Booking(String pid, int sc, double bf) {
    passengerId = pid;
    seatCount = sc;
    bookingFare = bf;
  }
}

class Train {
  String trainId;
  String source;
  String destination;
  int totalSeats;
  int availableSeats;
  double baseFare;
  List<Booking> bookings;

  Train(String tid, String src, String dest, int ts, double bf) {
    trainId = tid;
    source = src;
    destination = dest;
    totalSeats = ts;
    availableSeats = ts;
    baseFare = bf;
    bookings = new ArrayList<>();
  }
}

public class RailwaySeatAllocation {
  private static List<Train> trains = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String line = br.readLine().trim();
      String[] parts = line.split(" ");
      String cmd = parts[0];
      if (cmd.equals("ADDTRAIN")) {
        String tid = parts[1];
        String src = parts[2];
        String dest = parts[3];
        int ts = Integer.parseInt(parts[4]);
        double bf = Double.parseDouble(parts[5]);
        addTrain(tid, src, dest, ts, bf);
      } else if (cmd.equals("BOOK")) {
        String tid = parts[1];
        String pid = parts[2];
        int sc = Integer.parseInt(parts[3]);
        int res = bookSeats(tid, pid, sc);
        if (res == 1) {
          // already printed
        } else {
          System.out.println("Booking failed");
        }
      } else if (cmd.equals("CANCEL")) {
        String tid = parts[1];
        String pid = parts[2];
        int res = cancelBooking(tid, pid);
        if (res == 1) {
          System.out.println("CANCELLED " + tid + " " + pid);
        } else {
          System.out.println("Cancellation failed");
        }
      } else if (cmd.equals("ROUTE")) {
        String src = parts[1];
        String dest = parts[2];
        List<Train> routes = findRoute(src, dest);
        if (routes.isEmpty()) {
          System.out.println("No trains available");
        } else {
          for (Train t : routes) {
            System.out.println(t.trainId + " " + t.availableSeats);
          }
        }
      } else if (cmd.equals("SUMMARY")) {
        List<Train> summary = getRevenueSummary();
        for (Train t : summary) {
          double rev = 0;
          for (Booking b : t.bookings) {
            rev += b.bookingFare;
          }
          System.out.println(t.trainId + " " + (int) rev);
        }
      }
    }
  }

  private static int addTrain(String trainId, String source, String destination, int totalSeats, double baseFare) {
    for (Train t : trains) {
      if (t.trainId.equals(trainId))
        return 0;
    }
    trains.add(new Train(trainId, source, destination, totalSeats, baseFare));
    return 1;
  }

  private static int bookSeats(String trainId, String passengerId, int seatCount) {
    for (Train t : trains) {
      if (t.trainId.equals(trainId)) {
        if (seatCount <= t.availableSeats) {
          double fare = (seatCount * t.baseFare) + (seatCount * 25);
          t.availableSeats -= seatCount;
          t.bookings.add(new Booking(passengerId, seatCount, fare));
          System.out.println("BOOKED " + trainId + " " + passengerId + " " + (int) fare);
          return 1;
        }
        return 0;
      }
    }
    return 0;
  }

  private static int cancelBooking(String trainId, String passengerId) {
    for (Train t : trains) {
      if (t.trainId.equals(trainId)) {
        for (int i = 0; i < t.bookings.size(); i++) {
          Booking b = t.bookings.get(i);
          if (b.passengerId.equals(passengerId)) {
            t.availableSeats += b.seatCount;
            t.bookings.remove(i);
            return 1;
          }
        }
      }
    }
    return 0;
  }

  private static List<Train> findRoute(String source, String destination) {
    List<Train> list = new ArrayList<>();
    for (Train t : trains) {
      if (t.source.equals(source) && t.destination.equals(destination)) {
        list.add(t);
      }
    }
    return list;
  }

  private static List<Train> getRevenueSummary() {
    return trains;
  }
}