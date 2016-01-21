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

/**
 * 
 * Класс контроллер всего, что касается конфигурации клиентской программы.
 * Клиент самым первым делом запрашивает свою конфигурацию.
 * 
 * В объект конфигурации клиента (таблицы object_configuration и jdbc_driver) входят понятия :
 * 1. уникальный идентификатор клиентской программы
 * 2. параметры базы данных на стороне клиента :
 * 		a. Строка соединения с базой
 * 		b. Имя пользователя для той базы
 * 		c. Пароль пользователя
 * 		d. sql-запрос в ту базу для получения информации о выручке или посещаемости
 * 3. идентификатор соответствующего JDBC драйвера из таблицы jdbc_driver
 * 4. опционально секретный ключ шифрования
 * 
 * @author Stepan
 *
 */
@Controller
@RequestMapping("/objectConfig")
public class ObjectConfigController {

	@Autowired
	ObjectConfigServices objectConfigServices;
	
	
	/**
	 * Метод общения с клиентской программой orblanc
	 * Этот метод получает запрос на получение конфигурации того или иного клиента, характеризуемого objId.
	 * Получив objId в запросе, метод выискивает в базе конфигурацию этого клиента и отсылает клиенту
	 * эту конфигурацию, преобразованную из объекта в строку и зашифрованную
	 * 
	 * @param  objId - уникальный идентификатор клиента, который обязательно должен быть в базе
	 * @return зашифрованная строка - после расшифровки это будет объект конфигурации клиента  
	 */
	@RequestMapping(value = "/config{objId}", method = RequestMethod.GET, consumes = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String getObjectIdConfig(@PathVariable("objId") String objId) {
		String encodedObjectConfig = new String();
		try {
			// Можно смотреть сразу в файле ObjectConfigDaoImpl
			// как хибер запрашивает FROM ObjectConfig a where a.objId = objId
			// и получаем конфигурацию клиента в форме объекта
			ObjectConfig objectConfig = objectConfigServices.getEntityByObjId(objId);
			// Для того, чтобы зашифровать, нужно переписать объект конфигурации в строку
			ObjectMapper mapper = new ObjectMapper();
			String toEncode = mapper.writeValueAsString(objectConfig);
			// Шифруем
			AesCrypt crypt = new AesCrypt(objId);
			encodedObjectConfig = crypt.encrypt(toEncode);
			// И отправляем зашифрованное
			return encodedObjectConfig;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Метод общения с клиентской программой orblanc
	 * Этот метод получает запрос на получение JDBC драйвера того или иного клиента, характеризуемого objId.
	 * Получив objId в запросе, метод выискивает в базе информацию об имени и нахождении драйвера, считывает его
	 * и отсылает клиенту байт-код драйвера в зашифровваном виде : строка
	 * 
	 * @param objId
	 * @return зашифрованная строка - после расшифровки это будет байт-код JDBC драйвера
	 */
	@RequestMapping(value = "/driver{objId}", method = RequestMethod.GET, consumes = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody
	String getJDBCDriver(@PathVariable("objId") String objId) {
		try {
			// Можно смотреть сразу в файле ObjectConfigDaoImpl
			// как хибер запрашивает FROM ObjectConfig a where a.objId = objId
			// и получаем байт-код драйвера
			byte[] jdbcDriver = objectConfigServices.getJDBCDriver(objId);
			// Шифруем его
			AesCrypt crypt = new AesCrypt(objId);
			// И отправляем зашифрованное
			return crypt.encrypt(jdbcDriver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 *  Будущие действия для админки 
	 *
	 * 
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

	*/

}
