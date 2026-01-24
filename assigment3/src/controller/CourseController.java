package controller;

import model.Course;
import service.CourseService;

public class CourseController {

    private final CourseService service = new CourseService();

    public int create(Course c) {
        int id = service.addCourse(c);
        System.out.println("✅ Course created with id=" + id);
        return id;
    }

    public void showAll() {
        System.out.println("📋 Courses list:");
        service.getAllCourses().forEach(cr -> System.out.println("  " + cr.getInfo()));
    }
}
