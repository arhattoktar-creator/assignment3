package service;

import exception.DatabaseOperationException;
import exception.InvalidInputException;
import model.Course;
import repository.CourseRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository courses = new CourseRepository();

    public void addCourse(Course c) {
        if (c == null || !c.isValid()) {
            throw new InvalidInputException("Course data is invalid");
        }

        try {
            courses.create(c);
        } catch (RuntimeException e) {
            throw new DatabaseOperationException("DB error while creating course", e);
        }
    }

    public List<Course> getAllCourses() {
        try {
            return courses.getAll();
        } catch (RuntimeException e) {
            throw new DatabaseOperationException("DB error while reading courses", e);
        }
    }
}
