package com.revature.persistance;

import com.revature.models.User;
import com.revature.models.Role;
import com.revature.util.ConnectionManager;
import java.sql.*;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding username or an empty optional if there is no match.
     */
    public Optional<User> getByUsername(String username) throws SQLException {
            String SQL = "SELECT * FROM ers_users WHERE ers_username = ?";
            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();
            User tempUser = new User();

            if (rs.next()) {
                tempUser.setId(rs.getInt("ers_user_id"));
                tempUser.setUsername(rs.getString("ers_username"));
                tempUser.setPassword(rs.getString("ers_password"));
                int userRoleId = rs.getInt("user_role_id");

                if (userRoleId == 1) {
                    tempUser.setRole(Role.EMPLOYEE);
                } else {
                    tempUser.setRole(Role.FINANCE_MANAGER);
                }
                return Optional.of(tempUser);
            }

            return Optional.empty();
    }
    /**
     * <ul>
     *     <li>Should Insert a new User record into the DB with the provided information.</li>
     *     <li>Should throw an exception if the creation is unsuccessful.</li>
     *     <li>Should return a User object with an updated ID.</li>
     * </ul>
     */

    //Note 1: Create would depend on registrationUnsuccesful exception as a way to check if the user exists in the DB.
    //When a user is created, their role is defaulted to employee. However, an admin can later change an employee to an admin.
    //Note 2: Return type needs to be changed to newUser object.
    public User create(User newUser) throws SQLException {
        String SQL = "INSERT INTO ers_users (ers_username,ers_password,user_first_name,user_last_name,user_email, user_role_id) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

        pstmt.setString(1, newUser.getUsername());
        pstmt.setString(2, newUser.getPassword());
        pstmt.setString(3, newUser.getFirstName());
        pstmt.setString(4, newUser.getLastName());
        pstmt.setString(5, newUser.getEmail());
        pstmt.setInt(6, 1);

        pstmt.execute();
        ResultSet keys = pstmt.getGeneratedKeys();

        if (keys.next()) {
            int id = keys.getInt(1);
            newUser.setId(id);
            return newUser;
        } else {
            throw new SQLException();
        }

    }

    /**
     The method below allows a user to change their password.
     Note: Change return type after passing console tests.
     Tested on 04/21/22. Does not work accordingly yet.
     */
    public String changePassword(String username, String newPassword) throws SQLException{
        String SQL = "UPDATE ers_users SET ers_password = ? WHERE ers_username = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
        pstmt.setString(1,newPassword);
        pstmt.setString(2,username);
        pstmt.executeUpdate();

        return "Password successfully changed!";
    }
}
