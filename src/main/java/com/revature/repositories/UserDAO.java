package com.revature.repositories;

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
            Connection conn = ConnectionManager.getConnection();
            String SQL = "SELECT * FROM ers_users WHERE ers_username = ?";
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,username);
            ResultSet rs = pstmt.executeQuery();

            User tempUser = new User();

            if (rs.next()) {
                tempUser.setId(rs.getInt("ers_user_id"));
                tempUser.setUsername(rs.getString("ers_username"));
                tempUser.setPassword(rs.getString("ers_password"));
                int roleId = rs.getInt("user_role_id");
                if (roleId == 1) {
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
    public User create(User userToBeRegistered) {

        return userToBeRegistered;
    }
}
