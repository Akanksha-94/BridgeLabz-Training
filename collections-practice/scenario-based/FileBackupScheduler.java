import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Custom Exception
class InvalidBackupPathException extends Exception {
    public InvalidBackupPathException(String message) {
        super(message);
    }
}

// BackupTask class implementing Comparable for priority queue
class BackupTask implements Comparable<BackupTask> {
    private int taskId;
    private String folderPath;
    private int priority;
    private LocalDateTime scheduledTime;
    private long estimatedSize;
    private BackupType backupType;

    private static int idCounter = 1;

    public enum BackupType {
        CRITICAL(1, "Critical"),
        IMPORTANT(2, "Important"),
        ROUTINE(3, "Routine");

        private final int level;
        private final String description;

        BackupType(int level, String description) {
            this.level = level;
            this.description = description;
        }

        public int getLevel() {
            return level;
        }

        public String getDescription() {
            return description;
        }
    }

    public BackupTask(String folderPath, int priority, LocalDateTime scheduledTime, BackupType backupType) 
            throws InvalidBackupPathException {
        
        // Validate path
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new InvalidBackupPathException("Invalid backup path: " + folderPath + " (folder does not exist or is not a directory)");
        }

        this.taskId = idCounter++;
        this.folderPath = folderPath;
        this.priority = priority;
        this.scheduledTime = scheduledTime;
        this.backupType = backupType;
        this.estimatedSize = calculateFolderSize(folder);
    }

    // Calculate total folder size in bytes
    private long calculateFolderSize(File folder) {
        long size = 0;
        File[] files = folder.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                } else if (file.isDirectory()) {
                    size += calculateFolderSize(file);
                }
            }
        }
        return size;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public long getEstimatedSize() {
        return estimatedSize;
    }

    public BackupType getBackupType() {
        return backupType;
    }

    public String getFormattedSize() {
        long bytes = estimatedSize;
        if (bytes <= 0) return "0 B";
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(bytes) / Math.log10(1024));
        return String.format("%.2f %s", bytes / Math.pow(1024, digitGroups), units[digitGroups]);
    }

    @Override
    public int compareTo(BackupTask other) {
        // Higher priority (lower number) comes first
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }
        // If same priority, earlier scheduled time comes first
        return this.scheduledTime.compareTo(other.scheduledTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "BackupTask{" +
                "ID=" + taskId +
                ", Type=" + backupType.getDescription() +
                ", Path='" + folderPath + '\'' +
                ", Priority=" + priority +
                ", Scheduled=" + scheduledTime.format(formatter) +
                ", Size=" + getFormattedSize() +
                "}";
    }
}

// File Backup Scheduler
public class FileBackupScheduler {
    private PriorityQueue<BackupTask> backupQueue;
    private List<BackupTask> completedBackups;
    private List<BackupTask> failedBackups;

    public FileBackupScheduler() {
        this.backupQueue = new PriorityQueue<>();
        this.completedBackups = new ArrayList<>();
        this.failedBackups = new ArrayList<>();
    }

    public void scheduleBackup(String folderPath, int priority, LocalDateTime scheduledTime, BackupTask.BackupType backupType) 
            throws InvalidBackupPathException {
        
        try {
            BackupTask task = new BackupTask(folderPath, priority, scheduledTime, backupType);
            backupQueue.add(task);
            System.out.println("✓ Backup scheduled: " + task);
        } catch (InvalidBackupPathException e) {
            System.out.println("✗ Failed to schedule backup: " + e.getMessage());
            throw e;
        }
    }

    public void executeNextBackup() {
        if (backupQueue.isEmpty()) {
            System.out.println("✗ No backup tasks in queue");
            return;
        }

        BackupTask task = backupQueue.poll();
        System.out.println("\n--- Executing Backup Task ---");
        System.out.println("Executing: " + task);

        try {
            // Simulate backup execution
            simulateBackupExecution(task);
            completedBackups.add(task);
            System.out.println("✓ Backup completed successfully!");
        } catch (Exception e) {
            failedBackups.add(task);
            System.out.println("✗ Backup failed: " + e.getMessage());
        }
    }

    private void simulateBackupExecution(BackupTask task) throws Exception {
        // Simulate backup process
        System.out.println("  - Connecting to backup storage...");
        Thread.sleep(500);
        
        System.out.println("  - Scanning folder: " + task.getFolderPath());
        Thread.sleep(300);
        
        System.out.println("  - Compressing " + task.getFormattedSize() + "...");
        Thread.sleep(800);
        
        System.out.println("  - Uploading to cloud storage...");
        Thread.sleep(600);
        
        System.out.println("  - Verifying backup integrity...");
        Thread.sleep(400);
    }

    public void executeAllBackups() {
        System.out.println("\n=== Executing All Scheduled Backups ===");
        int count = 0;
        while (!backupQueue.isEmpty()) {
            count++;
            System.out.println("\n[Batch " + count + "]");
            executeNextBackup();
        }

        if (count == 0) {
            System.out.println("✗ No backups to execute");
        }
    }

    public void showPendingTasks() {
        System.out.println("\n--- Pending Backup Tasks ---");
        if (backupQueue.isEmpty()) {
            System.out.println("No pending tasks");
            return;
        }

        List<BackupTask> temp = new ArrayList<>(backupQueue);
        temp.sort(BackupTask::compareTo);
        
        int position = 1;
        for (BackupTask task : temp) {
            System.out.println(position + ". " + task);
            position++;
        }
    }

    public void showCompletedBackups() {
        System.out.println("\n--- Completed Backups ---");
        if (completedBackups.isEmpty()) {
            System.out.println("No completed backups");
            return;
        }

        for (BackupTask task : completedBackups) {
            System.out.println("✓ " + task);
        }
    }

    public void showFailedBackups() {
        System.out.println("\n--- Failed Backups ---");
        if (failedBackups.isEmpty()) {
            System.out.println("No failed backups");
            return;
        }

        for (BackupTask task : failedBackups) {
            System.out.println("✗ " + task);
        }
    }

    public void showBackupStatistics() {
        System.out.println("\n--- Backup Statistics ---");
        System.out.println("Pending Tasks: " + backupQueue.size());
        System.out.println("Completed Backups: " + completedBackups.size());
        System.out.println("Failed Backups: " + failedBackups.size());
        System.out.println("Total Tasks: " + (backupQueue.size() + completedBackups.size() + failedBackups.size()));
    }

    // Main method
    public static void main(String[] args) {
        FileBackupScheduler scheduler = new FileBackupScheduler();

        try {
            System.out.println("=== File Backup Scheduler System ===\n");

            LocalDateTime now = LocalDateTime.now();

            // Schedule various backup tasks with different priorities
            try {
                System.out.println("--- Scheduling Backup Tasks ---");
                
                // Critical folder - highest priority
                scheduler.scheduleBackup(
                    "C:\\Users\\akank\\OneDrive\\Desktop\\BridgeLabz-Training",
                    1,
                    now.plusHours(1),
                    BackupTask.BackupType.CRITICAL
                );

                // Important folder - medium priority
                scheduler.scheduleBackup(
                    "C:\\Users\\akank\\OneDrive\\Desktop",
                    2,
                    now.plusHours(2),
                    BackupTask.BackupType.IMPORTANT
                );

                // Routine folder - low priority
                scheduler.scheduleBackup(
                    "C:\\Users\\akank\\OneDrive",
                    3,
                    now.plusHours(3),
                    BackupTask.BackupType.ROUTINE
                );

                // Another critical backup with earlier scheduled time
                scheduler.scheduleBackup(
                    "C:\\Windows\\Temp",
                    1,
                    now.plusMinutes(30),
                    BackupTask.BackupType.CRITICAL
                );

            } catch (InvalidBackupPathException e) {
                System.out.println("Error scheduling backup: " + e.getMessage());
            }

            // Show pending tasks (priority queue order)
            scheduler.showPendingTasks();

            // Execute all backups in priority order
            scheduler.executeAllBackups();

            // Show statistics
            scheduler.showBackupStatistics();
            scheduler.showCompletedBackups();
            scheduler.showFailedBackups();

            // Try to schedule an invalid path
            System.out.println("\n--- Attempting to schedule invalid path ---");
            try {
                scheduler.scheduleBackup(
                    "/invalid/non/existent/path",
                    1,
                    now.plusHours(1),
                    BackupTask.BackupType.CRITICAL
                );
            } catch (InvalidBackupPathException e) {
                System.out.println("✓ Exception properly caught: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
