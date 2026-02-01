package controller;

import model.Course;
import service.CourseService;

public class CourseController {

    private final CourseService service = new CourseService();

    public void create(Course c) {
        service.addCourse(c);
        System.out.println("✅ Course created");
    }

    public void showAll() {
        System.out.println("📋 Courses list:");
        service.getAllCourses()
                .forEach(cr -> System.out.println("  " + cr.getInfo()));
    }
}
