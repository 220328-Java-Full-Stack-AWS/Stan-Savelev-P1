package com.revature.persistance;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.models.Role;
import com.revature.util.ConnectionManager;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    /**
     * Should retrieve a User from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<User> getById(int id) throws SQLException{
            User tempUser = new User();

            String SQL = "SELECT * FROM ers_users WHERE ers_users_id = ?";
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();


            while(rs.next()) {
                tempUser.setId(rs.getInt("ers_users_id"));
                tempUser.setUsername(rs.getString("ers_username"));
                tempUser.setPassword(rs.getString("ers_password"));
                tempUser.setFirstName(rs.getString("user_first_name"));
                tempUser.setLastName(rs.getString("user_last_name"));
                tempUser.setEmail(rs.getString("user_email"));
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

    //The below methods checks if the user is an admin
//    public boolean checkIfAdmin(int userId) throws SQLException{
//        String SQL = "SELECT user_role_id FROM ers_users WHERE ers_user_id = ?";
//        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
//        pstmt.setInt(1,userId);
//        ResultSet rs = pstmt.executeQuery();
//        boolean admin = false;
//    }

    //Untested method
    public List<User> getAll() throws SQLException{
        ArrayList list = new ArrayList();

        String SQL = "SELECT * FROM ers_users";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            User tempUser = new User();
            tempUser.setId(rs.getInt("ers_users_id"));
            tempUser.setUsername(rs.getString("ers_username"));
            tempUser.setPassword(rs.getString("ers_password"));
            tempUser.setFirstName(rs.getString("user_first_name"));
            tempUser.setLastName(rs.getString("user_last_name"));
            tempUser.setEmail(rs.getString("user_email"));
            int userRoleId = rs.getInt("user_role_id");
            if (userRoleId == 1) {
                tempUser.setRole(Role.EMPLOYEE);
            } else {
                tempUser.setRole(Role.FINANCE_MANAGER);
            }
            list.add(tempUser);
        }

        return list;
    }

    /**
     * promoteToAdmin promotes a user in the DB to an Admin
     * Change to void after passing console test
     * 04/21/22 This method has not been tested yet.
     * @param userId
     */
    public String promoteToAdminById(int userId) throws SQLException{
        String SQL = "UPDATE ers_users SET user_role_id = ? WHERE ers_user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);

        pstmt.setInt(1,2);
        pstmt.setInt(2, userId);
        pstmt.executeUpdate();
        return "Update made";
    }

    //Returns a list of users with a specific status label.
    //Works as of 04/25/2022
    public List<Reimbursement> getByStatus(int status) throws SQLException {
        ArrayList<Reimbursement> resultArray = new ArrayList<>();


        String SQL = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);;
        pstmt.setInt(1, status);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Reimbursement tempReimb = new Reimbursement();

            tempReimb.setId(rs.getInt("reimb_id"));
            tempReimb.setAmount(rs.getDouble("reimb_amount"));
            tempReimb.setDesc(rs.getString("reimb_description"));
            tempReimb.setAuthor(rs.getInt("reimb_author"));
            tempReimb.setResolver(rs.getInt("reimb_resolver"));
            tempReimb.setTypeId(rs.getInt("reimb_type_id"));

            resultArray.add((tempReimb));
        }

        return resultArray;
    }

    //Approves/Denies requests.
    //Works as of (04/25/2022)
    public String processRequest(int resolver, int reimbId, int status) throws SQLException {
        LocalDate todaysDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String SQL = "UPDATE ers_reimbursement  SET reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ? WHERE reimb_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);

        pstmt.setString(1,todaysDate.format(pattern));
        pstmt.setInt(2,resolver);
        pstmt.setInt(3, status);
        pstmt.setInt(4, reimbId);

        pstmt.executeUpdate();
        return "Request resolved!";
    }

    //Deletes user by ers_user_id
    //Not yet tested
    public void deleteById(int id) throws SQLException {
        String SQL = "DELETE FROM ers_users WHERE ers_user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }


}
