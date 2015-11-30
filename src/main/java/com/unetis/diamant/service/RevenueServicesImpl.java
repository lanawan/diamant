package com.unetis.diamant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unetis.diamant.dao.RevenueDao;
import com.unetis.diamant.model.Revenue;

public class RevenueServicesImpl implements RevenueServices {

	@Autowired
	RevenueDao revenueDao;
	@Override
	public boolean addEntity(List<Revenue> revenue) throws Exception {
		return revenueDao.addEntity(revenue);
	}
	@Override
	public Revenue getEntityById(int id) throws Exception {
		return revenueDao.getEntityById(id);
	}
	@Override
	public List<Revenue> getEntityList() throws Exception {
		return revenueDao.getEntityList();
	}
	@Override
	public boolean deleteEntity(int id) throws Exception {
		return revenueDao.deleteEntity(id);
	}

}
