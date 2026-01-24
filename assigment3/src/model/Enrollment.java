package model;

public class Enrollment {
    private final Student student;
    private final Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public String info() {
        return student.getInfo() + " ENROLLED -> " + course.getInfo();
    }
}
