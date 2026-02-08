public class DownloadManager {
    
    static class FileDownloaderThread extends Thread {
        private String fileName;
        
        public FileDownloaderThread(String fileName) {
            this.fileName = fileName;
        }
        
        @Override
        public void run() {
            for (int progress = 0; progress <= 100; progress += 25) {
                System.out.println("[" + Thread.currentThread().getName() + "] Downloading " + fileName + ": " + progress + "%");
                try {
                    Thread.sleep((long)(Math.random() * 500 + 200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    static class FileDownloaderRunnable implements Runnable {
        private String fileName;
        
        public FileDownloaderRunnable(String fileName) {
            this.fileName = fileName;
        }
        
        @Override
        public void run() {
            for (int progress = 0; progress <= 100; progress += 25) {
                System.out.println("[" + Thread.currentThread().getName() + "] Downloading " + fileName + ": " + progress + "%");
                try {
                    Thread.sleep((long)(Math.random() * 500 + 200));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Approach 1: Using Thread Class ===\n");
        
        FileDownloaderThread t1 = new FileDownloaderThread("Document.pdf");
        FileDownloaderThread t2 = new FileDownloaderThread("Image.jpg");
        FileDownloaderThread t3 = new FileDownloaderThread("Video.mp4");
        
        t1.start();
        t2.start();
        t3.start();
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nAll downloads complete!\n");
        
        System.out.println("=== Approach 2: Using Runnable Interface ===\n");
        
        Thread r1 = new Thread(new FileDownloaderRunnable("Document.pdf"), "Thread-1");
        Thread r2 = new Thread(new FileDownloaderRunnable("Image.jpg"), "Thread-2");
        Thread r3 = new Thread(new FileDownloaderRunnable("Video.mp4"), "Thread-3");
        
        r1.start();
        r2.start();
        r3.start();
        
        try {
            r1.join();
            r2.join();
            r3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nAll downloads complete!");
    }
}