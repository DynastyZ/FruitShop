package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.po.ItemCategory;
import com.study.service.ItemCategoryService;
import com.study.utils.Pager;

@Controller
@RequestMapping("itemCategory")
public class ItemCategoryController {
	
	@Autowired
	ItemCategoryService itemCategoryService;
	
	@RequestMapping("selectBySqlPage")
	public String selectBySqlPage(Model model){
		String sql = "select * from item_category where isDelete=0 and pid is null order by id";
		Pager<ItemCategory>  pagers = itemCategoryService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		
		return "itemCategory/itemCategory";
	}
	
	
	@RequestMapping("add")
	public String add(){
		return "itemCategory/add";
	}
	
	@RequestMapping("exAdd")
	public String exadd(ItemCategory itemCategory){
		itemCategory.setIsDelete(0);
		itemCategoryService.insert(itemCategory);
		return "redirect:/itemCategory/selectBySqlPage";
	}
	
	@RequestMapping("update")
	public String update(@RequestParam("id")Integer id,Model model){
		ItemCategory entity = itemCategoryService.load(id);
		model.addAttribute("obj", entity);
		return "itemCategory/update";
	}
	
	
	@RequestMapping("exUpdate")
	public String update(ItemCategory itemCategory){
		itemCategoryService.updateById(itemCategory);
		return "redirect:/itemCategory/selectBySqlPage";
	}
	
	@RequestMapping("delete")
	public String delete(Integer id){
		ItemCategory entity = itemCategoryService.load(id);
		entity.setIsDelete(1);
		//伪删除一级类目
		itemCategoryService.updateById(entity);
		//伪删除一级类目的子类目
		String sql = "update item_category set isDelete=1 where pid=" + id;
		itemCategoryService.updateBysql(sql);
		
		return "redirect:/itemCategory/selectBySqlPage";
	}
	
	@RequestMapping("selectBySqlPage2")
	public String selectBySqlPage2(@RequestParam(value="pid",required = false)Integer pid,Model model){
		String sql = "select * from item_category where isDelete=0 and pid="+ pid+" order by id";
		Pager<ItemCategory>  pagers = itemCategoryService.findBySqlRerturnEntity(sql);
		model.addAttribute("pagers", pagers);
		model.addAttribute("pid",pid);
		return "itemCategory/itemCategory2";
	}
	
	@RequestMapping("add2")
	public String add2(@RequestParam("pid")Integer pid,Model model){
		model.addAttribute("pid",pid);
		return "itemCategory/add2";
	}
	
	@RequestMapping("exAdd2")
	public String exadd2(ItemCategory itemCategory){
		itemCategory.setIsDelete(0);
		itemCategoryService.insert(itemCategory);
		return "redirect:/itemCategory/selectBySqlPage2";
	}
	
	
	
}
