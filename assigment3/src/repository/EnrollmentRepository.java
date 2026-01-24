package repository;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollmentRepository {

    public void enroll(int studentId, int courseId) throws SQLException {
        String sql = "INSERT INTO enrollments(student_id, course_id) VALUES (?, ?)";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        }
    }

    public void deleteByStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM enrollments WHERE student_id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ps.executeUpdate();
        }
    }
}

