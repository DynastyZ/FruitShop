package com.study.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.study.base.BaseController;
import com.study.po.Item;
import com.study.po.ItemCategory;
import com.study.service.ItemCategoryService;
import com.study.service.ItemService;
import com.study.utils.Pager;
import com.study.utils.SystemContext;
import com.study.utils.UUIDUtils;

@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 分页查询商品列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Model model, Item item){
        String sql = "select * from item where isDelete = 0 ";
        if(!isEmpty(item.getName())){
            sql += " and name like '%" + item.getName() + "%' ";
        }
        sql += " order by id desc";
        Pager<Item> pagers = itemService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",item);
        return "item/item";
    }
    
    @RequestMapping("add")
    public String add(Model model) {
    	String sql = "select * from item_category where isDelete=0 and pid is not null order by id";
		List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
		
    	model.addAttribute("categoryList",listBySqlReturnEntity);
    	return "item/add";
    }
    
    @RequestMapping("exAdd")
    public String exAdd(Item item,@RequestParam("file")CommonsMultipartFile[] files,HttpServletRequest request) throws IllegalStateException, IOException {
    	if(files.length>0) {
    		for(int i=0;i<files.length;i++) {
    			String n = UUIDUtils.create();
				String path = SystemContext.getRealPath()+"/resource/ueditor/upload/" +n+files[i].getOriginalFilename();
				File newFile = new File(path);
				files[i].transferTo(newFile);
				
    			if(i == 0) {
    				item.setUrl1(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
    			}
    			if (i == 1) {
                    item.setUrl2(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
                }
                if (i == 2) {
                    item.setUrl3(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
                }
                if (i == 3) {
                    item.setUrl4(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
                }
                if (i == 4) {
                    item.setUrl5(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
                }
    		}
    	}
    	
    	item.setScNum(0);
		item.setGmNum(0);
		item.setIsDelete(0);
		Integer idTwo = item.getCategoryIdTwo();
		ItemCategory byId = itemCategoryService.getById(idTwo);
		item.setCategoryIdOne(byId.getPid());
		
		itemService.insert(item);
		
    	return "redirect:/item/findBySql";
    }
    
    @RequestMapping("update")
	public String update(@RequestParam("id")Integer id,Model model){
		Item entity = itemService.load(id);
		String sql = "select * from item_category where isDelete=0 and pid is not null order by id";
		List<ItemCategory> listBySqlReturnEntity = itemCategoryService.listBySqlReturnEntity(sql);
    	model.addAttribute("categoryList",listBySqlReturnEntity);
		model.addAttribute("obj", entity);
		return "item/update";
	}
    
    @RequestMapping("exUpdate")
    public String exUpdate(Item item,@RequestParam("file")CommonsMultipartFile[] files,HttpServletRequest request) throws IllegalStateException, IOException {
    	if(files.length>0) {
    		for(int i=0;i<files.length;i++) {
    			String n = UUIDUtils.create();
				String path = SystemContext.getRealPath()+"/resource/ueditor/upload/" +n+files[i].getOriginalFilename();
				File newFile = new File(path);
				files[i].transferTo(newFile);
				
    			if(i == 0) {
    				if(files[i] != null) {
    					item.setUrl1(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
    				}
    				
    			}
    			if (i == 1) {
                    item.setUrl2(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
                }
                if (i == 2) {
                    item.setUrl3(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
                }
                if (i == 3) {
                    item.setUrl4(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
                }
                if (i == 4) {
                    item.setUrl5(request.getContextPath()+"/resource/ueditor/upload/" + n + files[i].getOriginalFilename());
                }
    		}
    	}
    	
		itemService.updateById(item);
		
    	return "redirect:/item/findBySql";
    }
    
    @RequestMapping("delete")
    public String delete(Integer id,Model model) {
    	Item load = itemService.load(id);
    	load.setIsDelete(1);
    	itemService.updateById(load);
    	return "redirect:/item/findBySql";
    }
    
}
