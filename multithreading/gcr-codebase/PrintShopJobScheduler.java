public class PrintShopJobScheduler {
    
    static class PrintJob implements Runnable {
        private String jobName;
        private int pages;
        private int priority;
        
        public PrintJob(String jobName, int pages, int priority) {
            this.jobName = jobName;
            this.pages = pages;
            this.priority = priority;
        }
        
        @Override
        public void run() {
            String priorityLevel = priority >= 7 ? "High Priority" : priority >= 5 ? "Medium Priority" : "Low Priority";
            
            for (int page = 1; page <= pages; page++) {
                System.out.println("[" + priorityLevel + "] Printing " + jobName + " - Page " + page + " of " + pages);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(jobName + " completed!");
        }
        
        public int getPriority() {
            return priority;
        }
    }
    
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        System.out.println("Starting print jobs...\n");
        
        PrintJob job1 = new PrintJob("Job1", 10, 5);
        PrintJob job2 = new PrintJob("Job2", 5, 8);
        PrintJob job3 = new PrintJob("Job3", 15, 3);
        PrintJob job4 = new PrintJob("Job4", 8, 6);
        PrintJob job5 = new PrintJob("Job5", 12, 7);
        
        Thread t1 = new Thread(job1, "Job1-Thread");
        Thread t2 = new Thread(job2, "Job2-Thread");
        Thread t3 = new Thread(job3, "Job3-Thread");
        Thread t4 = new Thread(job4, "Job4-Thread");
        Thread t5 = new Thread(job5, "Job5-Thread");
        
        t1.setPriority(5);
        t2.setPriority(8);
        t3.setPriority(3);
        t4.setPriority(6);
        t5.setPriority(7);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        System.out.println("\nAll jobs completed in " + totalTime + "ms");
        System.out.println("\nNote: Thread priority affects execution order, but OS scheduler has final control.");
    }
}