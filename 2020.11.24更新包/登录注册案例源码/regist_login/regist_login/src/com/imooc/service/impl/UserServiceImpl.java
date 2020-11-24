package com.imooc.service.impl;

import java.util.List;

import com.imooc.domain.User;
import com.imooc.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public void regist(List<User> userList, User user) {
		// 保存用户信息:
		userList.add(user);
	}

	@Override
	public User login(List<User> userList, User user) {
		
		for (User existUser : userList) {
			if(existUser.getUsername().equals(user.getUsername()) && existUser.getPassword().equals(user.getPassword())){
				return existUser;
			}
		}
		
		return null;
	}

}
