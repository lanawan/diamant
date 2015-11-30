package com.unetis.diamant.service;

import java.util.List;

import com.unetis.diamant.model.Traffic;

public interface TrafficServices {
	public boolean addEntity(List<Traffic> traffic) throws Exception;
	public Traffic getEntityById(int id) throws Exception;
	public List<Traffic> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
}
