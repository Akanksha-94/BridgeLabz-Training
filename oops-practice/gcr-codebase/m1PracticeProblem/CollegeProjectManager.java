import java.io.*;
import java.util.*;

class ProjectTeam {
  String teamId;
  String section;
  String domain;
  String projectName;
  int projectScore;

  ProjectTeam(String tid, String sec, String dom, String pname, int score) {
    teamId = tid;
    section = sec;
    domain = dom;
    projectName = pname;
    projectScore = score;
  }
}

public class CollegeProjectManager {
  private static List<ProjectTeam> teams = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine().trim());
    for (int i = 0; i < N; i++) {
      String line = br.readLine().trim();
      String[] parts = line.split(" ");
      String cmd = parts[0];
      if (cmd.equals("REGISTER")) {
        String tid = parts[1];
        String sec = parts[2];
        String dom = parts[3];
        String pname = parts[4];
        int score = Integer.parseInt(parts[5]);
        registerTeam(tid, sec, dom, pname, score);
      } else if (cmd.equals("REVISE")) {
        String tid = parts[1];
        int score = Integer.parseInt(parts[2]);
        int res = reviseScore(tid, score);
        if (res == 0) {
          System.out.println("team is not available");
        } else {
          System.out.println("REVISED " + tid + " " + score);
        }
      } else if (cmd.equals("FILTERDOMAIN")) {
        String dom = parts[1];
        List<ProjectTeam> filtered = filterByDomain(dom);
        if (filtered.isEmpty()) {
          System.out.println("Team is not available for the domain: " + dom);
        } else {
          for (ProjectTeam t : filtered) {
            System.out
                .println(t.teamId + " " + t.section + " " + t.domain + " " + t.projectName + " " + t.projectScore);
          }
        }
      } else if (cmd.equals("QUALIFY")) {
        int cutoff = Integer.parseInt(parts[1]);
        List<ProjectTeam> qualified = qualifyTeams(cutoff);
        if (qualified.isEmpty()) {
          System.out.println("No team qualified");
        } else {
          for (ProjectTeam t : qualified) {
            System.out
                .println(t.teamId + " " + t.section + " " + t.domain + " " + t.projectName + " " + t.projectScore);
          }
        }
      }
    }
  }

  private static int registerTeam(String teamId, String section, String domain, String projectName, int projectScore) {
    for (ProjectTeam t : teams) {
      if (t.teamId.equals(teamId))
        return 0;
    }
    teams.add(new ProjectTeam(teamId, section, domain, projectName, projectScore));
    return 1;
  }

  private static int reviseScore(String teamId, int projectScore) {
    for (ProjectTeam t : teams) {
      if (t.teamId.equals(teamId)) {
        t.projectScore = projectScore;
        return 1;
      }
    }
    return 0;
  }

  private static List<ProjectTeam> filterByDomain(String domain) {
    List<ProjectTeam> list = new ArrayList<>();
    for (ProjectTeam t : teams) {
      if (t.domain.equals(domain)) {
        list.add(t);
      }
    }
    return list;
  }

  private static List<ProjectTeam> qualifyTeams(int cutoff) {
    List<ProjectTeam> list = new ArrayList<>();
    for (ProjectTeam t : teams) {
      if (t.projectScore >= cutoff) {
        list.add(t);
      }
    }
    return list;
  }
}