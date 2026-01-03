//Online Retail Order Management

class Order {
    int orderId;
    String orderDate;

    String getOrderStatus() {
        return "Order Placed";
    }
}

class ShippedOrder extends Order {
    String trackingNumber;

    @Override
    String getOrderStatus() {
        return "Order Shipped";
    }
}

class DeliveredOrder extends ShippedOrder {
    String deliveryDate;

    @Override
    String getOrderStatus() {
        return "Order Delivered";
    }
}

public class OrderSystem {
    public static void main(String[] args) {
        Order order = new DeliveredOrder();
        System.out.println(order.getOrderStatus());
    }
}
