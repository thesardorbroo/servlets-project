package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.Util;
import entity.Response;
import entity.Student;
import repository.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class StudentService {

    private StudentRepository repository;

    public StudentService(){
        repository = new StudentRepository();
    }

    public void getStudentByParam(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Map<String, String> map = new HashMap<>();
        Enumeration<String> enumeration = req.getParameterNames();

        while(enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            String val = req.getParameter(key);

            map.put(key, val);

            System.out.println(String.format("Key: %s --> Value: %s\n", key, val));
        }

        List<Student> students = repository.getStudent(map);
        students.stream().forEach(System.out::println);

        Response<List<Student>> response = new Response<>(0, true, "OK", students);

        Util.sendResponse("application.json", resp, response);
    }
    
    public void addNewStudent(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        byte[] bytes = new byte[100];
        req.getInputStream().read(bytes);
        ObjectMapper mapper = new ObjectMapper();
        Student student = mapper.readValue(bytes, Student.class);

        repository.saveStudent(student);

        resp.setContentType("application/json");
        Response<Student> response = new Response<>(0, true, "OK", student);
        PrintWriter writer = resp.getWriter();
        writer.println(response);
        writer.close();
    }
    

}
