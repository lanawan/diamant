package com.unetis.diamant.controller;

import java.util.List;

import com.unetis.diamant.model.Revenue;
import com.unetis.diamant.model.Traffic;
import com.unetis.diamant.service.RevenueServices;
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

/**
 * 
 * Класс контроллер всего, что касается выручки.
 * Клиент шлет выручку.
 * 
 * Получаем сразу несколько выручек (за каждый отдельный день и кассу)
 * Пишем полученное в базу  
 * 
 * @author Stepan
 *
 */
@RestController
@RequestMapping("/revenue")
public class RevenueController {

	@Autowired
	RevenueServices revenueServices;

	/**
	 * Метод общения с клиентской программой orblanc
	 * Метод получает выручку и записывает ее в базу.
	 * 
	 * @param encodedData - зашифрованная строка, после расшифровки нужно преобразовать в список объектов выручки
	 * @return Ок или Error с текстом ошибки
	 */
	@RequestMapping(value = "/create{objId}", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String addRevenue(@PathVariable("objId") String objId, @RequestBody String encodedData) {
		try {
			AesCrypt crypt = new AesCrypt(objId);
			ObjectMapper mapper = new ObjectMapper();
			// Расшифровываем и преобразуем полученное в список объектов выручки
			List<Revenue> revenue = mapper.readValue(crypt.decrypt(encodedData), mapper.getTypeFactory().constructCollectionType(List.class, Revenue.class));
			// Добавляем список в базу
			revenueServices.addEntity(revenue);
			// Отвечаем клиенту Ок - добавили
			return "Ok";
		} catch (Exception e) {
			return "Error:"+e.toString();
		}
	}
/*
	Будущие действия для админки


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
*/
}
