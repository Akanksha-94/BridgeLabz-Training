import java.util.*;

public class UniqueElements {

    public static void main(String[] args) {

        List<Integer> inputData = Arrays.asList(3, 1, 2, 2, 3, 4);
        List<Integer> cleanList = new ArrayList<>();

        for (int index = 0; index < inputData.size(); index++) {

            int number = inputData.get(index);
            boolean exists = false;

            for (int j = 0; j < cleanList.size(); j++) {
                if (cleanList.get(j) == number) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                cleanList.add(number);
            }
        }

        System.out.println("Final List: " + cleanList);
    }
}
