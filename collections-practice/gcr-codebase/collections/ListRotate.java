import java.util.*;

public class ListRotate {

    public static void main(String[] args) {

        ArrayList<Integer> data = new ArrayList<>();
        data.add(10);
        data.add(20);
        data.add(30);
        data.add(40);
        data.add(50);

        int steps = 2;  

        for (int i = 0; i < steps; i++) {
            int firstValue = data.get(0);  
            data.remove(0);               
            data.add(firstValue);          
        }

        System.out.println("Rotated List: " + data);
    }
}
