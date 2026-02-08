import java.util.*;

class Stock {
    String symbol;
    double price;
    
    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return symbol + ": $" + String.format("%.2f", price);
    }
}

public class StockPriceLogger {
    public static void main(String[] args) {
        List<Stock> stockPrices = Arrays.asList(
            new Stock("AAPL", 150.25),
            new Stock("GOOGL", 2800.50),
            new Stock("MSFT", 300.75),
            new Stock("TSLA", 850.30),
            new Stock("AMZN", 3200.80)
        );
        
        System.out.println("Live Stock Price Feed:");
        stockPrices.forEach(stock -> 
            System.out.println("UPDATE: " + stock));
    }
}