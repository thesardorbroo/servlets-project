package repository.mapper;

import entity.Task;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rowNum) throws SQLException {
        Task entity = new Task();
        entity.setId(rs.getInt(1));
        entity.setName(rs.getString(2));
        entity.setUniqueKey(rs.getString(3));
        entity.setCreatedAt(rs.getDate(4));

        return entity;
    }
}
