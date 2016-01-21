package com.unetis.diamant.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.unetis.diamant.model.Revenue;

/**
 * 
 * Класс обслуживания сервиса RevenueServices
 * 
 * Здесь методы, вызываемые не напрямую из контроллера RevenueController, а посредством сервиса-посредника RevenueServices
 * Это основные методы работы с данными в приложении
 * 
 * @author Stepan
 *
 */
public class RevenueDaoImpl implements RevenueDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;

	/**
	 * 
	 * Добавление выручки в базу. Именно добавление : существующая в базе выручка не понижается см. комментарии
	 * 
	 * @param revenue - список объектов выручки
	 * 
	 */
	public void addEntity(List<Revenue> revenue) {
		// Получение идентификатора сессии хибера
		session = sessionFactory.openSession();
		try{
			// Открытие тразакции внесения изменений в таблицу выручек в базе данных
			tx = session.beginTransaction();
			int count=0;
			Iterator<Revenue> i = revenue.iterator();
			// Пробег по всем объектам выручки в списке
			while(i.hasNext()){
				// Текущий объект выручки
				Revenue received = i.next();
				// Вызов методов класса выручки для определения полей таблицы
				int total = received.getTotal();
				Double realSum = received.getRealSum();
				// Создание условия поиска существующей записи для этого дня, клиента и этой кассы (pos) с другой выручкой
				Criteria criteria = session.createCriteria(Revenue.class);
				criteria.add(Restrictions.eq("objId", received.getObjId()));
				criteria.add(Restrictions.eq("pos", received.getPos()));
				criteria.add(Restrictions.eq("theDate", received.getTheDate()));
				Revenue record = (Revenue) criteria.uniqueResult();
				// Если нашли такую запись
				if(record!=null){
					// То изменяем выручку (update) только в сторону повышения.
					// Таким образом, многократные записи одной и той же кассы в один и тот же день будут просто обновлять сумму выручки.
					// Таким образом, выручка может только расти.
					// В случае возвратов нужно поменять логику в этом месте :
					// либо разрешить делать update с любой выручкой
					// либо записывать возвраты в отдельную таблицу с отдельным REST-сервисом, скажем Returns
					// А это уже пусть решает финансовый босс.
					record.setTotal(record.getTotal()>total?record.getTotal():total);
					record.setRealSum(record.getRealSum()>realSum?record.getRealSum():realSum);
				}
				// А если новая запись
				else{
					// То значит let it be :)
					record = received;
				}
				// Здесь автоматом : либо update, либо insert 
				session.saveOrUpdate(record);
				// Переход к следующему объекту в списке выручек
				count++;
				
				// Каждые 20 записей в нашей сессии, как это сделано и в самом JDBC batch size  
				if ( count % 20 == 0 ) {
					// Делаем промежуточный ввод всех накопленных в хиберовской сессии текстов (INSERT INTO revenue VALUES...)
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
	
	public Revenue getEntityById(int id) throws Exception {
		session = sessionFactory.openSession();
		Revenue revenue = session.load(Revenue.class, new Long(id));
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		return revenue;
	}

	@SuppressWarnings("unchecked")
	public List<Revenue> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<Revenue> revenueList = session.createCriteria(Revenue.class).list();
		tx.commit();
		session.close();
		return revenueList;
	}

	public boolean deleteEntity(int id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(Revenue.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}
*/
}
