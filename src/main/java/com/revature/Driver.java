package com.revature;

import com.revature.models.Reimbursement;
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
        AdminDAO testAdminDao = new AdminDAO();
        Scanner loginInput = new Scanner(System.in);

        System.out.println("Enter status:");
        System.out.println("The options are: 1)Pending 2)Approved 3)Denied");
        int statusCode = Integer.parseInt(loginInput.next());


        try{
            testAdminDao.getByStatus(statusCode);
        }catch (SQLException e){
            e.printStackTrace();
        }
//
        //If the user is not in the database, prompt them to either register or validate credentials.
        //I may include a change password method if time allows.
        loginInput.close();

        
    }

}

