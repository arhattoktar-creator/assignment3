package service;

import exception.DatabaseOperationException;
import exception.DuplicateResourceException;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Student;
import repository.EnrollmentRepository;
import repository.StudentRepository;

import java.util.List;

public class StudentService {

    private final StudentRepository students = new StudentRepository();
    private final EnrollmentRepository enrollments = new EnrollmentRepository();

    public void addStudent(Student s) {
        if (s == null || !s.isValid()) {
            throw new InvalidInputException("Student data is invalid");
        }

        try {
            students.create(s);
        } catch (RuntimeException e) {
            if (e.getCause() != null
                    && e.getCause() instanceof java.sql.SQLException
                    && "23505".equals(((java.sql.SQLException) e.getCause()).getSQLState())) {

                throw new DuplicateResourceException(
                        "Email already exists: " + s.getEmail()
                );
            }
            throw new DatabaseOperationException("DB error while creating student", e);
        }
    }

    public List<Student> getAllStudents() {
        try {
            return students.getAll();
        } catch (RuntimeException e) {
            throw new DatabaseOperationException("DB error while reading students", e);
        }
    }

    public void updateStudent(Student newData) {
        if (newData == null || !newData.isValid()) {
            throw new InvalidInputException("New student data is invalid");
        }

        try {
            students.update(newData);
        } catch (RuntimeException e) {
            if (e.getCause() != null
                    && e.getCause() instanceof java.sql.SQLException
                    && "23505".equals(((java.sql.SQLException) e.getCause()).getSQLState())) {

                throw new DuplicateResourceException(
                        "Email already exists: " + newData.getEmail()
                );
            }
            throw new DatabaseOperationException("DB error while updating student", e);
        }
    }

    public void deleteStudent(int id) {
        try {
            enrollments.deleteByStudent(id);
            students.delete(id);
        } catch (RuntimeException e) {
            throw new DatabaseOperationException("DB error while deleting student", e);
        }
    }
}
