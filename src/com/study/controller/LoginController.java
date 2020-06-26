package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.base.BaseController;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{

	@RequestMapping("mLogin")
	public String mLogin() {
		return "login/managerLogin";
	}
	
	@RequestMapping("toLogin")
	public String toLogin() {
		System.out.println("success@");
		return "login/managerLogin";
	}
}
