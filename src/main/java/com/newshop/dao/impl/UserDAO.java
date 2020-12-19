package com.newshop.dao.impl;

import java.util.List;

import com.newshop.dao.IUserDAO;
import com.newshop.mapper.UserMapper;
import com.newshop.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql =new StringBuilder("Select * from user as u ");
		sql.append(" inner join role as r on r.id = u.roleid");
		sql.append(" where userName = ? AND password= ? AND status =? ");
		List<UserModel> user = query(sql.toString(),new UserMapper() ,userName , password , status);
		return user.isEmpty() ? null : user.get(0);
	}	
}