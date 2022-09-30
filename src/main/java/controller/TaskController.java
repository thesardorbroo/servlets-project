package controller;

import config.Util;
import entity.Response;
import entity.Task;
import service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TaskController extends HttpServlet {

    private TaskService service;

    @Override
    public void init() throws ServletException {
        service = new TaskService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<Task> response = service.addNewTask(req, resp);
        Util.sendResponse("application.json", resp, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<List<Task>> response = service.getTask(req, resp);
        Util.sendResponse("application.json", resp, response);
    }
}
