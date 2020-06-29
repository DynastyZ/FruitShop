package com.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.base.BaseDao;
import com.study.base.BaseServiceImpl;
import com.study.mapper.ManageMapper;
import com.study.po.Manage;
import com.study.service.ManageService;

public class ManageServiceImpl extends BaseServiceImpl<Manage> implements ManageService{

	@Autowired
	ManageMapper manageMapper;
	
	@Override
	public BaseDao<Manage> getBaseDao() {
		// TODO Auto-generated method stub
		return this.manageMapper;
	}

}
