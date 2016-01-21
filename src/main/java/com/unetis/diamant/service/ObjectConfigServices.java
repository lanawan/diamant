package com.unetis.diamant.service;

import java.util.List;

import com.unetis.diamant.model.ObjectConfig;
/**
 * 
 * Сервисный интерфас для контроллера ObjectConfigController
 * 
 * @author Stepan
 *
 */
public interface ObjectConfigServices {
	public ObjectConfig getEntityByObjId(String objId) throws Exception;
	public byte[] getJDBCDriver(String objId) throws Exception;
/*
	Пока что не используется. Будет использоваться, когда будет админка.
	
	public boolean addEntity(ObjectConfig objectConfig) throws Exception;
	public ObjectConfig getEntityById(int id) throws Exception;
	public List<ObjectConfig> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
*/
}