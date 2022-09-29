package controller;

import entity.Response;
import entity.Users;
import service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UsersController extends HttpServlet {

    private UsersService service;

    @Override
    public void init() throws ServletException {
        service = new UsersService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<Users> response = service.addUser(req, resp);

        sendResponse("application.json", resp, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<List<Users>> response = service.getAllUsers(req, resp);

        sendResponse("application.json", resp, response);
    }

    private void sendResponse(String contentType,HttpServletResponse resp, Response<?> value) throws IOException {
        resp.setContentType(contentType);
        PrintWriter writer = resp.getWriter();
        writer.println(value);

        writer.close();

    }
}
