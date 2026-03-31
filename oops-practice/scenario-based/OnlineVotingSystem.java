import java.util.*;

/**
 * Online Voting System
 * Real Scenario: Users cast votes securely during an election.
 * 
 * Concepts Used:
 * - OOP: Voter, Candidate, Vote
 * - Abstraction: ElectionService
 * - Exception Handling: DuplicateVoteException, InvalidVoteException
 */

// Custom Exceptions
class DuplicateVoteException extends Exception {
  public DuplicateVoteException(String message) {
    super(message);
  }
}

class InvalidVoteException extends Exception {
  public InvalidVoteException(String message) {
    super(message);
  }
}

// Voter class
class Voter {
  private String voterId;
  private String name;
  private String email;
  private boolean hasVoted;
  private String votedFor; // candidate ID

  public Voter(String voterId, String name, String email) {
    this.voterId = voterId;
    this.name = name;
    this.email = email;
    this.hasVoted = false;
    this.votedFor = null;
  }

  public String getVoterId() {
    return voterId;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public boolean hasVoted() {
    return hasVoted;
  }

  public String getVotedFor() {
    return votedFor;
  }

  public void markAsVoted(String candidateId) throws DuplicateVoteException {
    if (hasVoted) {
      throw new DuplicateVoteException("Voter " + voterId + " has already voted!");
    }
    this.hasVoted = true;
    this.votedFor = candidateId;
  }

  @Override
  public String toString() {
    return String.format("Voter ID: %s | Name: %-25s | Email: %-25s | Voted: %s",
        voterId, name, email, (hasVoted ? "Yes (" + votedFor + ")" : "No"));
  }
}

// Candidate class
class Candidate {
  private String candidateId;
  private String name;
  private String party;
  private String manifesto;
  private int voteCount;

  public Candidate(String candidateId, String name, String party, String manifesto) {
    this.candidateId = candidateId;
    this.name = name;
    this.party = party;
    this.manifesto = manifesto;
    this.voteCount = 0;
  }

  public String getCandidateId() {
    return candidateId;
  }

  public String getName() {
    return name;
  }

  public String getParty() {
    return party;
  }

  public int getVoteCount() {
    return voteCount;
  }

  public void receiveVote() {
    voteCount++;
  }

  @Override
  public String toString() {
    return String.format("Candidate ID: %s | Name: %-25s | Party: %-20s | Votes: %d",
        candidateId, name, party, voteCount);
  }

  public String getDetails() {
    return String.format(
        "Candidate ID: %s\n" +
            "Name: %s\n" +
            "Party: %s\n" +
            "Manifesto: %s\n" +
            "Votes Received: %d",
        candidateId, name, party, manifesto, voteCount);
  }
}

// Vote class (for record keeping)
class Vote {
  private String voteId;
  private String voterId;
  private String candidateId;
  private long timestamp;

  public Vote(String voterId, String candidateId) {
    this.voteId = "VOTE" + System.currentTimeMillis();
    this.voterId = voterId;
    this.candidateId = candidateId;
    this.timestamp = System.currentTimeMillis();
  }

  public String getVoteId() {
    return voteId;
  }

  public String getVoterId() {
    return voterId;
  }

  public String getCandidateId() {
    return candidateId;
  }

  public long getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return String.format("Vote ID: %s | Voter: %s | Candidate: %s | Timestamp: %d",
        voteId, voterId, candidateId, timestamp);
  }
}

// Election Service Interface
interface ElectionService {
  void registerVoter(Voter voter);

  void registerCandidate(Candidate candidate);

  void castVote(String voterId, String candidateId) throws DuplicateVoteException, InvalidVoteException;

  int getVoteCount(String candidateId);

  Candidate getWinner();

  double getVotePercentage(String candidateId);
}

// Election Service Implementation
class ElectionServiceImpl implements ElectionService {
  private Map<String, Voter> voters;
  private Map<String, Candidate> candidates;
  private List<Vote> voteRecords;
  private boolean electionActive;

  public ElectionServiceImpl() {
    this.voters = new HashMap<>();
    this.candidates = new HashMap<>();
    this.voteRecords = new ArrayList<>();
    this.electionActive = true;
  }

  @Override
  public void registerVoter(Voter voter) {
    voters.put(voter.getVoterId(), voter);
    System.out.println("✓ Voter registered: " + voter.getName());
  }

  @Override
  public void registerCandidate(Candidate candidate) {
    candidates.put(candidate.getCandidateId(), candidate);
    System.out.println("✓ Candidate registered: " + candidate.getName() +
        " (" + candidate.getParty() + ")");
  }

  @Override
  public void castVote(String voterId, String candidateId)
      throws DuplicateVoteException, InvalidVoteException {
    if (!electionActive) {
      throw new InvalidVoteException("Election is closed");
    }

    Voter voter = voters.get(voterId);
    Candidate candidate = candidates.get(candidateId);

    if (voter == null) {
      throw new InvalidVoteException("Voter not found");
    }
    if (candidate == null) {
      throw new InvalidVoteException("Candidate not found");
    }

    voter.markAsVoted(candidateId);
    candidate.receiveVote();
    voteRecords.add(new Vote(voterId, candidateId));

    System.out.println("✓ Vote cast: " + voter.getName() + " → " + candidate.getName());
  }

  @Override
  public int getVoteCount(String candidateId) {
    Candidate candidate = candidates.get(candidateId);
    return candidate != null ? candidate.getVoteCount() : 0;
  }

  @Override
  public Candidate getWinner() {
    Candidate winner = null;
    int maxVotes = 0;

    for (Candidate candidate : candidates.values()) {
      if (candidate.getVoteCount() > maxVotes) {
        maxVotes = candidate.getVoteCount();
        winner = candidate;
      }
    }

    return winner;
  }

  @Override
  public double getVotePercentage(String candidateId) {
    int totalVotes = voteRecords.size();
    if (totalVotes == 0)
      return 0.0;

    int candidateVotes = getVoteCount(candidateId);
    return (candidateVotes * 100.0) / totalVotes;
  }

  public void closeElection() {
    electionActive = false;
    System.out.println("✓ Election closed");
  }

  public int getTotalVotes() {
    return voteRecords.size();
  }

  public Map<String, Candidate> getCandidates() {
    return candidates;
  }

  public Map<String, Voter> getVoters() {
    return voters;
  }

  public List<Vote> getVoteRecords() {
    return voteRecords;
  }
}

public class OnlineVotingSystem {
  public static void main(String[] args) {
    ElectionService electionService = new ElectionServiceImpl();

    System.out.println("╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║          ONLINE VOTING SYSTEM - DEMONSTRATION                    ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

    // PHASE 1: Register candidates
    System.out.println("--- PHASE 1: Register Candidates ---");
    electionService.registerCandidate(
        new Candidate("C001", "Alice Brown", "Democratic Party", "Focus on education and healthcare"));
    electionService.registerCandidate(
        new Candidate("C002", "Bob Johnson", "Republican Party", "Economic growth and jobs"));
    electionService.registerCandidate(
        new Candidate("C003", "Carol Davis", "Independent", "Environmental protection"));

    // PHASE 2: Register voters
    System.out.println("\n--- PHASE 2: Register Voters ---");
    electionService.registerVoter(new Voter("V001", "John Smith", "john@email.com"));
    electionService.registerVoter(new Voter("V002", "Jane Doe", "jane@email.com"));
    electionService.registerVoter(new Voter("V003", "Mike Wilson", "mike@email.com"));
    electionService.registerVoter(new Voter("V004", "Sarah Lee", "sarah@email.com"));
    electionService.registerVoter(new Voter("V005", "Tom Harris", "tom@email.com"));

    // PHASE 3: Cast votes
    System.out.println("\n--- PHASE 3: Cast Votes ---");
    try {
      electionService.castVote("V001", "C001");
      electionService.castVote("V002", "C002");
      electionService.castVote("V003", "C001");
      electionService.castVote("V004", "C003");
      electionService.castVote("V005", "C001");
    } catch (DuplicateVoteException | InvalidVoteException e) {
      System.out.println("✗ Error: " + e.getMessage());
    }

    // PHASE 4: Test duplicate vote prevention
    System.out.println("\n--- PHASE 4: Test Duplicate Vote Prevention ---");
    try {
      electionService.castVote("V001", "C002");
    } catch (DuplicateVoteException | InvalidVoteException e) {
      System.out.println("✗ Error caught (expected): " + e.getMessage());
    }

    // PHASE 5: Display voting results
    System.out.println("\n--- PHASE 5: Voting Results ---");
    ElectionServiceImpl serviceImpl = (ElectionServiceImpl) electionService;

    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    VOTING RESULTS                                  ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Candidate candidate : serviceImpl.getCandidates().values()) {
      double percentage = electionService.getVotePercentage(candidate.getCandidateId());
      System.out.println(candidate + " | Percentage: " + String.format("%.2f", percentage) + "%");
    }

    System.out.println("Total Votes Cast: " + serviceImpl.getTotalVotes());
    System.out.println("╚════════════════════════════════════════════════════════════════════╝");

    // PHASE 6: Declare winner
    System.out.println("\n--- PHASE 6: Declare Winner ---");
    Candidate winner = electionService.getWinner();
    if (winner != null) {
      System.out.println("\n🎉 ELECTION WINNER: " + winner.getName() +
          " (" + winner.getParty() + ") with " +
          winner.getVoteCount() + " votes");
    }

    // PHASE 7: Display voter information
    System.out.println("\n--- PHASE 7: Voter Information ---");
    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    VOTER INFORMATION                               ║");
    System.out.println("╠════════════════════════════════════════════════════════════════════╣");

    for (Voter voter : serviceImpl.getVoters().values()) {
      System.out.println(voter);
    }

    System.out.println("╚════════════════════════════════════════════════════════════════════╝");

    // PHASE 8: Close election
    System.out.println("\n--- PHASE 8: Close Election ---");
    serviceImpl.closeElection();

    try {
      electionService.castVote("V005", "C002");
    } catch (DuplicateVoteException | InvalidVoteException e) {
      System.out.println("✗ Error caught (expected): " + e.getMessage());
    }

    System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
    System.out.println("║                    SIMULATION COMPLETED                           ║");
    System.out.println("╚════════════════════════════════════════════════════════════════════╝");
  }
}
