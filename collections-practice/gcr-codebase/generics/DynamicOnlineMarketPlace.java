import java.util.ArrayList;
import java.util.List;

abstract class Category {
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class BookCategory extends Category {
    public BookCategory() {
        super("Books");
    }
}

class ClothingCategory extends Category {
    public ClothingCategory() {
        super("Clothing");
    }
}

class GadgetCategory extends Category {
    public GadgetCategory() {
        super("Gadgets");
    }
}

class Product<T extends Category> {
    private String productName;
    private double price;
    private T category;

    public Product(String productName, double price, T category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String details() {
        return productName + " | " + category.getName() + " | " + price;
    }
}

class MarketplaceUtil {
    public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        double discount = product.getPrice() * (percentage / 100);
        product.setPrice(product.getPrice() - discount);
    }
}

public class DynamicOnlineMarketPlace {
    public static void main(String[] args) {

        List<Product<?>> catalog = new ArrayList<>();

        Product<BookCategory> book =
                new Product<>("Java Basics", 500, new BookCategory());

        Product<ClothingCategory> cloth =
                new Product<>("Jacket", 2000, new ClothingCategory());

        Product<GadgetCategory> gadget =
                new Product<>("Smartwatch", 8000, new GadgetCategory());

        catalog.add(book);
        catalog.add(cloth);
        catalog.add(gadget);

        MarketplaceUtil.applyDiscount(book, 10);
        MarketplaceUtil.applyDiscount(cloth, 20);
        MarketplaceUtil.applyDiscount(gadget, 5);

        for (Product<?> product : catalog) {
            System.out.println(product.details());
        }
    }
}