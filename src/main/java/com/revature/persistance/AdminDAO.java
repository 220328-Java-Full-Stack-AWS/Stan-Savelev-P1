package com.revature.persistance;

import com.revature.models.User;
import com.revature.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
//This DAO is intended for Admin use only.

public class AdminDAO extends UserDAO {
    @Override
    public Optional<User> getByUsername(String username) throws SQLException {
        return super.getByUsername(username);
    }

    @Override
    public String create(User newUser) throws SQLException {
        return super.create(newUser);
    }

    @Override
    public String changePassword(String username, String newPassword) throws SQLException {
        return super.changePassword(username, newPassword);
    }

    /**
     * promoteToAdmin promotes a user in the DB to an Admin
     * Change to void after passing console test
     * 04/21/22 This method has not been tested yet.
     */
    public void promoteToAdmin(String username) throws SQLException{
        String SQL = "UPDATE ers_user SET user_role_id = 2 WHERE ers_username = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
        pstmt.setString(1, username);
        pstmt.executeUpdate();

    }

}
