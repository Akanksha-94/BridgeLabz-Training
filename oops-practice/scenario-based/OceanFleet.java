import java.util.*;
import java.util.stream.Collectors;

class Vessel {
    private String vesselId;
    private String vesselName;
    private double averageSpeed;
    private String vesselType;
    
    public Vessel() {}
    
    public Vessel(String vesselId, String vesselName, double averageSpeed, String vesselType) {
        this.vesselId = vesselId;
        this.vesselName = vesselName;
        this.averageSpeed = averageSpeed;
        this.vesselType = vesselType;
    }
    
    public String getVesselId() { return vesselId; }
    public void setVesselId(String vesselId) { this.vesselId = vesselId; }
    
    public String getVesselName() { return vesselName; }
    public void setVesselName(String vesselName) { this.vesselName = vesselName; }
    
    public double getAverageSpeed() { return averageSpeed; }
    public void setAverageSpeed(double averageSpeed) { this.averageSpeed = averageSpeed; }
    
    public String getVesselType() { return vesselType; }
    public void setVesselType(String vesselType) { this.vesselType = vesselType; }
}

class VesselUtil {
    private List<Vessel> vesselList;
    
    public VesselUtil() {
        this.vesselList = new ArrayList<>();
    }
    
    public List<Vessel> getVesselList() { return vesselList; }
    public void setVesselList(List<Vessel> vesselList) { this.vesselList = vesselList; }
    
    public void addVesselPerformance(Vessel vessel) {
        vesselList.add(vessel);
    }
    
    public Vessel getVesselById(String vesselId) {
        return vesselList.stream()
                .filter(v -> v.getVesselId().equals(vesselId))
                .findFirst()
                .orElse(null);
    }
    
    public List<Vessel> getHighPerformanceVessels() {
        double maxSpeed = vesselList.stream()
                .mapToDouble(Vessel::getAverageSpeed)
                .max()
                .orElse(0.0);
        
        return vesselList.stream()
                .filter(v -> v.getAverageSpeed() == maxSpeed)
                .collect(Collectors.toList());
    }
}

public class OceanFleet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VesselUtil vesselUtil = new VesselUtil();
        
        System.out.println("Enter the number of vessels to be added");
        int n = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("Enter vessel details");
        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] parts = input.split(":");
            Vessel vessel = new Vessel(parts[0], parts[1], Double.parseDouble(parts[2]), parts[3]);
            vesselUtil.addVesselPerformance(vessel);
        }
        
        System.out.println("Enter the Vessel Id to check speed");
        String searchId = scanner.nextLine();
        
        Vessel foundVessel = vesselUtil.getVesselById(searchId);
        if (foundVessel != null) {
            System.out.println(foundVessel.getVesselId() + " | " + foundVessel.getVesselName() + 
                             " | " + foundVessel.getVesselType() + " | " + foundVessel.getAverageSpeed() + " knots");
        } else {
            System.out.println("Vessel Id " + searchId + " not found");
        }
        
        System.out.println("High performance vessels are");
        List<Vessel> highPerformers = vesselUtil.getHighPerformanceVessels();
        for (Vessel vessel : highPerformers) {
            System.out.println(vessel.getVesselId() + " | " + vessel.getVesselName() + 
                             " | " + vessel.getVesselType() + " | " + vessel.getAverageSpeed() + " knots");
        }
        
        scanner.close();
    }
}