package com.revature.services;

import java.sql.SQLException;
import java.util.Optional;
import com.revature.models.User;
import com.revature.persistance.UserDAO;

import java.util.List;
/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	private final UserDAO dao;

	public UserService() {
		this.dao = new UserDAO();
	}

	//create works
	public User create(User user) throws SQLException {
		return dao.create(user);
	}
	//read works
	public User read(int id) throws SQLException {
		return dao.getById(id);
	}
	public List<User> read() throws SQLException {
		return dao.getAll();
	}

	public Optional<User> read(String username) throws SQLException {
		return dao.getByUsername(username);
	}
	public void update(int resolver, int reimbId, int status) throws SQLException {
		dao.processRequest(resolver,reimbId,status);
	}
	//update works
	public void update(int id) throws SQLException {
		dao.promoteToAdminById(id);
	}
//	public void delete(int id) throws SQLException {
//		dao.deleteById(id);
//	}

}

