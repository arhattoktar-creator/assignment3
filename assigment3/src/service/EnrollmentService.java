package service;

import exception.DatabaseOperationException;
import exception.InvalidInputException;
import repository.EnrollmentRepository;

import java.sql.SQLException;

public class EnrollmentService {

    private final EnrollmentRepository repo = new EnrollmentRepository();

    public void enroll(int studentId, int courseId) {
        if (studentId <= 0 || courseId <= 0) {
            throw new InvalidInputException("Ids must be > 0");
        }

        try {
            repo.enroll(studentId, courseId);
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while enrolling", e);
        }
    }
}
