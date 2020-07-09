package com.study.controller;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.study.base.BaseController;
import com.study.po.Message;
import com.study.service.MessageService;
import com.study.utils.Pager;

/**
 * 公告管理
 */
@Controller
@RequestMapping("/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    /**
     * 公告列表
     */
    @RequestMapping("/findBySql")
    public String findBySql(Message message, Model model){
        String sql = "select * from message where 1=1 ";
        if(!isEmpty(message.getName())){
            sql += " and name like '%"+message.getName()+"%'";
        }
        sql += " order by id desc";
        Pager<Message> pagers = messageService.findBySqlRerturnEntity(sql);
        model.addAttribute("pagers",pagers);
        model.addAttribute("obj",message);
        return "message/message";
    }
    
  
    @RequestMapping("/delete") 
    public String delete(Integer id){
    	messageService.deleteById(id);
        return "redirect:/message/findBySql";
    }
    
    
    @RequestMapping("/add")
    public String add(){
    	return "message/add";
    }
    
    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(Message mes){
    	JSONObject js = new JSONObject();
    	messageService.insert(mes);
    	return js.toJSONString();
    }
    
    
}