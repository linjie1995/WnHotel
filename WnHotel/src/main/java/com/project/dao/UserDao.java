package com.project.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.project.bean.User;

public interface UserDao {
	
	@Select("select password from user where name = #{name}")
	public String findUserByName(String name);
	
	@Insert("insert into user(name,passWord) values(#{name},#{passWord})")
	public int insert(User user);
}
