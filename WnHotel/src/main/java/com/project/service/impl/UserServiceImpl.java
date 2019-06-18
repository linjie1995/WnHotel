package com.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bean.User;
import com.project.dao.UserDao;
import com.project.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	UserDao dao;
	
	@Override
	public String findUserByName(String name) {
		
		return dao.findUserByName(name);
	}

	@Override
	public void reg(User user) {
		System.out.println("注册");
		int i=dao.insert(user);
		System.out.println(i);
		
	}

}
