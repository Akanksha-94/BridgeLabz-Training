import java.util.*;

class SensorReading {
    String sensorId;
    double value;
    String unit;
    
    SensorReading(String sensorId, double value, String unit) {
        this.sensorId = sensorId;
        this.value = value;
        this.unit = unit;
    }
    
    @Override
    public String toString() {
        return sensorId + ": " + value + " " + unit;
    }
}

public class IoTSensorReadings {
    public static void main(String[] args) {
        List<SensorReading> readings = Arrays.asList(
            new SensorReading("TEMP_01", 25.5, "°C"),
            new SensorReading("TEMP_02", 35.2, "°C"),
            new SensorReading("HUMIDITY_01", 65.0, "%"),
            new SensorReading("TEMP_03", 40.8, "°C"),
            new SensorReading("PRESSURE_01", 1013.2, "hPa"),
            new SensorReading("TEMP_04", 28.3, "°C")
        );
        
        double temperatureThreshold = 30.0;
        
        System.out.println("Temperature readings above " + temperatureThreshold + "°C:");
        readings.stream()
            .filter(reading -> reading.sensorId.startsWith("TEMP") && reading.value > temperatureThreshold)
            .forEach(reading -> System.out.println("ALERT: " + reading));
    }
}