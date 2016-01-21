package com.unetis.diamant.dao;

import java.util.List;

import com.unetis.diamant.model.Traffic;

/**
 * 
 * Интерфас для сервиса TrafficServices
 * 
 * @author Stepan
 *
 */
public interface TrafficDao {
	public void addEntity(List<Traffic> traffic);
/*
	Пока что не используется. Будет использоваться, когда будет админка.

	public Traffic getEntityById(int id) throws Exception;
	public List<Traffic> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
*/
}
