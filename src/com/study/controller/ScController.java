package com.study.controller;

import com.study.po.Item;
import com.study.po.Sc;
import com.study.po.User;
import com.study.service.ItemService;
import com.study.service.ScService;
import com.study.utils.Consts;
import com.study.utils.Pager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏
 */
@Controller
@RequestMapping("/sc")
public class ScController {

    @Autowired
    private ScService scService;

    @Autowired
    private ItemService itemService;

    @RequestMapping("/exAdd")
    public String exAdd(@RequestParam("itemId") Integer itemId,HttpServletRequest request) {
    	Integer userID = (Integer)request.getSession().getAttribute("userId");
    	if(userID == null){
    		return "redirect:/login/uLogin";
    	}
    	//给sc对象赋值
    	Sc sc = new Sc();
    	sc.setItemId(itemId);
    	sc.setUserId(userID);
    	//商品收藏数量+1
    	Item load = itemService.load(itemId);
    	load.setScNum(load.getScNum()+1);
    	itemService.update(load);
    	//向SC表添加记录
    	scService.insert(sc);
    	
    	return "redirect:/sc/findBySql";
    }
    
    @RequestMapping("/findBySql")
    public String findBySql(HttpServletRequest request,Model model) {
    	Integer userID = (Integer)request.getSession().getAttribute("userId");
    	if(userID == null){
    		return "redirect:/login/uLogin";
    	}
    	
    	String sql = "select * from sc where user_id="+ userID +" order by id desc" ;
    	Pager<Sc> pagers = scService.findBySqlRerturnEntity(sql);
    	model.addAttribute("pagers", pagers);
    	return "sc/view";
    }
    
    @RequestMapping("/cancle")
    public String cancle(HttpServletRequest request,Integer id) {
    	Integer userID = (Integer)request.getSession().getAttribute("userId");
    	if(userID == null){
    		return "redirect:/login/uLogin";
    	}
    	
    	scService.deleteById(id);
    	return "redirect:/sc/findBySql";
    }
    
}
