import java.io.*;
import java.util.*;

public class VersionControlledStorage {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    Map<String, List<Version>> files = new HashMap<>();
    for (int i = 0; i < N; i++) {
      String line = br.readLine().trim();
      String[] parts = line.split(" ");
      String op = parts[0];
      if (op.equals("UPLOAD")) {
        String file = parts[1];
        String ver = parts[2];
        long size = Long.parseLong(parts[3]);
        files.computeIfAbsent(file, k -> new ArrayList<>());
        boolean exists = false;
        for (Version v : files.get(file)) {
          if (v.version.equals(ver)) {
            exists = true;
            break;
          }
        }
        if (!exists) {
          files.get(file).add(new Version(ver, size));
        }
      } else if (op.equals("FETCH")) {
        String file = parts[1];
        if (!files.containsKey(file)) {
          System.out.println("File Not Found");
        } else {
          List<Version> vers = new ArrayList<>(files.get(file));
          vers.sort((a, b) -> {
            if (a.size != b.size)
              return Long.compare(a.size, b.size);
            return a.version.compareTo(b.version);
          });
          for (Version v : vers) {
            System.out.println(file + " " + v.version + " " + v.size);
          }
        }
      } else if (op.equals("LATEST")) {
        String file = parts[1];
        if (!files.containsKey(file) || files.get(file).isEmpty()) {
          System.out.println("File Not Found");
        } else {
          Version latest = files.get(file).get(files.get(file).size() - 1);
          System.out.println(file + " " + latest.version + " " + latest.size);
        }
      } else if (op.equals("TOTAL_STORAGE")) {
        String file = parts[1];
        if (!files.containsKey(file)) {
          System.out.println("File Not Found");
        } else {
          long total = 0;
          for (Version v : files.get(file)) {
            total += v.size;
          }
          System.out.println(file + " " + total);
        }
      }
    }
  }

  static class Version {
    String version;
    long size;

    Version(String v, long s) {
      version = v;
      size = s;
    }
  }
}