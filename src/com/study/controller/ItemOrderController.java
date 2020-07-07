package com.study.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
public class ItemOrderController extends BaseController {

	@Autowired
	ItemOrderService itemOrderService;

	@RequestMapping("findBySql")
	public String findBySql(ItemOrder itemOrder, Model model) {
		String sql = "select * from item_order where 1=1 ";
		if (itemOrder.getCode() != null) {
			sql += "and code like '%" + itemOrder.getCode() + "%'";
		}
		sql += "order by id ";

		Pager<ItemOrder> pagers = itemOrderService.findBySqlRerturnEntity(sql);

		model.addAttribute("pagers", pagers);
		model.addAttribute("obj", itemOrder);
		return "itemOrder/itemOrder";
	}

	@RequestMapping("my")
	public String my(Model model, HttpServletRequest request) {
		Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null) {
			return "redirect:/login/uLogin";
		}

		Integer userId = Integer.valueOf(attribute.toString());
		
		// 全部订单
		String sql = "select * from item_order where user_id=" + userId + " order by id desc";
		List<ItemOrder> all = itemOrderService.listBySqlReturnEntity(sql);
		Pager<ItemOrder> pager = itemOrderService.findBySqlRerturnEntity(sql);
		// 待发货
		String sql2 = "select * from item_order where user_id=" + userId + " and status=0 order by id desc";
		List<ItemOrder> dfh = itemOrderService.listBySqlReturnEntity(sql2);
		// 已取消
		String sql3 = "select * from item_order where user_id=" + userId + " and status=1 order by id desc";
		List<ItemOrder> yqx = itemOrderService.listBySqlReturnEntity(sql3);
		// 待收货
		String sql4 = "select * from item_order where user_id=" + userId + " and status=2 order by id desc";
		List<ItemOrder> dsh = itemOrderService.listBySqlReturnEntity(sql4);
		// 已收货
		String sql5 = "select * from item_order where user_id=" + userId + " and status=3 order by id desc";
		List<ItemOrder> ysh = itemOrderService.listBySqlReturnEntity(sql5);
		model.addAttribute("pagers", pager);
		model.addAttribute("all", all);
		model.addAttribute("dfh", dfh);
		model.addAttribute("yqx", yqx);
		model.addAttribute("dsh", dsh);
		model.addAttribute("ysh", ysh);
		return "itemOrder/my";
	}

}
