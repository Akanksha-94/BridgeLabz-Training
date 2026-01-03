class Device {
    String deviceId;
    String status;

    void displayStatus() {
        System.out.println("Device ID: " + deviceId);
        System.out.println("Status: " + status);
    }
}

class Thermostat extends Device {
    int temperatureSetting;

    @Override
    void displayStatus() {
        super.displayStatus();
        System.out.println("Temperature: " + temperatureSetting + "°C");
        System.out.println("------------------");
    }
}

public class SmartHome {
    public static void main(String[] args) {
        Thermostat t = new Thermostat();
        t.deviceId = "TH101";
        t.status = "ON";
        t.temperatureSetting = 24;

        t.displayStatus();
    }
}
