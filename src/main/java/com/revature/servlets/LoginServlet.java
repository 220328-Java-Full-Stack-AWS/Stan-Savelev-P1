package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    private AuthService authService;
    private UserService userService;
    private ObjectMapper mapper;

    public void init() throws ServletException {
        this.authService = new AuthService();
        this.mapper = new ObjectMapper();
        this.userService = new UserService();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
