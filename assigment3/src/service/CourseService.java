package service;

import exception.DatabaseOperationException;
import exception.InvalidInputException;
import model.Course;
import repository.CourseRepository;

import java.sql.SQLException;
import java.util.List;

public class CourseService {

    private final CourseRepository courses = new CourseRepository();

    public int addCourse(Course c) {
        if (c == null || !c.isValid()) {
            throw new InvalidInputException("Course data is invalid");
        }

        try {
            return courses.create(c);
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while creating course", e);
        }
    }

    public List<Course> getAllCourses() {
        try {
            return courses.getAll();
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while reading courses", e);
        }
    }
}
