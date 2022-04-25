package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.User;
import com.revature.services.AuthService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationPageServlet extends HttpServlet {
    private ObjectMapper mapper;
    private AuthService authService;

    public void init() throws  ServletException{
        this.authService = new AuthService();
        this.mapper  = new ObjectMapper();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = mapper.readValue(req.getReader().toString(), User.class);
        try {
            user = authService.register(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = mapper.writeValueAsString(user);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }
}
