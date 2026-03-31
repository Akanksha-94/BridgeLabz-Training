import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Order{
    private String customer;
    private double amount;
    // private String date;
    public Order(String customer,double amount){
        this.customer=customer;
        this.amount=amount;
    }
    public String getCustomer(){
        return this.customer;
    }
    public double getAmount(){
        return this.amount;
    }
}
public class OrderRevenue {
    public static void main(String[] args) {
        
        List<Order> orders =Arrays.asList(
            new Order("Alice",250.0),
            new Order("Bob",150.0),
            new Order("Alice",300.0),
            new Order("Bob",200.0)
        );
        Map<String,Double> revenueByCustomer=
        orders.stream()
        .collect(Collectors.groupingBy(
            Order::getCustomer,
            Collectors.summingDouble(Order::getAmount)
        ));
        System.out.println(revenueByCustomer);
    }
}
