package com.newshop.service.impl;

import javax.inject.Inject;

import com.newshop.dao.IUserDAO;
import com.newshop.model.UserModel;
import com.newshop.service.IUserService;

public class UserService implements IUserService{

	@Inject IUserDAO userDAO;
	
	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
