package com.revature;

import com.revature.services.AuthService;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {
        AuthService as = new AuthService();
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Employee Reimbursement System");
        System.out.println("To begin, enter your username");
        String username = input.next();

        System.out.println("Hello, " + username + ".");
        System.out.println("Enter your password");
        String password = input.next();
        // Testing scanner
        System.out.println("Username: " + username + "\n" + "Password: " +  password);
        // Beyond this point the login method found in AuthService is used to check the
        //database with the provided credentials.
        input.close();
    }
}
