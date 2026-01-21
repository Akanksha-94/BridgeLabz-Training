import java.util.ArrayList;
import java.util.List;

abstract class CourseType {
    private String evaluation;

    public CourseType(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluation() {
        return evaluation;
    }
}

class ExamCourse extends CourseType {
    public ExamCourse() {
        super("Exam Based");
    }
}

class AssignmentCourse extends CourseType {
    public AssignmentCourse() {
        super("Assignment Based");
    }
}

class ResearchCourse extends CourseType {
    public ResearchCourse() {
        super("Research Based");
    }
}

class Course<T extends CourseType> {
    private String courseName;
    private T courseType;

    public Course(String courseName, T courseType) {
        this.courseName = courseName;
        this.courseType = courseType;
    }

    public String info() {
        return courseName + " - " + courseType.getEvaluation();
    }

    public T getCourseType() {
        return courseType;
    }
}

class CourseViewer {
    public static void display(List<? extends CourseType> types) {
        for (CourseType type : types) {
            System.out.println(type.getEvaluation());
        }
    }
}

public class UniversityCourseManagement {
    public static void main(String[] args) {

        Course<ExamCourse> math =
                new Course<>("Mathematics", new ExamCourse());

        Course<AssignmentCourse> cs =
                new Course<>("Computer Science", new AssignmentCourse());

        Course<ResearchCourse> phd =
                new Course<>("PhD Research", new ResearchCourse());

        List<CourseType> evaluations = new ArrayList<>();
        evaluations.add(math.getCourseType());
        evaluations.add(cs.getCourseType());
        evaluations.add(phd.getCourseType());

        System.out.println(math.info());
        System.out.println(cs.info());
        System.out.println(phd.info());

        CourseViewer.display(evaluations);
    }
}