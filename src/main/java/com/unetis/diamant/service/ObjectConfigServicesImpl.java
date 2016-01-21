package com.unetis.diamant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unetis.diamant.dao.ObjectConfigDao;
import com.unetis.diamant.model.ObjectConfig;
/**
 * Класс обслуживания контроллера ObjectConfig
 * 
 * Методы-сервисы, вызываемые напрямую из контроллера ObjectConfigController
 * 
 * @author Stepan
 *
 */
public class ObjectConfigServicesImpl implements ObjectConfigServices {
	@Autowired
	ObjectConfigDao objectConfigDao;
	
/*
	Пока что не используется. Будет использоваться, когда будет админка.

	@Override
	public boolean addEntity(ObjectConfig objectConfig) throws Exception {
		return objectConfigDao.addEntity(objectConfig);
	}
	@Override
	public ObjectConfig getEntityById(int id) throws Exception {
		return objectConfigDao.getEntityById(id);
	}
	@Override
	public List<ObjectConfig> getEntityList() throws Exception {
		return objectConfigDao.getEntityList();
	}
	@Override
	public boolean deleteEntity(int id) throws Exception {
		return objectConfigDao.deleteEntity(id);
	}
*/
	@Override
	public byte[] getJDBCDriver(String objId) throws Exception {
		return objectConfigDao.getJDBCDriver(objId);
	}
	@Override
	public ObjectConfig getEntityByObjId(String objId) throws Exception {
		return objectConfigDao.getEntityByObjId(objId);
	}
}
