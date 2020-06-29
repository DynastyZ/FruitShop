package com.study.service.impl;

import com.study.base.BaseDao;
import com.study.base.BaseServiceImpl;
import com.study.mapper.ItemCategoryMapper;
import com.study.po.ItemCategory;
import com.study.service.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl extends BaseServiceImpl<ItemCategory> implements ItemCategoryService {
    @Autowired
    ItemCategoryMapper itemCategoryMapper;

    @Override
    public BaseDao<ItemCategory> getBaseDao() {
        return itemCategoryMapper;
    }
}
