package com.unetis.diamant.controller;

import java.util.List;

import com.unetis.diamant.model.Traffic;
import com.unetis.diamant.service.TrafficServices;
import com.unetis.emeraude.AesCrypt;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.unetis.diamant.Crypt;

@RestController
@RequestMapping("/traffic")
public class TrafficController {

	@Autowired
	TrafficServices trafficServices;
	
	@RequestMapping(value = "/create{objId}", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String addTraffic(@PathVariable("objId") String objId, @RequestBody String encodedData) {
		try {
			AesCrypt crypt = new AesCrypt(objId);
			ObjectMapper mapper = new ObjectMapper();
			List<Traffic> traffic = mapper.readValue(crypt.decrypt(encodedData), mapper.getTypeFactory().constructCollectionType(List.class, Traffic.class));
			trafficServices.addEntity(traffic);
			return "Ok";
		} catch (Exception e) {
			return "Error:"+e.toString();
		}

	}
	
	//Status addTraffic(@RequestBody List<Traffic> traffic) {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Traffic getTraffic(@PathVariable("id") int id) {
		Traffic traffic = null;
		try {
			traffic = trafficServices.getEntityById(id);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return traffic;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<Traffic> getTraffic() {

		List<Traffic> trafficList = null;
		try {
			trafficList = trafficServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return trafficList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String deleteTraffic(@PathVariable("id") int id) {

		try {
			trafficServices.deleteEntity(id);
			return "Ok";
		} catch (Exception e) {
			return "Error:"+e.toString();
		}

	}
}
