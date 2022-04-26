package com.revature;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.util.ConnectionManager;
import com.revature.persistance.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

//Note 1: The Junit test package is marked as excluded for the time being.
public class Driver {

    public static void main(String[] args) {
        UserDAO testAdminDao = new UserDAO();
        Scanner loginInput = new Scanner(System.in);
//        System.out.println("Updating request");
//        System.out.println("Enter the reimbusrement id");
//        int reimbId = Integer.parseInt(loginInput.next());
//        System.out.println("Amount");
//        double am = Double.parseDouble(loginInput.next());
//        System.out.println("Description");
//        String desc = loginInput.next();
//        System.out.println("Enter the type. 1) FOOD 2) TRAVEL 3) LODGING");
//        int type = Integer.parseInt(loginInput.next());
//
//        Reimbursement request = new Reimbursement(am,desc,type);
//        try{
//            System.out.println(testReimbDao.editByReimbId(reimbId, request));
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

        System.out.println("Enter the status code");
        int status = Integer.parseInt(loginInput.next());

        try{
            System.out.println(testAdminDao.getByStatus(status));
        }catch (SQLException e){
            e.printStackTrace();
        }

        loginInput.close();
    }

}

