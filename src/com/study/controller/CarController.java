package com.study.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.study.po.Car;
import com.study.po.Item;
import com.study.service.CarService;
import com.study.service.ItemService;
import com.study.utils.Pager;

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
    
    @RequestMapping("findBySql")
    public String findBySql(Model model,HttpServletRequest request) {
    	Object attribute = request.getSession().getAttribute("userId");
		if (attribute == null) {
			return "redirect:/login/uLogin";
		}
		Integer userId = Integer.valueOf(attribute.toString());
		String sql = "select * from car where user_id=" + userId +" order by id desc";
		List<Car> carList = carService.listBySqlReturnEntity(sql);
		
		model.addAttribute("carList", carList);
    	return "car/myCar";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(Integer id) {
    	carService.deleteById(id);
    	return "delete success!";
    }
}
