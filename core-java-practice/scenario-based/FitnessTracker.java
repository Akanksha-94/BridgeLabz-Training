public class FitnessTracker {
    public static void main(String[] args) {
        int[] pushUpsWeek  = {20, 30, 0, 25, 35, 0, 40};
        
        int total  = 0;
        int daysCounted  = 0;

        for (int count : pushUpsWeek)  {
            if (count == 0)  { 

                continue;       // skip rest day
            }
            total +=  count;
            daysCounted++;
        }

        double avg= (daysCounted > 0) ? (double) total / daysCounted : 0;

        System.out.println("Total Push-ups : " + total);
        System.out.println("Average Push-ups (Workout Days): " + avg);
    }
}
