import controller.CourseController;
import controller.EnrollmentController;
import controller.StudentController;
import model.Course;
import model.Student;
import utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        StudentController students = new StudentController();
        CourseController courses = new CourseController();
        EnrollmentController enrollments = new EnrollmentController();

        // create student
        String email = "arkat" + System.currentTimeMillis() + "@mail.com";
        Student s = new Student(0, "Arkat", email);
        students.create(s);

        // create course
        Course c = new Course(0, "Java OOP", 5);
        courses.create(c);

        // show all
        students.showAll();
        courses.showAll();

        // === STEP 7: LAMBDA DEMO ===
        List<Student> list = new ArrayList<>();
        list.add(s);
        list.add(new Student(999, "Beka", "b@mail.com"));
        list.add(new Student(998, "Ali", "a@mail.com"));

        list.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("🔀 Lambda sorting demo:");
        list.forEach(Student::print);

        // === STEP 8: REFLECTION DEMO ===
        ReflectionUtils.printClassInfo(s);

        System.out.println("✅ Demo finished OK");
    }
}
