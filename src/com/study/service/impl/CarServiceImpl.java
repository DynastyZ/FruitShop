package com.study.service.impl;

import com.study.base.BaseDao;
import com.study.base.BaseServiceImpl;
import com.study.mapper.CarMapper;
import com.study.po.Car;
import com.study.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends BaseServiceImpl<Car> implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public BaseDao<Car> getBaseDao() {
        return carMapper;
    }
}
