package com.unetis.diamant.controller;

import java.util.List;

import com.unetis.diamant.model.ObjectConfig;
import com.unetis.diamant.service.ObjectConfigServices;
import com.unetis.emeraude.AesCrypt;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/objectConfig")
public class ObjectConfigController {

	@Autowired
	ObjectConfigServices objectConfigServices;

	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	String addObjectConfig(@RequestBody ObjectConfig objectConfig) {
		try {
			objectConfigServices.addEntity(objectConfig);
			return "Ok";
		} catch (Exception e) {
			return "Error:"+e.toString();
		}

	}
	@RequestMapping(value = "/read{id}", method = RequestMethod.GET)
	public @ResponseBody
	ObjectConfig getObjectConfig(@PathVariable("id") int id) {
		ObjectConfig objectConfig = null;
		try {
			objectConfig = objectConfigServices.getEntityById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectConfig;
	}

	@RequestMapping(value = "/config{objId}", method = RequestMethod.GET, consumes = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String getObjectIdConfig(@PathVariable("objId") String objId) {
		String encodedObjectConfig = new String();
		try {
			ObjectConfig objectConfig = objectConfigServices.getEntityByObjId(objId);
			ObjectMapper mapper = new ObjectMapper();
			String toEncode = mapper.writeValueAsString(objectConfig);
			AesCrypt crypt = new AesCrypt(objId);
			encodedObjectConfig = crypt.encrypt(toEncode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedObjectConfig;
	}

	@RequestMapping(value = "/driver{objId}", method = RequestMethod.GET, consumes = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String getJDBCDriver(@PathVariable("objId") String objId) {
		try {
			byte[] jdbcDriver = objectConfigServices.getJDBCDriver(objId);
			AesCrypt crypt = new AesCrypt(objId);
			return crypt.encrypt(jdbcDriver);

			//return(objectConfigServices.getJDBCDriver(objId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	List<ObjectConfig> getObjectConfig() {
		List<ObjectConfig> objectConfigList = null;
		try {
			objectConfigList = objectConfigServices.getEntityList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return objectConfigList;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	String deleteObjectConfig(@PathVariable("id") int id) {
		try {
			objectConfigServices.deleteEntity(id);
			return "Ok";
		} catch (Exception e) {
			return "Error:"+e;
		}
	}
}
