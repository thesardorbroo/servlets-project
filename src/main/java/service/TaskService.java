package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.ServletSecurity;
import entity.Response;
import entity.Task;
import entity.Users;
import repository.TaskRepository;
import util.Node;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class TaskService {

    private TaskRepository repository;

    private ServletSecurity security;

    public TaskService(){
        repository = new TaskRepository();
        security = ServletSecurity.getInstance();
    }

    public Response<Task> addNewTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String headerValue = request.getHeader("Authorization").substring(6);
        Users users = security.checkTheUser(headerValue, "TEACHER");
        if (users == null){
            return new Response<>(401, false, "Unauthorized", null);
        }

//        System.out.println("User is authorized successfully.");
        Task entity = splitBodyFromRequest(request);
        entity = repository.save(entity);

        if(entity == null){
            return new Response<>(-1, false, "Entity is already exists", null);
        }

        return new Response<>(0, true, "OK", entity);
    }

    public Response<List<Task>> getTask(HttpServletRequest request, HttpServletResponse response){
        Map<Integer, Node> map = hasAnyParams(request);

        List<Task> list = repository.getEntity(map);

        if(list.isEmpty()){
            return new Response<>(-2, false, "There is none entity.", list);
        }

        return new Response<>(0, true, "OK", list);
    }

    private Task splitBodyFromRequest(HttpServletRequest request) throws IOException {
        byte[] bytes = request.getInputStream().readAllBytes();
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(bytes, Task.class);
    }

    private Map<Integer, Node> hasAnyParams(HttpServletRequest request){
        Map<Integer, Node> map = new HashMap<>();
        Enumeration<String> e = request.getParameterNames();

        int i = 1;
        while(e.hasMoreElements()){
            String key = e.nextElement();
            String value = request.getParameter(key);

            Node node = new Node(key, value);
            map.put(i++, node);
        }

        return map;
    }
}
