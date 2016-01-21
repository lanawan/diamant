package com.unetis.diamant.dao;

import java.util.List;

import com.unetis.diamant.model.Revenue;

/**
 * 
 * Интерфас для сервиса RevenueServices
 * 
 * @author Stepan
 *
 */
public interface RevenueDao {
	public void addEntity(List<Revenue> revenue);
/*
	Пока что не используется. Будет использоваться, когда будет админка.
	
	public Revenue getEntityById(int id) throws Exception;
	public List<Revenue> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
*/
}
