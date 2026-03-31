import java.util.ArrayList;
import java.util.List;

abstract class WarehouseItem {
    private String name;

    public WarehouseItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String category();
}

class Electronics extends WarehouseItem {
    public Electronics(String name) {
        super(name);
    }

    public String category() {
        return "Electronics";
    }
}

class Groceries extends WarehouseItem {
    public Groceries(String name) {
        super(name);
    }

    public String category() {
        return "Groceries";
    }
}

class Furniture extends WarehouseItem {
    public Furniture(String name) {
        super(name);
    }

    public String category() {
        return "Furniture";
    }
}

class Storage<T extends WarehouseItem> {
    private List<T> data = new ArrayList<>();

    public void store(T item) {
        data.add(item);
    }

    public List<T> fetchAll() {
        return data;
    }
}

class DisplayHelper {
    public static void show(List<? extends WarehouseItem> list) {
        for (WarehouseItem item : list) {
            System.out.println(item.getName() + " - " + item.category());
        }
    }
}

public class SmartWareHouse {
    public static void main(String[] args) {

        Storage<Electronics> eStore = new Storage<>();
        eStore.store(new Electronics("Laptop"));
        eStore.store(new Electronics("Camera"));

        Storage<Groceries> gStore = new Storage<>();
        gStore.store(new Groceries("Sugar"));
        gStore.store(new Groceries("Oil"));

        Storage<Furniture> fStore = new Storage<>();
        fStore.store(new Furniture("Bed"));
        fStore.store(new Furniture("Sofa"));

        DisplayHelper.show(eStore.fetchAll());
        DisplayHelper.show(gStore.fetchAll());
        DisplayHelper.show(fStore.fetchAll());
    }
}