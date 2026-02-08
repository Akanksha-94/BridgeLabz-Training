interface DeviceControl{
        void turnOn();
        void turnOff();
}
class Light implements DeviceControl{
    public void turnOn(){
        System.out.println("Light is turned on");
    }
    public void turnOff(){
        System.out.println("Light is turned off");
    }
}
class AC implements DeviceControl{
    public void turnOn(){
        System.out.println("AC is turned on");
    }
    public void turnOff(){
        System.out.println("AC is turned off");
    }
}
class Fan implements DeviceControl{
    public void turnOn(){
        System.out.println("Fan is turned on");
    }
    public void turnOff(){
        System.out.println("Fan is turned off");
    }
}
class TV implements DeviceControl{
    public void turnOn(){
        System.out.println("TV is turned on");
    }
    public void turnOff(){
        System.out.println("TV is turned off");
    }
}
public class SmartDeviceControl {
    public static void main(String[] args) {
        // DeviceControl light=new Light();

        DeviceControl light=new Light();
        DeviceControl ac=new AC();
        DeviceControl fan=new Fan();
        DeviceControl tv=new TV();
        light.turnOn();
        ac.turnOn();
        tv.turnOn();
        light.turnOff();
        ac.turnOff();
        tv.turnOff();     
    }
}
