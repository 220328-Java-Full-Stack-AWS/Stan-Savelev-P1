package com.revature.services;

import com.revature.models.Reimbursement;
import com.revature.persistance.ReimbursementDAO;

import java.sql.SQLException;
import java.util.List;

/**
 * The ReimbursementService should handle the submission, processing,
 * and retrieval of Reimbursements for the ERS application.
 *
 * {@code process} and {@code getReimbursementsByStatus} are the minimum methods required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create Reimbursement</li>
 *     <li>Update Reimbursement</li>
 *     <li>Get Reimbursements by ID</li>
 *     <li>Get Reimbursements by Author</li>
 *     <li>Get Reimbursements by Resolver</li>
 *     <li>Get All Reimbursements</li>
 * </ul>
 */
public class ReimbursementService {


    private final ReimbursementDAO dao;

    public ReimbursementService() {
        this.dao = new ReimbursementDAO();
    }

    public void create(Reimbursement request) throws SQLException {
        dao.submitRequest(request);
    }

    public List<Reimbursement> read(int id) throws SQLException{
        return dao.getAllRequestsById(id);
    }

    public void update(int id, Reimbursement request) throws SQLException{
        dao.editByReimbId(id, request);
    }



/**
 * <ul>
 *     <li>Should ensure that the user is logged in as a Finance Manager</li>
 *     <li>Must throw exception if user is not logged in as a Finance Manager</li>
 *     <li>Should ensure that the reimbursement request exists</li>
 *     <li>Must throw exception if the reimbursement request is not found</li>
 *     <li>Should persist the updated reimbursement status with resolver information</li>
 *     <li>Must throw exception if persistence is unsuccessful</li>
 * </ul>
 *
 */

//    public Reimbursement process(Reimbursement unprocessedReimbursement, Status finalStatus, User resolver) {
//        return null;
//    }

    //    public List<Reimbursement> getReimbursementsByStatus(Status status) {
//        return Collections.emptyList();
//    }
}
