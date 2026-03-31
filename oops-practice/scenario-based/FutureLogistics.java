import java.util.Scanner;

abstract class GoodsTransport {
    protected String transportId;
    protected String transportDate;
    protected int transportRating;

    public GoodsTransport(String transportId, String transportDate, int transportRating) {
        this.transportId = transportId;
        this.transportDate = transportDate;
        this.transportRating = transportRating;
    }

    public String getTransportId() { return transportId; }
    public String getTransportDate() { return transportDate; }
    public int getTransportRating() { return transportRating; }

    public abstract String vehicleSelection();
    public abstract float calculateTotalCharge();
}

class BrickTransport extends GoodsTransport {
    private float brickSize;
    private int brickQuantity;
    private float brickPrice;

    public BrickTransport(String id, String date, int rating, float size, int quantity, float price) {
        super(id, date, rating);
        this.brickSize = size;
        this.brickQuantity = quantity;
        this.brickPrice = price;
    }

    public int getBrickQuantity() { return brickQuantity; }
    public float getBrickPrice() { return brickPrice; }

    public String vehicleSelection() {
        if (brickQuantity < 300) return "Truck";
        else if (brickQuantity <= 500) return "Lorry";
        else return "MonsterLorry";
    }

    public float calculateTotalCharge() {
        float price = brickPrice * brickQuantity;
        float tax = price * 0.3f;

        float discountRate = 0;
        if (transportRating == 5) discountRate = 0.20f;
        else if (transportRating >= 3) discountRate = 0.10f;

        float discount = price * discountRate;

        float vehicleCost;
        String vehicle = vehicleSelection();
        if (vehicle.equalsIgnoreCase("Truck")) vehicleCost = 1000;
        else if (vehicle.equalsIgnoreCase("Lorry")) vehicleCost = 1700;
        else vehicleCost = 3000;

        return (price + vehicleCost + tax) - discount;
    }
}

class TimberTransport extends GoodsTransport {
    private float timberLength;
    private float timberRadius;
    private String timberType;
    private float timberPrice;

    public TimberTransport(String id, String date, int rating, float length, float radius, String type, float price) {
        super(id, date, rating);
        this.timberLength = length;
        this.timberRadius = radius;
        this.timberType = type;
        this.timberPrice = price;
    }

    public String getTimberType() { return timberType; }
    public float getTimberPrice() { return timberPrice; }

    public String vehicleSelection() {
        float area = 2 * 3.147f * timberRadius * timberLength;

        if (area < 250) return "Truck";
        else if (area <= 400) return "Lorry";
        else return "MonsterLorry";
    }

    public float calculateTotalCharge() {
        float volume = 3.147f * timberRadius * timberRadius * timberLength;

        float typeRate = timberType.equalsIgnoreCase("Premium") ? 0.25f : 0.15f;
        float price = volume * timberPrice * typeRate;

        float tax = price * 0.3f;

        float discountRate = 0;
        if (transportRating == 5) discountRate = 0.20f;
        else if (transportRating >= 3) discountRate = 0.10f;

        float discount = price * discountRate;

        float vehicleCost;
        String vehicle = vehicleSelection();
        if (vehicle.equalsIgnoreCase("Truck")) vehicleCost = 1000;
        else if (vehicle.equalsIgnoreCase("Lorry")) vehicleCost = 1700;
        else vehicleCost = 3000;

        return (price + vehicleCost + tax) - discount;
    }
}

class Utility {

    public GoodsTransport parseDetails(String input) {
        String[] parts = input.split(":");

        String id = parts[0];
        String date = parts[1];
        int rating = Integer.parseInt(parts[2]);
        String type = parts[3];

        if (type.equalsIgnoreCase("BrickTransport")) {
            float size = Float.parseFloat(parts[4]);
            int qty = Integer.parseInt(parts[5]);
            float price = Float.parseFloat(parts[6]);
            return new BrickTransport(id, date, rating, size, qty, price);
        } else {
            float length = Float.parseFloat(parts[4]);
            float radius = Float.parseFloat(parts[5]);
            String timberType = parts[6];
            float timberPrice = Float.parseFloat(parts[7]);
            return new TimberTransport(id, date, rating, length, radius, timberType, timberPrice);
        }
    }

    public boolean validateTransportId(String id) {
        if (!id.matches("^RTS\\d{3}[A-Z]$")) {
            System.out.println("Transport id " + id + " is invalid");
            return false;
        }
        return true;
    }

    public String findObjectType(GoodsTransport obj) {
        if (obj instanceof TimberTransport) return "TimberTransport";
        return "BrickTransport";
    }
}

public class FutureLogistics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Utility util = new Utility();

        System.out.println("Enter the Goods Transport details");
        String input = sc.nextLine();

        GoodsTransport gt = util.parseDetails(input);

        if (!util.validateTransportId(gt.getTransportId())) {
            System.out.println("Please provide a valid record");
            return;
        }

        System.out.println("Transporter id : " + gt.getTransportId());
        System.out.println("Date of transport : " + gt.getTransportDate());
        System.out.println("Rating of the transport : " + gt.getTransportRating());

        if (gt instanceof BrickTransport) {
            BrickTransport bt = (BrickTransport) gt;
            System.out.println("Quantity of bricks : " + bt.getBrickQuantity());
            System.out.println("Brick price : " + bt.getBrickPrice());
            System.out.println("Vehicle for transport : " + bt.vehicleSelection());
            System.out.println("Total charge : " + bt.calculateTotalCharge());
        } else {
            TimberTransport tt = (TimberTransport) gt;
            System.out.println("Type of the timber : " + tt.getTimberType());
            System.out.println("Timber price per kilo : " + tt.getTimberPrice());
            System.out.println("Vehicle for transport : " + tt.vehicleSelection());
            System.out.println("Total charge : " + tt.calculateTotalCharge());
        }
    }
}