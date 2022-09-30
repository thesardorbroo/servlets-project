package controller;

import config.Util;
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

        Util.sendResponse("application.json", resp, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<List<Users>> response = service.getAllUsers(req, resp);

        Util.sendResponse("application.json", resp, response);
    }

}
