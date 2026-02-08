import java.util.function.*;
public class TempAlertSystem {
    public static void main(String[] args) {
        double thresholdTemp=30.0;
        Predicate<Double> isHighTemp=
             temp->temp>thresholdTemp;
        double[] temp={28.5,31.5,55.0};
        for(double t:temp){
            if(isHighTemp.test(t)){
                System.out.println("Temperature is high: "+t);
            }
            else{
                System.out.println("Temperature is normal: "+t);
            }

        }
    }
}
