package com.unetis.diamant.dao;

import java.util.List;

import com.unetis.diamant.model.ObjectConfig;
/**
 * 
 * Интерфас для сервиса ObjectConfigServices
 * 
 * @author Stepan
 *
 */
public interface ObjectConfigDao {
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
