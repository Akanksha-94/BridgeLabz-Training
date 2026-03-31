interface VehicleRent{
    void rent();
    void returnVehicle();
}
class Car implements VehicleRent{
    public void rent(){
        System.out.println("Car is rented");
    }
    public void returnVehicle(){
        System.out.println("Car is returned");
    }
}
class Bike implements VehicleRent{
    public void rent(){ 
        System.out.println("Brij ghumao");
    }
    public void returnVehicle(){
        System.out.println("Bike is returned");
    }
}
public class MultiVehicleRental {
    public static void main(String[] args) {
        VehicleRent car=new Car();
        car.rent();
        car.returnVehicle();
        VehicleRent bike = new Bike();
        bike.rent();
        bike.returnVehicle();
        
    }
}
