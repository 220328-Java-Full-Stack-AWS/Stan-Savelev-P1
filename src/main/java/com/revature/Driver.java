package com.revature;

import com.revature.services.AuthService;
import com.revature.util.ConnectionManager;
import com.revature.repositories.UserDAO;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;

//Note 1: The Junit test package is marked at excluded for the time being.
public class Driver {

    public static void main(String[] args) {
        AuthService as = new AuthService();
        UserDAO foundUser = new UserDAO();

        Scanner loginInput = new Scanner(System.in);

        try{
            Connection conn = ConnectionManager.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Welcome to the Employee Reimbursement System");
        System.out.println("To begin, enter your username");
        String username = loginInput.next();
        //Testing userByUsername method.
        try{
            System.out.println(foundUser.getByUsername(username));
        }catch (SQLException e){
            e.printStackTrace();
        }

//        System.out.println("Hello, " + username + ".");
//        System.out.println("Enter your password");
//        String password = loginInput.next();
        // Testing scanner
//        System.out.println("Username: " + username + "\n" + "Password: " +  password);
        // Beyond this point, the login method found in AuthService will be invoked to check the
        //database with the provided credentials.
//        as.login(username, password);
        //If the user is not in the database, prompt them to either register or validate credentials.
        //I may include a change password method if time allows.
        loginInput.close();
        //vv Testing connection vv

    }
}
