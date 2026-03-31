public class StreamBuzz {
  public static void main(String[] args) {
    java.util.Scanner sc = new java.util.Scanner(System.in);
    Program program = new Program();
    while (true) {
      if (!sc.hasNextInt())
        break;
      int choice = sc.nextInt();
      if (choice == 1) {
        String name = sc.next();
        double[] likes = new double[4];
        for (int i = 0; i < 4; i++) {
          likes[i] = sc.nextDouble();
        }
        CreatorStats cs = new CreatorStats();
        cs.CreatorName = name;
        cs.WeeklyLikes = likes;
        program.RegisterCreator(cs);
        System.out.println("Creator registered successfully");
      } else if (choice == 2) {
        double threshold = sc.nextDouble();
        java.util.Map<String, Integer> top = program.GetTopPostCounts(CreatorStats.EngagementBoard, threshold);
        if (top.isEmpty()) {
          System.out.println("No top-performing posts this week");
        } else {
          for (java.util.Map.Entry<String, Integer> e : top.entrySet()) {
            System.out.println(e.getKey() + " : " + e.getValue());
          }
        }
      } else if (choice == 3) {
        double avg = program.CalculateAverageLikes();
        System.out.printf("Overall average weekly likes: %.1f%n", avg);
      } else if (choice == 4) {
        System.out.println("Logging off — Keep Creating with StreamBuzz!");
        break;
      }
    }
    sc.close();
  }
}

class CreatorStats {
  public String CreatorName;
  public double[] WeeklyLikes;

  public static java.util.List<CreatorStats> EngagementBoard = new java.util.ArrayList<>();
}

class Program {
  public void RegisterCreator(CreatorStats record) {
    CreatorStats.EngagementBoard.add(record);
  }

  public java.util.Map<String, Integer> GetTopPostCounts(java.util.List<CreatorStats> records, double likeThreshold) {
    java.util.Map<String, Integer> result = new java.util.LinkedHashMap<>();
    for (CreatorStats cs : records) {
      int count = 0;
      if (cs.WeeklyLikes != null) {
        for (double d : cs.WeeklyLikes) {
          if (d >= likeThreshold)
            count++;
        }
      }
      if (count > 0)
        result.put(cs.CreatorName, count);
    }
    return result;
  }

  public double CalculateAverageLikes() {
    double sum = 0.0;
    int total = 0;
    for (CreatorStats cs : CreatorStats.EngagementBoard) {
      if (cs.WeeklyLikes != null) {
        for (double d : cs.WeeklyLikes) {
          sum += d;
          total++;
        }
      }
    }
    if (total == 0)
      return 0.0;
    return sum / total;
  }
}
