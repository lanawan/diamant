package com.unetis.diamant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unetis.diamant.dao.TrafficDao;
import com.unetis.diamant.model.Traffic;

/**
 * Класс обслуживания контроллера TrafficController
 * 
 * Методы-сервисы, вызываемые напрямую из контроллера TrafficController
 * 
 * @author Stepan
 *
 */
public class TrafficServicesImpl implements TrafficServices {

	@Autowired
	TrafficDao trafficDao;
	
	/**
	 * Добавть трафик в базу см. TrafficDaoImpl 
	 */
	@Override
	public void addEntity(List<Traffic> traffic) {
		trafficDao.addEntity(traffic);
	}
	
/*
	@Override
	public Traffic getEntityById(int id) throws Exception {
		return trafficDao.getEntityById(id);
	}
	@Override
	public List<Traffic> getEntityList() throws Exception {
		return trafficDao.getEntityList();
	}
	@Override
	public boolean deleteEntity(int id) throws Exception {
		return trafficDao.deleteEntity(id);
	}
*/
}
