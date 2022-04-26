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
//
//     * <ul>
//     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
//     *     <li>Should throw an exception if the update is unsuccessful.</li>
//     *     <li>Should return a Reimbursement object with updated information.</li>
//     * </ul>
//     */


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


    //updateAmountById works when SQL command is executed in Dbeaver but not through the DAO?
    // Change to void when console can past test
    //Update(edit) finally works! (04/25/2022). The issue was the SQL command
    public Reimbursement editByReimbId(int id, Reimbursement request) throws SQLException {
        LocalDate todaysDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String SQL = "UPDATE ers_reimbursement SET reimb_amount = ? ,reimb_submitted = ?, reimb_description = ? , reimb_type_id = ? WHERE reimb_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);

        pstmt.setDouble(1, request.getAmount());
        pstmt.setString(2, todaysDate.format(pattern));
        pstmt.setString(3, request.getDesc());
        pstmt.setInt(4, request.getTypeId());
        pstmt.setInt(5, id);

        pstmt.executeUpdate();

        return request;
    }

    //To clarify, this method deletes the row with the uniquely assigned reimb_id.
    //Tested and works!(04/23/2022)
    public void cancelByReimbId(int id) throws SQLException {
        String SQL = "DELETE FROM ers_reimbursement WHERE reimb_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    //Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
    public List<Reimbursement> viewByStatus(Status status) throws SQLException {
        ArrayList<Reimbursement> resultArray = new ArrayList<>();
        String SQL = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);

        if(status == Status.PENDING) {
            pstmt.setInt(1, 1);
        }else if( status == Status.APPROVED){
            pstmt.setInt(1, 2);
        }else{
            pstmt.setInt(1, 3);
        }
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
}
