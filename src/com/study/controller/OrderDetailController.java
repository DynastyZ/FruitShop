package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.po.OrderDetail;
import com.study.service.OrderDetailService;
import com.study.utils.Pager;

@Controller
@RequestMapping("orderDetail")
public class OrderDetailController {

	@Autowired
	OrderDetailService orderDetailService;
	
	@RequestMapping("/findBySql")
	public String findBySql(Model model) {
		System.out.println("ssssssssssss");
		String sql = "select * from order_detail where 1=1 ";
		Pager<OrderDetail> pagers = orderDetailService.findBySqlRerturnEntity(sql);

		model.addAttribute("pagers", pagers);
		return "orderDetail/orderDetailList";
	}
}
