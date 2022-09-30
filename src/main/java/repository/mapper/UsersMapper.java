package repository.mapper;

import entity.Users;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersMapper implements RowMapper<Users> {

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt(1);
        String firstName = rs.getString(2);
        String lastName = rs.getString(3);
        String phoneNumber = rs.getString(4);
        String username = rs.getString(5);
        String password = rs.getString(6);
        String priority = rs.getString(10);

        return new Users(id, firstName, lastName, phoneNumber, username, password, priority);
    }
}
