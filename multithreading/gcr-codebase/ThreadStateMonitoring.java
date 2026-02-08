import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ThreadStateMonitoring {
    
    static class TaskRunner extends Thread {
        public TaskRunner(String name) {
            super(name);
        }
        
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                
                long sum = 0;
                for (int i = 0; i < 1000000; i++) {
                    sum += i;
                }
                
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class StateMonitor extends Thread {
        private List<Thread> threadsToMonitor;
        private Map<String, List<Thread.State>> stateHistory;
        
        public StateMonitor(List<Thread> threads) {
            this.threadsToMonitor = threads;
            this.stateHistory = new HashMap<>();
            for (Thread t : threads) {
                stateHistory.put(t.getName(), new ArrayList<>());
            }
        }
        
        @Override
        public void run() {
            boolean allTerminated = false;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            
            while (!allTerminated) {
                allTerminated = true;
                
                for (Thread thread : threadsToMonitor) {
                    Thread.State currentState = thread.getState();
                    List<Thread.State> history = stateHistory.get(thread.getName());
                    
                    if (history.isEmpty() || history.get(history.size() - 1) != currentState) {
                        history.add(currentState);
                        String timestamp = LocalTime.now().format(formatter);
                        System.out.println("[Monitor] " + thread.getName() + " is in " + currentState + " state at " + timestamp);
                    }
                    
                    if (currentState != Thread.State.TERMINATED) {
                        allTerminated = false;
                    }
                }
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            System.out.println("\n=== Summary ===");
            for (Map.Entry<String, List<Thread.State>> entry : stateHistory.entrySet()) {
                System.out.println("Summary: " + entry.getKey() + " went through " + entry.getValue().size() + " states");
                System.out.println("  States: " + entry.getValue());
            }
        }
    }
    
    public static void main(String[] args) {
        TaskRunner task1 = new TaskRunner("Task-1");
        TaskRunner task2 = new TaskRunner("Task-2");
        
        List<Thread> tasks = Arrays.asList(task1, task2);
        StateMonitor monitor = new StateMonitor(tasks);
        
        System.out.println("Starting Thread State Monitoring System\n");
        
        monitor.start();
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        task1.start();
        task2.start();
        
        try {
            monitor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\nMonitoring complete!");
    }
}