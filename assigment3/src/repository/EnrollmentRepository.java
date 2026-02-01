package repository;

import model.Enrollment;
import repository.interfaces.CrudRepository;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollmentRepository implements CrudRepository<Enrollment> {

    // === BUSINESS METHOD (остается как есть) ===
    public void enroll(int studentId, int courseId) {
        String sql = "INSERT INTO enrollments(student_id, course_id) VALUES (?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // === CRUD INTERFACE METHODS (минимальная реализация) ===

    @Override
    public void create(Enrollment enrollment) {
        // not used (enroll is used instead)
    }

    @Override
    public Enrollment getById(int id) {
        return null;
    }

    @Override
    public void update(Enrollment enrollment) {
        // not used
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM enrollments WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // === EXTRA METHOD (можно оставить) ===
    public void deleteByStudent(int studentId) {
        String sql = "DELETE FROM enrollments WHERE student_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
