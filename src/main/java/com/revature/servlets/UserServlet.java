package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.persistance.UserDAO;
import com.revature.services.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UserServlet extends HttpServlet {

    private UserService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService();
        this.mapper = new ObjectMapper();
    }
    //creates user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user =  mapper.readValue(req.getInputStream(), User.class);
        try {
            user = service.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = mapper.writeValueAsString(user);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    //get by id
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ers_user_id = req.getHeader("ers_user_id");
        Integer i = Integer.parseInt(ers_user_id);
        User user = new User();
        try {
            user = service.read(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    //promotes to admin works
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ers_user_id = req.getHeader("ers_user_id");
        Integer i = Integer.parseInt(ers_user_id);
        User user = new User();

        try {
            service.update(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = mapper.writeValueAsString(user);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    //Foreign key issue.
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            service.delete(Integer.parseInt(req.getHeader("ers_user_id")));
//            resp.setStatus(200);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
