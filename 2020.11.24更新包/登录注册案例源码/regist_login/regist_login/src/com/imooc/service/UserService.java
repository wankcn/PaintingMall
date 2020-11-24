package com.imooc.service;

import java.util.List;

import com.imooc.domain.User;

public interface UserService {
	public void regist(List<User> userList,User user);

	public User login(List<User> userList, User user);
}
