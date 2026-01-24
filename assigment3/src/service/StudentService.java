package service;

import exception.DatabaseOperationException;
import exception.DuplicateResourceException;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import model.Student;
import repository.EnrollmentRepository;
import repository.StudentRepository;

import java.sql.SQLException;
import java.util.List;

public class StudentService {

    private final StudentRepository students = new StudentRepository();
    private final EnrollmentRepository enrollments = new EnrollmentRepository();

    public int addStudent(Student s) {
        if (s == null || !s.isValid()) {
            throw new InvalidInputException("Student data is invalid");
        }

        try {
            return students.create(s);
        } catch (SQLException e) {
            // SQLState 23505 = unique violation
            if ("23505".equals(e.getSQLState())) {
                throw new DuplicateResourceException("Email already exists: " + s.getEmail());
            }
            throw new DatabaseOperationException("DB error while creating student", e);
        }
    }

    public List<Student> getAllStudents() {
        try {
            return students.getAll();
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while reading students", e);
        }
    }

    public void updateStudent(int id, Student newData) {
        if (newData == null || !newData.isValid()) {
            throw new InvalidInputException("New student data is invalid");
        }

        try {
            boolean ok = students.update(id, newData);
            if (!ok) throw new ResourceNotFoundException("Student not found: id=" + id);
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new DuplicateResourceException("Email already exists: " + newData.getEmail());
            }
            throw new DatabaseOperationException("DB error while updating student", e);
        }
    }

    public void deleteStudent(int id) {
        try {
            // чтобы точно не словить FK, даже если CASCADE вдруг не стоит
            enrollments.deleteByStudent(id);

            boolean ok = students.delete(id);
            if (!ok) throw new ResourceNotFoundException("Student not found: id=" + id);
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB error while deleting student", e);
        }
    }
}
