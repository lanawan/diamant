package com.unetis.diamant.service;

import java.util.List;

import com.unetis.diamant.model.Revenue;

/**
 * 
 * Сервисный интерфас для контроллера RevenueController
 * 
 * @author Stepan
 *
 */
public interface RevenueServices {
	// Добавить выручку в базу
	public void addEntity(List<Revenue> revenue);
/*
	Когда будет админка, тогда это и понадобится

	public Revenue getEntityById(int id) throws Exception;
	public List<Revenue> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
*/
}