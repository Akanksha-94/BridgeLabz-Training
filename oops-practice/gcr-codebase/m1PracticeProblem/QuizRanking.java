import java.io.*;
import java.util.*;

public class QuizRanking {
  static class Student {
    String name;
    String dept;
    int q1, q2, q3;
    int total;
    int index;

    Student(String n, String d, int a, int b, int c, int idx) {
      name = n;
      dept = d;
      q1 = a;
      q2 = b;
      q3 = c;
      total = a + b + c;
      index = idx;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    Map<String, List<Student>> depts = new HashMap<>();
    List<Student> all = new ArrayList<>();
    int idx = 0;
    for (int i = 0; i < N; i++) {
      String line = br.readLine().trim();
      String[] parts = line.split(" ");
      String cmd = parts[0];
      if (cmd.equals("Record")) {
        String name = parts[1];
        String dept = parts[2];
        int q1 = Integer.parseInt(parts[3]);
        int q2 = Integer.parseInt(parts[4]);
        int q3 = Integer.parseInt(parts[5]);
        Student s = new Student(name, dept, q1, q2, q3, idx++);
        depts.computeIfAbsent(dept, k -> new ArrayList<>()).add(s);
        all.add(s);
        System.out.println("Record Added: " + name);
      } else if (cmd.equals("Top")) {
        String param = parts[1];
        if (depts.containsKey(param)) {
          List<Student> list = depts.get(param);
          int maxScore = Integer.MIN_VALUE;
          for (Student s : list) {
            if (s.total > maxScore)
              maxScore = s.total;
          }
          List<Student> tops = new ArrayList<>();
          for (Student s : list) {
            if (s.total == maxScore)
              tops.add(s);
          }
          tops.sort(Comparator.comparingInt(a -> a.index));
          for (Student s : tops) {
            System.out.println(s.name + " " + s.total);
          }
        } else if (param.equals("Q1") || param.equals("Q2") || param.equals("Q3")) {
          int quiz = param.equals("Q1") ? 1 : param.equals("Q2") ? 2 : 3;
          int max = Integer.MIN_VALUE;
          for (Student s : all) {
            int score = quiz == 1 ? s.q1 : quiz == 2 ? s.q2 : s.q3;
            if (score > max)
              max = score;
          }
          List<Student> tops = new ArrayList<>();
          for (Student s : all) {
            int score = quiz == 1 ? s.q1 : quiz == 2 ? s.q2 : s.q3;
            if (score == max)
              tops.add(s);
          }
          tops.sort(Comparator.comparingInt(a -> a.index));
          for (Student s : tops) {
            System.out.println(s.name + " " + max);
          }
        } else {
          System.out.println("Department Not Found");
        }
      }
    }
  }
}