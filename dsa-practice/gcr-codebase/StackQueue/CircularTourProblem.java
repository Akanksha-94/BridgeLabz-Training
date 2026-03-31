import java.util.LinkedList;
import java.util.Queue;

public class CircularTourProblem {
    
    static class PetrolPump {
        int petrol;
        int distance;
        
        PetrolPump(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }
    
    public static int findStartingPoint(PetrolPump[] pumps) {
        Queue<Integer> queue = new LinkedList<>();
        int totalPetrol = 0;
        int totalDistance = 0;
        int startPoint = -1;
        
        for (int i = 0; i < pumps.length; i++) {
            totalPetrol += pumps[i].petrol;
            totalDistance += pumps[i].distance;
            queue.add(i);
        }
        
        if (totalPetrol < totalDistance) {
            return -1;
        }
        
        int currentPetrol = 0;
        while (!queue.isEmpty()) {
            int index = queue.poll();
            currentPetrol += pumps[index].petrol - pumps[index].distance;
            
            if (currentPetrol < 0) {
                currentPetrol = 0;
                startPoint = -1;
            } else {
                if (startPoint == -1) {
                    startPoint = index;
                }
            }
        }
        
        return startPoint;
    }
    
    public static void main(String[] args) {
        PetrolPump[] pumps = {
            new PetrolPump(4, 6),
            new PetrolPump(6, 5),
            new PetrolPump(7, 3),
            new PetrolPump(4, 5)
        };
        
        int startingPoint = findStartingPoint(pumps);
        
        System.out.println("========== Circular Tour Problem ==========");
        System.out.println("Petrol Pumps (Petrol, Distance to Next):");
        for (int i = 0; i < pumps.length; i++) {
            System.out.println("Pump " + (i + 1) + ": Petrol=" + pumps[i].petrol + 
                             ", Distance=" + pumps[i].distance);
        }
        
        if (startingPoint == -1) {
            System.out.println("\nNo valid starting point to complete the tour!");
        } else {
            System.out.println("\nStarting Point: Pump " + (startingPoint + 1));
            System.out.println("You can complete the circular tour starting from Pump " + 
                             (startingPoint + 1));
        }
    }
}