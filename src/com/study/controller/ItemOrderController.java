package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.base.BaseController;
import com.study.po.ItemOrder;
import com.study.service.ItemOrderService;
import com.study.utils.Pager;

@Controller
@RequestMapping("itemOrder")
public class ItemOrderController extends BaseController{
	
	@Autowired
	ItemOrderService itemOrderService;
	
	@RequestMapping("findBySql")
	public String findBySql(ItemOrder itemOrder,Model model) {
		String sql = "select * from item_order where 1=1 ";
		if(itemOrder.getCode() != null) {
			sql += "and code like '%" + itemOrder.getCode() + "%'";
		}
		sql += "order by id ";
		
		Pager<ItemOrder> pagers = itemOrderService.findBySqlRerturnEntity(sql);
		
		model.addAttribute("pagers", pagers);
		model.addAttribute("obj", itemOrder);
		return "itemOrder/itemOrder";
	}

}
