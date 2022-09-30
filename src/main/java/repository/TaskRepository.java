package repository;

import config.DatabaseConfig;
import entity.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import repository.mapper.TaskMapper;
import util.Node;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class TaskRepository {

    private JdbcTemplate template;

    public TaskRepository(){
         DriverManagerDataSource source = DatabaseConfig.getInstance().getDataSource();
         template = new JdbcTemplate(source);
    }

    public Task save(Task task){
        if(!taskIsExists(task)){
            return null;
        }
        String query = String.format(
                "INSERT INTO task(name, unique_key) values ('%s', '%s')",
                task.getName(), task.getUniqueKey()
                );

        template.execute(query);
        return getSavedEntity(task);
    }

    public List<Task> getEntity(Map<Integer, Node> map){
        StringBuilder builder = new StringBuilder("SELECT * FROM task WHERE 1=1 ");
        createQuery(builder, map);

        List<Task> list = template.query(builder + "", new TaskMapper());
        return list;

    }

    private boolean taskIsExists(Task task){
        String query = String.format("SELECT 1 FROM task WHERE unique_key = '%s'", task.getUniqueKey());

        List<Task> list = template.query(query, new TaskMapper());
        return list.isEmpty();
    }

    private Task getSavedEntity(Task task){
        String query = String.format("SELECT * FROM task WHERE unique_key = '%s'", task.getUniqueKey());

        return template.queryForObject(query, new TaskMapper());
    }

    private void createQuery(StringBuilder builder, Map<Integer, Node> map){
        Set<Map.Entry<Integer, Node>> set = map.entrySet();
        for (Map.Entry<Integer, Node> e: set){
            Node n = e.getValue();

            try{
                Integer number = Integer.parseInt(n.getValue());
                builder.append(String.format(" AND %s = %d", n.getParameter(), number));
            }catch (NumberFormatException exception){
                builder.append(String.format(" AND %s = '%s'", n.getParameter(), n.getValue()));
            }
        }
    }

}
