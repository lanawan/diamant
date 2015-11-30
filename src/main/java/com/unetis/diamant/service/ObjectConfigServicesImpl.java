package com.unetis.diamant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.unetis.diamant.dao.ObjectConfigDao;
import com.unetis.diamant.model.ObjectConfig;

public class ObjectConfigServicesImpl implements ObjectConfigServices {
	@Autowired
	ObjectConfigDao objectConfigDao;
	@Override
	public boolean addEntity(ObjectConfig objectConfig) throws Exception {
		return objectConfigDao.addEntity(objectConfig);
	}
	@Override
	public ObjectConfig getEntityById(int id) throws Exception {
		return objectConfigDao.getEntityById(id);
	}
	@Override
	public byte[] getJDBCDriver(String objId) throws Exception {
		return objectConfigDao.getJDBCDriver(objId);
	}
	@Override
	public ObjectConfig getEntityByObjId(String objId) throws Exception {
		return objectConfigDao.getEntityByObjId(objId);
	}
	@Override
	public List<ObjectConfig> getEntityList() throws Exception {
		return objectConfigDao.getEntityList();
	}
	@Override
	public boolean deleteEntity(int id) throws Exception {
		return objectConfigDao.deleteEntity(id);
	}

}
