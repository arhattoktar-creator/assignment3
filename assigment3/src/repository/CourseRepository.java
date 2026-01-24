package repository;

import model.Course;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    public int create(Course course) throws SQLException {
        String sql = "INSERT INTO courses(name, credits) VALUES (?, ?) RETURNING id";

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, course.getName());
            ps.setInt(2, course.getCredits());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public List<Course> getAll() throws SQLException {
        String sql = "SELECT id, name, credits FROM courses ORDER BY id";
        List<Course> list = new ArrayList<>();

        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("credits")
                ));
            }
        }
        return list;
    }
}
