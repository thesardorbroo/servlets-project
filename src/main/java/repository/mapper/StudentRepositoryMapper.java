package repository.mapper;

import entity.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepositoryMapper implements RowMapper<Student> {

    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3)
        );

        return student;
    }
}
