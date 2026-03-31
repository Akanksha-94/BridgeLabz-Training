public class BackgroundJobExecution {
    public static void main(String[] args) {
        Runnable backgroundTask=()->{
            System.out.println("BG job started");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("BG job completed");;
        };
        // run task async
        Thread backgroundThread=new Thread(backgroundTask);
        backgroundThread.start();
        System.out.println("Main thread continues with other work...");

    }
}
