package com.revature;

import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.util.ConnectionManager;
import com.revature.persistance.UserDAO;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

//Note 1: The Junit test package is marked at excluded for the time being.
public class Driver {

    public static void main(String[] args) {
//        AuthService as = new AuthService();
        UserDAO daoTest = new UserDAO();
        Scanner loginInput = new Scanner(System.in);

//        try{
//            Connection conn = ConnectionManager.getConnection();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
        System.out.println("Welcome to the Employee Reimbursement System");
      System.out.println("To begin, enter your username");
//        System.out.println("To begin registration, enter a username:");
        String un = loginInput.next();
//        System.out.println("Enter a password:");
//        String pw = loginInput.next();
//        System.out.println("Enter your first name");
//        String fn = loginInput.next();
//        System.out.println("Enter your last name");
//        String ln = loginInput.next();
//        System.out.println("Finally, enter your email");
//        String em = loginInput.next();
        //Testing create method found in UserDAO
        //Test successfully adds a user! (04/20/2022)
//        User newUser = new User(un,pw,fn,ln,em);
//        try{
//            System.out.println(daoTest.create(newUser));
//        }catch (SQLException e){
//            e.printStackTrace();
//        }

        System.out.println("Please enter a new password");
        String newPw = loginInput.next();

        try{
            System.out.println(daoTest.changePassword(newPw, un));
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

