package com.revature;

import com.revature.services.AuthService;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        AuthService as = new AuthService();
        Scanner loginInput = new Scanner(System.in);

        System.out.println("Welcome to the Employee Reimbursement System");
        System.out.println("To begin, enter your username");
        String username = loginInput.next();

        System.out.println("Hello, " + username + ".");
        System.out.println("Enter your password");
        String password = loginInput.next();
        // Testing scanner
        System.out.println("Username: " + username + "\n" + "Password: " +  password);
        // Beyond this point, the login method found in AuthService will be invoked to check the
        //database with the provided credentials.
        //as.login(username, password)
        //If the user is not in the database, prompt them to either register or validate credentials.
        //I may include a change password method if time allows.
        loginInput.close();
    }
}
