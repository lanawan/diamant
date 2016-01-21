package com.unetis.diamant.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.unetis.diamant.model.Traffic;

/**
 * 
 * Класс обслуживания сервиса TrafficServices
 * 
 * Здесь методы, вызываемые не напрямую из контроллера TrafficController, а посредством сервиса-посредника TrafficServices
 * Это основные методы работы с данными в приложении
 * 
 * @author Stepan
 *
 */
public class TrafficDaoImpl implements TrafficDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	
	/**
	 * 
	 * Добавление трафика в базу.
	 * 
	 * @param revenue - список объектов трафика
	 * 
	 */
	@Override
	public void addEntity(List<Traffic> traffic) {
		// Получение идентификатора сессии хибера
		session = sessionFactory.openSession();
		try{
			// Открытие тразакции внесения изменений в таблицу трафика в базе данных
			tx = session.beginTransaction();
			int count=0;
			Iterator<Traffic> i = traffic.iterator();
			// Пробег по всем объектам трафика в списке
			while(i.hasNext()){
				// Текущий объект выручки
				Traffic received = i.next();
				// Вызов методов класса трафика для определения полей таблицы
				int rin = received.getRin();
				int rout = received.getRout();
				// Создание условия поиска существующей записи для этого дня, клиента и этого датчика (sensor) с другим трафиком
				Criteria criteria = session.createCriteria(Traffic.class);
				criteria.add(Restrictions.eq("objId", received.getObjId()));
				criteria.add(Restrictions.eq("sensor", received.getSensor()));
				criteria.add(Restrictions.eq("theDate", received.getTheDate()));
				Traffic record = (Traffic) criteria.uniqueResult();
				// Если нашли такую запись
				if(record!=null){
					// То изменяем трафик (update) только в сторону повышения.
					// Таким образом, многократные записи одного и того же датчика в один и тот же день будут просто обновлять количесво посетителей.
					// Таким образом, сумма посетителей (трафик) может только расти.
					record.setRin(record.getRin()>rin?record.getRin():rin);
					record.setRout(record.getRout()>rout?record.getRout():rout);
				}
				// А если новая запись
				else{
					// let it be, let it be, let it be, let it be :)
					record = received;
				}
				// Здесь автоматом : либо update, либо insert
				session.saveOrUpdate(record);
				// Переход к следующему объекту в списке трафика
				count++;
				
				// Каждые 20 записей в нашей сессии, как это сделано и в самом JDBC batch size
				if ( count % 20 == 0 ) {
					// Делаем промежуточный ввод всех накопленных в хиберовской сессии текстов (INSERT INTO traffic VALUES...)
					// эдакий flush a batch of inserts
					session.flush();
					// и освобождение памяти, резервированной для той самой сессии
					session.clear();
				}
			}
			// Коммитим транзакцию
			tx.commit();
		}
		catch(Exception e){
			// Если транзакция не прошла, то откатываем
			if(tx!=null) tx.rollback();
			// И пишем все исключения
			e.printStackTrace();
		}finally{
			// Закрываем сессию при любом раскладе
			session.close();
		}
	}
/*
	Пока что не используется. Будет использоваться, когда будет админка.

	@Override
	public Traffic getEntityById(int id) throws Exception {
		session = sessionFactory.openSession();
		Traffic traffic = session.load(Traffic.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return traffic;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<Traffic> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Traffic> trafficList = session.createCriteria(Traffic.class).list();
		tx.commit();
		session.close();
		return trafficList;
	}
	@Override
	public boolean deleteEntity(int id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Traffic.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}
*/
}
