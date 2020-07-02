package com.study.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.base.BaseController;
import com.study.po.ItemCategory;
import com.study.po.Manage;
import com.study.service.ItemCategoryService;
import com.study.service.ManageService;
import com.study.utils.Consts;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Autowired
	ManageService manageService;
	
	@Autowired
	ItemCategoryService itemCategoryService;
	
	@RequestMapping("mLogin")
	public String mLogin() {
		return "login/managerLogin";
	}
	
	@RequestMapping("toLogin")
	public String toLogin(Manage manage,HttpServletRequest request) {
		Manage byEntity = manageService.getByEntity(manage);
		if(byEntity == null) {
			return "redirect:/login/mLoginOut";
		}
		request.getSession().setAttribute(Consts.MANAGE, byEntity);
		return "login/mindex";
	}
	
	@RequestMapping("mLoginOut")
	public String mLoginOut(HttpServletRequest request) {
		request.getSession().setAttribute(Consts.MANAGE, null);
		return "login/managerLogin";
	}
	
	@RequestMapping("uIndex")
	public String uLogin(Model model,HttpServletRequest request) {
		String sql = "select * from item_catagory where 1=1 and pid is null order by name";
		List<ItemCategory> FatherList = itemCategoryService.listBySqlReturnEntity(sql);
		FatherList. 
		return "login/uIndex";
	}
}
