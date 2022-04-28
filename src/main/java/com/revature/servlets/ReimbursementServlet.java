package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ReimbursementServlet extends HttpServlet {

    private ReimbursementService service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reimbursement request =  mapper.readValue(req.getInputStream(), Reimbursement.class);
        try {
            service.create(request);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = mapper.writeValueAsString(request);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reimb_author = req.getHeader("reimb_author");
        Integer i = Integer.parseInt(reimb_author);
        Reimbursement reimb = new Reimbursement();
        try {
            reimb = (Reimbursement) service.read(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reimb);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
        resp.setStatus(200);
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reimb_id = req.getHeader("reimb_id");
        Integer i = Integer.parseInt(reimb_id);
        Reimbursement reimb = new Reimbursement();

        try {
            service.update(i,reimb);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = mapper.writeValueAsString(reimb);
        resp.setStatus(201);
        resp.getWriter().print(json);
    }
}
