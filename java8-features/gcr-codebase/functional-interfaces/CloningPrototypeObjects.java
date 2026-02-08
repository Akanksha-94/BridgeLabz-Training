public class CloningPrototypeObjects {
    
    static class Product implements Cloneable {
        String id;
        String name;
        double price;
        String category;
        
        Product(String id, String name, double price, String category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.category = category;
        }
        
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
        
        @Override
        public String toString() {
            return String.format("Product{id='%s', name='%s', price=$%.2f, category='%s'}", 
                               id, name, price, category);
        }
    }
    
    static class ProductPrototype {
        public Product createClone(Product prototype) {
            try {
                return (Product) prototype.clone();
            } catch (CloneNotSupportedException e) {
                System.err.println("Clone not supported: " + e.getMessage());
                return null;
            }
        }
    }
    
    public static void main(String[] args) {
        Product originalProduct = new Product("P001", "Laptop", 999.99, "Electronics");
        
        System.out.println("Original: " + originalProduct);
        
        ProductPrototype prototype = new ProductPrototype();
        
        Product clonedProduct1 = prototype.createClone(originalProduct);
        clonedProduct1.id = "P002";
        clonedProduct1.name = "Gaming Laptop";
        clonedProduct1.price = 1499.99;
        
        Product clonedProduct2 = prototype.createClone(originalProduct);
        clonedProduct2.id = "P003";
        clonedProduct2.name = "Business Laptop";
        clonedProduct2.price = 1299.99;
        
        System.out.println("Clone 1: " + clonedProduct1);
        System.out.println("Clone 2: " + clonedProduct2);
        System.out.println("Original (unchanged): " + originalProduct);
    }
}