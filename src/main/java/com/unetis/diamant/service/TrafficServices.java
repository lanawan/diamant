package com.unetis.diamant.service;

import java.util.List;

import com.unetis.diamant.model.Traffic;

/**
 * 
 * Сервисный интерфас для контроллера TrafficController
 * 
 * @author Stepan
 *
 */
public interface TrafficServices {
	// Добавить трафик в базу
	public void addEntity(List<Traffic> traffic);
/*
	public Traffic getEntityById(int id) throws Exception;
	public List<Traffic> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
*/
}
