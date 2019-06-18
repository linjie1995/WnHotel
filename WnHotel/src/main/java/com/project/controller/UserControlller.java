package com.project.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.bean.User;
import com.project.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserControlller {
	
	@Autowired
	IUserService service;
	
	@GetMapping
	public String login(User user) {
		Subject currentUser = SecurityUtils.getSubject();
		if (!currentUser.isAuthenticated()) {
	        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPassWord());
	        try {
	        	//调用login进行认证
	            currentUser.login(token);
	            System.out.println("认证成功");
	            return "redirect:/index.html";
	        } 
	        // 父异常。认证失败异常
	        catch (AuthenticationException ae) {
	            //unexpected condition?  error?
	        	System.out.println("异常不详：自己解决");
	        	return "redirect:/login.html";
            }
		}
		 return "redirect:/index.html";
	}
	
	@PostMapping
	public String reg(User user) {
		System.out.println(user);
		Object obj = new SimpleHash("MD5", user.getPassWord(), user.getName(), 1024);
		user.setPassWord(obj.toString());
		service.reg(user);
		return "redirect:login.html";
	}
	
	
	@GetMapping("/find/{passWord}")
	@ResponseBody
	public String text(@PathVariable("passWord")String name) {
		String pwd= service.findUserByName(name);
		System.out.println(pwd);
		return pwd;
	}
}

