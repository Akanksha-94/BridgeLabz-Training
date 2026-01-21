import java.util.ArrayList;
import java.util.List;

abstract class JobRole {
    private String roleName;

    public JobRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}

class SoftwareEngineer extends JobRole {
    public SoftwareEngineer() {
        super("Software Engineer");
    }
}

class DataScientist extends JobRole {
    public DataScientist() {
        super("Data Scientist");
    }
}

class ProductManager extends JobRole {
    public ProductManager() {
        super("Product Manager");
    }
}

class Resume<T extends JobRole> {
    private String candidateName;
    private T role;

    public Resume(String candidateName, T role) {
        this.candidateName = candidateName;
        this.role = role;
    }

    public T getRole() {
        return role;
    }

    public String summary() {
        return candidateName + " -> " + role.getRoleName();
    }
}

class ScreeningEngine {
    public static <T extends Resume<?>> void screen(T resume) {
        System.out.println("Screening: " + resume.summary());
    }

    public static void pipeline(List<? extends JobRole> roles) {
        for (JobRole role : roles) {
            System.out.println("Processing role: " + role.getRoleName());
        }
    }
}

public class AIDrivenResumeScreeningSystem {
    public static void main(String[] args) {

        Resume<SoftwareEngineer> r1 =
                new Resume<>("Aman", new SoftwareEngineer());

        Resume<DataScientist> r2 =
                new Resume<>("Neha", new DataScientist());

        Resume<ProductManager> r3 =
                new Resume<>("Rohit", new ProductManager());

        ScreeningEngine.screen(r1);
        ScreeningEngine.screen(r2);
        ScreeningEngine.screen(r3);

        List<JobRole> roleFlow = new ArrayList<>();
        roleFlow.add(r1.getRole());
        roleFlow.add(r2.getRole());
        roleFlow.add(r3.getRole());

        ScreeningEngine.pipeline(roleFlow);
    }
}