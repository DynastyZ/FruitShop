package com.study.service.impl;

import com.study.base.BaseDao;
import com.study.base.BaseServiceImpl;
import com.study.mapper.ItemOrderMapper;
import com.study.po.ItemOrder;
import com.study.service.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemOrderServiceImpl extends BaseServiceImpl<ItemOrder> implements ItemOrderService {

    @Autowired
    private ItemOrderMapper itemOrderMapper;

    @Override
    public BaseDao<ItemOrder> getBaseDao() {
        return itemOrderMapper;
    }
}
