package com.study.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.base.BaseController;
import com.study.po.News;
import com.study.service.NewsService;
import com.study.utils.Pager;

/**
 * 公告管理
 */
@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    /**
     * 公告列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(News news, Model model){
        String sql = "select * from news where 1=1 ";
        if(!isEmpty(news.getName())){
            sql += " and name like '%"+news.getName()+"%'";
        }
        sql += " order by id desc";
        Pager<News> pagers = newsService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",news);
        return "news/news";
    }
    
    @RequestMapping("/add")
    public String add(){
    	return"news/add";
    }
    
    @RequestMapping("/exAdd")
    public String exAdd(News news){
    	news.setAddTime(new Date());
    	newsService.insert(news);
    	return "redirect:/news/findBySql";
    }
    
    @RequestMapping("/update")
    public String update(Integer id,Model model){
    	News load = newsService.load(id);
    	model.addAttribute("obj", load);
    	return"news/update";
    }
    
    @RequestMapping("/exUpdate")
    public String exUpdate(News news){
    	newsService.updateById(news);
    	return "redirect:/news/findBySql";
    }
    
    @RequestMapping("/delete")
    public String delete(Integer id){
        newsService.deleteById(id);
        return "redirect:/news/findBySql";
    }
    
    
    
}