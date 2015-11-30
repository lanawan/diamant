package com.unetis.diamant.service;

import java.util.List;

import com.unetis.diamant.model.ObjectConfig;

public interface ObjectConfigServices {
	public boolean addEntity(ObjectConfig objectConfig) throws Exception;
	public ObjectConfig getEntityById(int id) throws Exception;
	public ObjectConfig getEntityByObjId(String objId) throws Exception;
	public byte[] getJDBCDriver(String objId) throws Exception;
	public List<ObjectConfig> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
}