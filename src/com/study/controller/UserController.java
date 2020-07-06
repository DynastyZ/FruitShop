package com.study.controller;

import javax.servlet.http.HttpServletRequest;

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
	
	/**
     * 查看用户信息
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/view")
    public String view(Model model, HttpServletRequest request){
        Object attribute = request.getSession().getAttribute("userId");
        if(attribute==null){
            return "redirect:/login/uLogin";
        }
        Integer userId = Integer.valueOf(attribute.toString());
        User obj = userService.load(userId);
        model.addAttribute("obj",obj);
        return "user/view";
    }
	
    @RequestMapping("/exUpdate")
    public String exUpdate(User user,Model model, HttpServletRequest request){
        Object attribute = request.getSession().getAttribute("userId");
        if(attribute==null){
            return "redirect:/login/uLogin";
        }
        Integer userId = Integer.valueOf(attribute.toString());
        user.setId(userId);
        userService.updateById(user);
        model.addAttribute("obj", user);	
        return "user/view";
    }
    
}
