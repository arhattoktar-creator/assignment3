package service;

import exception.DatabaseOperationException;
import exception.InvalidInputException;
import repository.EnrollmentRepository;

public class EnrollmentService {

    private final EnrollmentRepository repo = new EnrollmentRepository();

    public void enroll(int studentId, int courseId) {

        if (studentId <= 0 || courseId <= 0) {
            throw new InvalidInputException("Ids must be > 0");
        }

        try {
            repo.enroll(studentId, courseId);
        } catch (RuntimeException e) {
            throw new DatabaseOperationException("DB error while enrolling", e);
        }
    }
}
