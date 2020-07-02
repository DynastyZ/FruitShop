package com.study.service.impl;

import com.study.base.BaseDao;
import com.study.base.BaseServiceImpl;
import com.study.mapper.OrderDetailMapper;
import com.study.po.OrderDetail;
import com.study.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl extends BaseServiceImpl<OrderDetail> implements OrderDetailService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public BaseDao<OrderDetail> getBaseDao() {
        return orderDetailMapper;
    }
}
