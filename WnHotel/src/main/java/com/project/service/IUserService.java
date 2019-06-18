package com.project.service;

import com.project.bean.User;

public interface IUserService {
	
	public String findUserByName(String name);
	
	public void reg(User user);
}
