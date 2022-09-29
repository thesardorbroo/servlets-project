package repository;

import config.DatabaseConfig;
import entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import repository.mapper.StudentRepositoryMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentRepository {

    private final JdbcTemplate template;

    public StudentRepository(){
        DataSource source = DatabaseConfig.getInstance().getDataSource();
        this.template = new JdbcTemplate(source);
    }

    public List<Student> getStudent(Map<String, String> map){

        StringBuilder query = new StringBuilder("SELECT * FROM student WHERE  1=1");
        createStringQuery(query, map);

        PreparedStatementSetter setter = ps -> {setValuesToStatement(ps, map);};
        List<Student> students = template.query(query + "", setter, new StudentRepositoryMapper());

        return students;
    }

    private void createStringQuery(StringBuilder builder, Map<String, String> map){
        if(map.containsKey("id")){
            builder.append(" AND id = ?");
        }
        if(map.containsKey("name")){
            builder.append(" AND name = ?");
        }
        if(map.containsKey("phone_number")){
            builder.append(" AND phone_number = ?");
        }
    }

    private void setValuesToStatement(PreparedStatement ps, Map<String, String> map){
        Set<Map.Entry<String, String>> set = map.entrySet();

        int i = 1;
        for(Map.Entry<String, String> e: set){
            String k = e.getKey();
            String v = e.getValue();

            try {
                if ("id".equals(k)) {
                    ps.setInt(i, Integer.parseInt(v));
                } else if ("name".equals(k) || "phoneNumber".equals(k)) {
                    ps.setString(i, v);
                }
            }catch (SQLException exception){
                System.err.println("Error while setting values to statement!");
            }
            i++;
        }

    }

    public void saveStudent(Student student){
        String query = String.format("INSERT INTO student (name, phone_number) VALUES ('%s', '%s')", student.getName(), student.getPhoneNumber());
        template.execute(query);
    }
}
