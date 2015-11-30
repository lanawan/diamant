package com.unetis.diamant.controller;

import java.util.List;

import com.unetis.diamant.model.Revenue;
import com.unetis.diamant.service.RevenueServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

	@Autowired
	RevenueServices revenueServices;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String addRevenue(@RequestBody List<Revenue> revenue) {
		try {
			revenueServices.addEntity(revenue);
			return "Ok";
		} catch (Exception e) {
			return "Error:"+e.toString();
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Revenue getRevenue(@PathVariable("id") int id) {
		Revenue revenue = null;
		try {
			revenue = revenueServices.getEntityById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return revenue;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Revenue> getRevenue() {

		List<Revenue> revenueList = null;
		try {
			revenueList = revenueServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return revenueList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String deleteRevenue(@PathVariable("id") int id) {

		try {
			revenueServices.deleteEntity(id);
			return "Ok";
		} catch (Exception e) {
			return "Error:"+e.toString();
		}

	}
}
