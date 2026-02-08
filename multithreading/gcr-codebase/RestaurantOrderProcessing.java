public class RestaurantOrderProcessing {
    
    static class Chef extends Thread {
        private String dishName;
        private double cookingTimeSeconds;
        
        public Chef(String name, String dishName, double cookingTimeSeconds) {
            super(name);
            this.dishName = dishName;
            this.cookingTimeSeconds = cookingTimeSeconds;
        }
        
        @Override
        public void run() {
            System.out.println(getName() + " started preparing " + dishName);
            
            int[] progressSteps = {25, 50, 75, 100};
            int sleepTime = (int)((cookingTimeSeconds * 1000) / 4);
            
            for (int progress : progressSteps) {
                try {
                    Thread.sleep(sleepTime);
                    System.out.println(getName() + " preparing " + dishName + ": " + progress + "% complete");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Restaurant kitchen opening...\n");
        
        Chef chef1 = new Chef("Chef-1", "Pizza", 3);
        Chef chef2 = new Chef("Chef-2", "Pasta", 2);
        Chef chef3 = new Chef("Chef-3", "Salad", 1);
        Chef chef4 = new Chef("Chef-4", "Burger", 2.5);
        
        chef1.start();
        chef2.start();
        chef3.start();
        chef4.start();
        
        try {
            chef1.join();
            chef2.join();
            chef3.join();
            chef4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nKitchen closed - All orders completed");
    }
}