public class TemperatureAnalyzer {
    //Method to analyze temperature data
    public static void analyzeTemperature(float[][] temperatures) {
        float hottestTemp = temperatures[0][0];
        float coldestTemp = temperatures[0][0];
        int hottestDay = 0;
        int coldestDay = 0;
        //Calculate average temperature for each day
        for (int day = 0; day < temperatures.length; day++) {
            float sum = 0;
            for (int hour = 0; hour < temperatures[day].length; hour++) {
                float temp = temperatures[day][hour];
                sum += temp;
                if (temp > hottestTemp) {
                    hottestTemp = temp;
                    hottestDay = day;
                }
                if (temp < coldestTemp) {
                    coldestTemp = temp;
                    coldestDay = day;
                }
            }
            float average = sum / temperatures[day].length;
            System.out.println("AverageTemperatureOfDay" + (day + 1) + ":" + average);
        }
        System.out.println("\nHottestDay:Day" + (hottestDay + 1) + " Temperature:" + hottestTemp);
        System.out.println("ColdestDay:Day" + (coldestDay + 1) + " Temperature:" + coldestTemp);
    }
    public static void main(String[] args) {
        float[][] weeklyTemperatures = new float[7][24];
        //Sample data
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                weeklyTemperatures[i][j] = 20 + (float)(Math.random() * 15);
            }
        }
        analyzeTemperature(weeklyTemperatures);
    }
}