package com.revature.persistance;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//This DAO is intended for Admin use only.
//It is created when an admin logs in.
//A ReimbursementDAO is invoked as well.
public class AdminDAO extends UserDAO {
    @Override
    public Optional<User> getByUsername(String username) throws SQLException {
        return super.getByUsername(username);
    }

    @Override
    public String changePassword(String username, String newPassword) throws SQLException {
        return super.changePassword(username, newPassword);
    }




//    Admin/Finance Manager:

//    As an admin, I can approve expense reimbursements
//    As an admin, I can deny expense reimbursements
//    As an admin, I can filter requests by status
//    (COMPLETED)As an admin, I can change a user's role between admin and user

    //Approves/Denies requests.
    public String resolveRequest(int resolver, int reimbId, int status) throws SQLException {
        LocalDate todaysDate = LocalDate.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String SQL = "UPDATE ers_reimbursement  SET reimb_resolved = ?, reimb_resolver = ?, reimb_status_id = ? WHERE reimb_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);

        pstmt.setString(1,todaysDate.format(pattern));
        pstmt.setInt(2,resolver);
        pstmt.setInt(3, status);
        pstmt.setInt(4, reimbId);

        return "Request resolved!";
    }

    //Returns a list of users with a specific status label.
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

    /**
     * promoteToAdmin promotes a user in the DB to an Admin
     * Change to void after passing console test
     * 04/21/22 This method has not been tested yet.
     */
    public void promoteToAdminById(int userId) throws SQLException{
        String SQL = "UPDATE ers_users SET user_role_id = ? WHERE ers_user_id = ?";
        PreparedStatement pstmt = ConnectionManager.getConnection().prepareStatement(SQL);

        pstmt.setInt(1,2);
        pstmt.setInt(2, userId);
        pstmt.executeUpdate();
    }
}
