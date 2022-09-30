package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Response;
import entity.Users;
import repository.UsersRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersService {

    private UsersRepository repository;

    public UsersService(){
        repository = new UsersRepository();
    }

    public Response<Users> addUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] bytes = req.getInputStream().readAllBytes();

        ObjectMapper objectMapper = new ObjectMapper();
        Users entity = objectMapper.readValue(bytes, Users.class);
//        Users entity = objectMapper.readValue(inputStream, Users.class);
        entity = repository.save(entity);

        return entity == null?
                new Response<>(-1, false, "User is already exists", null):
                new Response<>(0, true, "OK", entity);
    }

    public Response<List<Users>> getAllUsers(HttpServletRequest req, HttpServletResponse resp) {
        Enumeration<String> parameters = req.getParameterNames();

        Map<String, String> map = new HashMap<>();

        while (parameters.hasMoreElements()) {
            String key = parameters.nextElement();
            String value = req.getParameter(key);

            map.put(key, value);

        }

        List<Users> users = repository.getUsers(map);

        return users.isEmpty() ?
                new Response<>(-1, false, "User is not exists", null) :
                new Response<>(0, true, "OK", users);

    }
}
