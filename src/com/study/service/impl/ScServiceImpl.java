package com.study.service.impl;

import com.study.base.BaseDao;
import com.study.base.BaseServiceImpl;
import com.study.mapper.ScMapper;
import com.study.po.Sc;
import com.study.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScServiceImpl extends BaseServiceImpl<Sc> implements ScService {

    @Autowired
    private ScMapper scMapper;

    @Override
    public BaseDao<Sc> getBaseDao() {
        return scMapper;
    }
}
