package com.unetis.diamant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unetis.diamant.dao.RevenueDao;
import com.unetis.diamant.model.Revenue;

/**
 * Класс обслуживания контроллера RevenueController
 * 
 * Методы-сервисы, вызываемые напрямую из контроллера RevenueController
 * 
 * @author Stepan
 *
 */
public class RevenueServicesImpl implements RevenueServices {

	@Autowired
	RevenueDao revenueDao;
	
	/**
	 * Добавть выручку в базу см. RevenueDaoImpl 
	 */
	@Override
	public void addEntity(List<Revenue> revenue) {
		revenueDao.addEntity(revenue);
	}
/*
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
*/
}
