package com.unetis.diamant.service;

import java.util.List;

import com.unetis.diamant.model.Revenue;

public interface RevenueServices {
	public boolean addEntity(List<Revenue> revenue) throws Exception;
	public Revenue getEntityById(int id) throws Exception;
	public List<Revenue> getEntityList() throws Exception;
	public boolean deleteEntity(int id) throws Exception;
}