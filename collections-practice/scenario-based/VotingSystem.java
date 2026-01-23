import java.util.*;
public class VotingSystem {
    
    static class Vote {
        String candidate;
        long timestamp;

        Vote(String candidate) {
            this.candidate = candidate;
            this.timestamp = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return candidate;
        }
    }

    static class VotingManager {
      
        private Map<String, Integer> voteCount = new HashMap<>();
        
        private Map<Integer, Vote> voteHistory = new LinkedHashMap<>();

        private Map<String, Integer> sortedResults;
        
        private int voteCounter = 0;
        private List<String> validCandidates = new ArrayList<>();

        public VotingManager(String... candidates) {
            validCandidates.addAll(Arrays.asList(candidates));
            for (String candidate : candidates) {
                voteCount.put(candidate, 0);
            }
        }

        public void castVote(String candidate) {
            if (!validCandidates.contains(candidate)) {
                System.out.println("✗ Invalid candidate: " + candidate);
                return;
            }
            
            voteCount.put(candidate, voteCount.get(candidate) + 1);
            voteHistory.put(voteCounter++, new Vote(candidate));
            System.out.println("✓ Vote cast for: " + candidate);
        }

        public int getVoteCount(String candidate) {
            return voteCount.getOrDefault(candidate, 0);
        }

        public void displayVoteHistory() {
            System.out.println("\n=== Vote History (In Order) ===");
            if (voteHistory.isEmpty()) {
                System.out.println("No votes cast yet.");
                return;
            }
            voteHistory.values().forEach(vote -> System.out.println("  " + vote));
        }

        public void displayResultsSortedByName() {
            System.out.println("\n=== Results (Sorted by Candidate Name) ===");
            TreeMap<String, Integer> sortedByName = new TreeMap<>(voteCount);
            sortedByName.forEach((candidate, votes) -> 
                System.out.println(candidate + ": " + votes + " votes"));
        }

        public void displayResultsSortedByVotes() {
            System.out.println("\n=== Results (Sorted by Vote Count - Descending) ===");
            voteCount.entrySet()
                    .stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                    .forEach(entry -> 
                        System.out.println(entry.getKey() + ": " + entry.getValue() + " votes"));
        }

        public String getWinner() {
            return voteCount.entrySet()
                           .stream()
                           .max(Map.Entry.comparingByValue())
                           .map(Map.Entry::getKey)
                           .orElse(null);
        }

        public void displayStatistics() {
            System.out.println("\n=== Voting Statistics ===");
            int totalVotes = voteCount.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println("Total Votes Cast: " + totalVotes);
            System.out.println("Candidates: " + String.join(", ", validCandidates));
            System.out.println("Winner: " + getWinner());
            
            System.out.println("\nVote Distribution:");
            voteCount.forEach((candidate, votes) -> {
                double percentage = totalVotes > 0 ? (votes * 100.0 / totalVotes) : 0;
                System.out.println("  " + candidate + ": " + votes + " votes (" + 
                                 String.format("%.1f", percentage) + "%)");
            });
        }

        public void resetVoting() {
            voteCount.clear();
            voteHistory.clear();
            voteCounter = 0;
            for (String candidate : validCandidates) {
                voteCount.put(candidate, 0);
            }
            System.out.println("✓ Voting system reset.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Voting System ===\n");
        
        VotingManager voting = new VotingManager("Alice", "Bob", "Carol", "David");

        System.out.println("--- Casting Votes ---");
        voting.castVote("Alice");
        voting.castVote("Bob");
        voting.castVote("Alice");
        voting.castVote("Carol");
        voting.castVote("Bob");
        voting.castVote("Alice");
        voting.castVote("David");
        voting.castVote("Bob");
        voting.castVote("Alice");
        voting.castVote("Carol");
        voting.castVote("Alice");
        voting.castVote("Eve"); 

        voting.displayVoteHistory();
        voting.displayResultsSortedByName();
        voting.displayResultsSortedByVotes();
        voting.displayStatistics();

        System.out.println("\n=== Individual Vote Counts ===");
        System.out.println("Alice: " + voting.getVoteCount("Alice") + " votes");
        System.out.println("Bob: " + voting.getVoteCount("Bob") + " votes");
        System.out.println("Carol: " + voting.getVoteCount("Carol") + " votes");
        System.out.println("David: " + voting.getVoteCount("David") + " votes");
    }
}
