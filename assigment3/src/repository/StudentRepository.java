package repository;

import model.Student;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public int create(Student student) throws SQLException {
        String sql = "INSERT INTO students(name, email) VALUES (?, ?) RETURNING id";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public List<Student> getAll() throws SQLException {
        String sql = "SELECT id, name, email FROM students ORDER BY id";
        List<Student> list = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                ));
            }
        }
        return list;
    }

    public Student getById(int id) throws SQLException {
        String sql = "SELECT id, name, email FROM students WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    public boolean update(int id, Student newData) throws SQLException {
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, newData.getName());
            ps.setString(2, newData.getEmail());
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
