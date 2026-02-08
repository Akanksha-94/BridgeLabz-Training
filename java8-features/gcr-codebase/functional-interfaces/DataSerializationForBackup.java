import java.io.*;

public class DataSerializationForBackup {
    
    static class User implements Serializable {
        private static final long serialVersionUID = 1L;
        String userId;
        String name;
        String email;
        
        User(String userId, String name, String email) {
            this.userId = userId;
            this.name = name;
            this.email = email;
        }
        
        @Override
        public String toString() {
            return "User{id='" + userId + "', name='" + name + "', email='" + email + "'}";
        }
    }
    
    static class BackupService {
        public void backup(Object obj, String filename) {
            if (!(obj instanceof Serializable)) {
                System.out.println("Error: Object is not serializable for backup");
                return;
            }
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(obj);
                System.out.println("Backup successful: " + filename);
            } catch (IOException e) {
                System.err.println("Backup failed: " + e.getMessage());
            }
        }
        
        public Object restore(String filename) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                Object obj = ois.readObject();
                System.out.println("Restore successful: " + filename);
                return obj;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Restore failed: " + e.getMessage());
                return null;
            }
        }
    }
    
    public static void main(String[] args) {
        User user = new User("U001", "John Doe", "john@example.com");
        
        System.out.println("Original: " + user);
        
        BackupService backupService = new BackupService();
        backupService.backup(user, "user_backup.ser");
        
        User restoredUser = (User) backupService.restore("user_backup.ser");
        System.out.println("Restored: " + restoredUser);
    }
}