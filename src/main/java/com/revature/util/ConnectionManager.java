package com.revature.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionManager {
    //keep track of the connection
    private static Connection connection;

    private ConnectionManager() {
    }


    public static Connection getConnection(){
        if(connection == null) {
            connection = connect();
        }

        return connection;
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    //establish connection method
    private static Connection connect(){

        try {

            //New method grabbing the properties from the JAR classpath
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("application.properties");
            props.load(input);


            String connectionString = "jdbc:postgresql://" +
                    props.getProperty("hostname") + ":" +
                    props.getProperty("port") + "/" +
                    props.getProperty("dbname")
                    + "?schemaName=" +
                    props.getProperty("schemaName");

            String username = props.getProperty("username");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(connectionString, username, password);


            System.out.println("Connection String: " + connectionString);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


        return connection;

    }
}