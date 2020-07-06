package com.study.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.study.po.Car;
import com.study.po.Item;
import com.study.service.CarService;
import com.study.service.ItemService;

/**
 * 购物车
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private ItemService itemService;

    @RequestMapping("/exAdd")
    @ResponseBody
    public String exAdd(Car car, HttpServletRequest request){
    	JSONObject js = new JSONObject();
    	Integer userId = (Integer)request.getSession().getAttribute("userId");
    	if(userId == null) {
    		js.put("result", 0);
    		return js.toJSONString();
    	}
    	car.setUserId(userId);
		Item load = itemService.load(car.getItemId());
    	Double pri = Double.valueOf(load.getPrice());
    	if(load.getZk() != null ) {
    		pri = pri * load.getZk() / 10;
    		BigDecimal bg = new BigDecimal(pri).setScale(2, RoundingMode.UP);
    		car.setPrice(bg.doubleValue());
    	}
    	Double toatl = pri * car.getNum();
    	BigDecimal bg = new BigDecimal(toatl).setScale(2, RoundingMode.UP);
    	Double t = bg.doubleValue();
    	car.setTotal(t.toString());
    	carService.insert(car);
    	js.put("result", 1);
		return js.toJSONString();
    }
}
