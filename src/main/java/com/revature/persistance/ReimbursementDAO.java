package com.revature.persistance;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.revature.models.Status;

import javax.swing.text.html.Option;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
//    public Optional<User> getByUsername(String username) throws SQLException {
//            String SQL = "SELECT * FROM ers_users WHERE ers_username = ?";
//            PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
//            pstmt.setString(1,username);
//            ResultSet rs = pstmt.executeQuery();
//            User tempUser = new User();
//
//            if (rs.next()) {
//                tempUser.setId(rs.getInt("ers_user_id"));
//                tempUser.setUsername(rs.getString("ers_username"));
//                tempUser.setPassword(rs.getString("ers_password"));
//                int userRoleId = rs.getInt("user_role_id");
//
//                if (userRoleId == 1) {
//                    tempUser.setRole(Role.EMPLOYEE);
//                } else {
//                    tempUser.setRole(Role.FINANCE_MANAGER);
//                }
//                return Optional.of(tempUser);
//            }
//
//            return Optional.empty();
//        }
//    public Optional<Reimbursement> getById(int id) throws SQLException {
//        String SQL = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
//        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
//
//        pstmt.setInt(1,id);
//        ResultSet rs = pstmt.executeQuery();
//        Reimbursement tempReimb = new Reimbursement();
//
//        if (rs.next()) {
////            tempReimb
//            return Optional.of(tempReimb);
//        }
//
//        return Optional.empty();
//
////
//    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    //See admin DAO
    public List<Reimbursement> getByStatus(Status status) {
//
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */


    //Change submitRequest to void after test
    //Works as of 04/24/2022
    public String submitRequest(Reimbursement request) throws SQLException {

        LocalDate todaysDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String SQL = "INSERT INTO ers_reimbursement"
                    + "(reimb_amount,reimb_submitted,reimb_description,reimb_author,reimb_status_id, reimb_type_id)"
                    + "VALUES (?,?,?,?,?,?)";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);

        pstmt.setDouble(1, request.getAmount());
        pstmt.setString(2, todaysDate.format(pattern));
        pstmt.setString(3, request.getDesc());
        pstmt.setInt(4, request.getAuthor()); //This is an employees ID number
        pstmt.setInt(5, 1); //Defaulted to pending until an admin makes changes.
        pstmt.setInt(6, request.getTypeId());

        pstmt.execute();
        ResultSet keys = pstmt.getGeneratedKeys();

        if (keys.next()) {
            int id = keys.getInt(1);
            request.setId(id);
        } else {
            throw new SQLException();
        }

        return "Request successfully submitted!";
    }
    public Reimbursement readById(int id) throws SQLException{return null;}

    //updateAmountById works when SQL command is executed in Dbeaver but not through the DAO?
    //Does an update require an object input?
    public void editAmountById(int id, double amount) throws SQLException {
        String SQL = "UPDATE ers_reimbursement SET reimb_amount = ? WHERE reimb_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);
        pstmt.setInt(1, id);
        pstmt.setDouble(2, amount);
        pstmt.executeUpdate();

    }

    //To clarify, this method deletes the row with the uniquely assigned reimb_id.
    //Tested and works!(04/23/2022)
    public void cancelById(int id) throws SQLException {
        String SQL = "DELETE FROM ers_reimbursement WHERE reimb_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    //To be completed by today(04/24/2022)

}
