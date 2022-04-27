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
        UserDAO testDao = new UserDAO();
        Scanner loginInput = new Scanner(System.in);

        System.out.println("Enter an id");
        int idNum = Integer.parseInt(loginInput.next());

        try{
            System.out.println(testDao.promoteToAdminById(idNum));
        }catch (SQLException e){
            e.printStackTrace();
        }

        loginInput.close();
    }

}

