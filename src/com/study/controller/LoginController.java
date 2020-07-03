package com.study.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.base.BaseController;
import com.study.po.CategoryDto;
import com.study.po.Item;
import com.study.po.ItemCategory;
import com.study.po.Manage;
import com.study.po.User;
import com.study.service.ItemCategoryService;
import com.study.service.ItemService;
import com.study.service.ManageService;
import com.study.service.UserService;
import com.study.utils.Consts;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	
	@Autowired
	ManageService manageService;
	@Autowired
	ItemCategoryService itemCategoryService;
	@Autowired
	ItemService itemService;
	@Autowired
	UserService userService;
	
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
		String sql = "select * from item_category where 1=1 and pid is null order by name";
		List<ItemCategory> FatherList = itemCategoryService.listBySqlReturnEntity(sql);
		if(!CollectionUtils.isEmpty(FatherList)) {
			List<CategoryDto> categoryList = new ArrayList<CategoryDto>();
			for(ItemCategory  ic : FatherList) {
				String sql2 = "select * from item_category where 1=1 and pid=" + ic.getId();
				List<ItemCategory> childrens = itemCategoryService.listBySqlReturnEntity(sql2);
				CategoryDto cDto = new CategoryDto();
				cDto.setFather(ic);
				cDto.setChildrens(childrens);
				categoryList.add(cDto);
			}
			model.addAttribute("lbs",categoryList);
		}
		//折扣商品
		List<Item> zkList = itemService.listBySqlReturnEntity("select * from item where 1=1  order by zk  limit 0,10");
		model.addAttribute("zkList", zkList);
		//热销商品
		List<Item> hotList = itemService.listBySqlReturnEntity("select * from item where 1=1  order by gmNum desc limit 0,10");
		model.addAttribute("hotList", hotList);
		
		return "login/uIndex";
	}
	
	@RequestMapping("register")
	public String register() {
		return "login/register";
	}
	
	@RequestMapping("uLogout")
	public String uLogOut(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:/login/uIndex";
	}
	
	@RequestMapping("toRegister")
	public String toRegister(User user,Model model) {
		userService.insert(user);
		model.addAttribute("userId", user.getId());
		model.addAttribute("username", user.getUserName());
		return "forward:/login/uIndex";
	}
	
	@RequestMapping("uLogin")
	public String uLogin() {
		return "login/uLogin";
	}
	
	@RequestMapping("utoLogin")
	public String utoLogin(User user,Model model,HttpServletRequest request) {
		User getEntity = userService.getByEntity(user);
		if(getEntity == null) {
			return "redirect:/login/uIndex";
		}
		request.getSession().setAttribute("user",getEntity);
		model.addAttribute("userId", getEntity.getId());
		model.addAttribute("username", getEntity.getUserName());
		return "forward:/login/uIndex";
	}
	
}
