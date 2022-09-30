package repository;

import config.DatabaseConfig;
import entity.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import repository.mapper.UsersMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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

    public List<Users> getUsers(Map<String, String> map){
        StringBuilder builder = new StringBuilder("SELECT * FROM users WHERE 1=1 ");
        setParameters(builder, map);

        return template.query(builder + "", mapper);
    }

    private void setParameters(StringBuilder builder, Map<String, String> map){
        if(map.containsKey("id")){
            builder.append(String.format(" AND id = %d", Integer.parseInt(map.get("id"))));
        }
        if(map.containsKey("firstName")){
            builder.append(String.format(" AND firstname = '%s'", map.get("firstName")));
        }
        if(map.containsKey("lastName")){
            builder.append(String.format(" AND lastname = '%s'", map.get("lastName")));
        }
        if(map.containsKey("phoneNumber")){
            builder.append(String.format(" AND phonenumber = '%s'", map.get("phoneNumber")));
        }
        if(map.containsKey("username")){
            builder.append(String.format(" AND username = '%s'", map.get("username")));
        }
        if(map.containsKey("password")){
            builder.append(String.format(" AND password = '%s'", map.get("password")));
        }
        if(map.containsKey("priority")){
            builder.append(String.format(" AND priority = '%s'", map.get("priority")));
        }

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
