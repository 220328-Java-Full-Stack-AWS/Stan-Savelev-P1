package com.revature.persistance;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ReimbursementDAO {

    /**
     * Should retrieve a Reimbursement from the DB with the corresponding id or an empty optional if there is no match.
     */
    public Optional<Reimbursement> getById(int id) throws SQLException {
//        Connection conn = ConnectionManager.getConnection();
//        String SQL = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";
//        PreparedStatement pstmt = conn.prepareStatement(SQL);
//        pstmt.setInt(1,id);
//        ResultSet rs = pstmt.executeQuery();
//        Reimbursement tempReimb = new Reimbursement();
//
//        if (rs.next()) {
//            tempReimb.setId(rs.getInt("ers_user_id"));
//            tempReimb.setStatus(rs.getString("ers_username"));
//            tempReimb.setAuthor(rs.getString("ers_password"));
//            tempReimb.setResolver(rs.getString("ers_password"));
//            tempReimb.setAmount(rs.getString("ers_password"));
//            return Optional.of(tempReimb);
//        }

        return Optional.empty();
    }

    /**
     * Should retrieve a List of Reimbursements from the DB with the corresponding Status or an empty List if there are no matches.
     */
    public List<Reimbursement> getByStatus(Status status) {
        return Collections.emptyList();
    }

    /**
     * <ul>
     *     <li>Should Update an existing Reimbursement record in the DB with the provided information.</li>
     *     <li>Should throw an exception if the update is unsuccessful.</li>
     *     <li>Should return a Reimbursement object with updated information.</li>
     * </ul>
     */
    public Reimbursement update(Reimbursement unprocessedReimbursement) {
    	return null;
    }
}
