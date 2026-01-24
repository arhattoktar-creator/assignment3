import controller.CourseController;
import controller.EnrollmentController;
import controller.StudentController;
import exception.DuplicateResourceException;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.BaseEntity;
import model.Course;
import model.Enrollment;
import model.Student;

public class Main {
    public static void main(String[] args) {

        StudentController students = new StudentController();
        CourseController courses = new CourseController();
        EnrollmentController enroll = new EnrollmentController();

        try {
            // CREATE student with unique email each run
            String email = "arkat" + System.currentTimeMillis() + "@mail.com";
            Student s = new Student(0, "Arkat", email);
            int studentId = students.create(s);

            // CREATE course
            Course c = new Course(0, "Java OOP", 5);
            int courseId = courses.create(c);

            // READ
            students.showAll();
            courses.showAll();

            // ENROLL (composition demo)
            enroll.enroll(studentId, courseId);
            Enrollment e = new Enrollment(s, c);
            System.out.println("📌 Composition demo: " + e.info());

            // POLYMORPHISM demo (BaseEntity reference)
            BaseEntity asBase = s;
            System.out.println("🌀 Polymorphism demo: " + asBase.getInfo());

            // UPDATE student
            String newEmail = "arkat_updated_" + System.currentTimeMillis() + "@mail.com";
            students.update(studentId, new Student(studentId, "Arkat the 2nd", newEmail));

            // DELETE student (enrollments removed first + cascade in DB)
            students.delete(studentId);

            System.out.println("✅ Demo finished OK");

        } catch (DuplicateResourceException ex) {
            System.out.println("⚠️ Duplicate: " + ex.getMessage());
        } catch (InvalidInputException ex) {
            System.out.println("⚠️ Invalid input: " + ex.getMessage());
        } catch (ResourceNotFoundException ex) {
            System.out.println("⚠️ Not found: " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("💥 Unexpected error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
