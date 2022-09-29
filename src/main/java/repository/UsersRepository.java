package repository;

import config.DatabaseConfig;
import entity.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.mapper.UsersMapper;

import javax.sql.DataSource;
import java.util.List;

public class UsersRepository {

    private JdbcTemplate template;

    private UsersMapper mapper;

    public UsersRepository(){
        DataSource source = DatabaseConfig.getInstance().getDataSource();
        template = new JdbcTemplate(source);
        mapper = new UsersMapper();

    }

    public Users save(Users users){
        if (existsUsersByOnceParam("username", users.getUsername())){
            return null;
        }

        String query = String.format(
                "INSERT INTO users (first_name, last_name, phone_number, username, password) VALUES ('%s','%s','%s','%s','%s')",
                users.getFirstName(), users.getLastName(), users.getPhoneNumber(), users.getUsername(), users.getPassword()
                );
        template.execute(query);

        return findUsersByOnceParam("username", users.getUsername());
    }

    public List<Users> findAll(){
        String query = "SELECT * FROM users";
        return template.query(query, mapper);
    }

    public Users findUsersByOnceParam(String key, String value){
        String query = String.format("SELECT * FROM users WHERE %s = %s", key, value);
        List<Users> users = template.query(query, mapper);

        return users.isEmpty()? null: users.get(0);
    }

    public Users findUsersByOnceParam(String key, Integer value){
        String query = String.format("SELECT * FROM users WHERE %s = %d", key, value);
        List<Users> users = template.query(query, mapper);

        return users.isEmpty()? null: users.get(0);
    }

    public boolean existsUsersByOnceParam(String key, String value){
        String query = String.format("SELECT 1 FROM users WHERE %s = %s", key, value);
        List<Users> users = template.query(query, mapper);

        return users.isEmpty();
    }

    public boolean existsUsersByOnceParam(String key, Integer value){
        String query = String.format("SELECT 1 FROM users WHERE %s = %d", key, value);
        List<Users> users = template.query(query, mapper);

        return users.isEmpty();
    }
}
