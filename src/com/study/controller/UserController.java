package com.study.controller;

import org.apache.ibatis.io.ResolverUtil.IsA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.base.BaseController;
import com.study.po.User;
import com.study.service.UserService;
import com.study.utils.Pager;

@Controller
@RequestMapping("user")
public class UserController extends BaseController{

	@Autowired
	UserService userService;
	
	@RequestMapping("findBySql")
	public String findBySql(User user,Model model) {
		String sql = "select * from user where 1=1 ";
		if(!isEmpty(user.getUserName())) {
			sql += " and userName like '%"+ user.getUserName() +"%' ";
		}
		sql += " order by id";
		Pager<User> pagers = userService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		model.addAttribute("obj", user);
		return "user/user";
	}
	
	
}
