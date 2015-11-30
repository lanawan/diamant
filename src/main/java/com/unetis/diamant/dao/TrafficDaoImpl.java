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

public class TrafficDaoImpl implements TrafficDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	@Override
	public boolean addEntity(List<Traffic> traffic) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		int count=0;
		Iterator<Traffic> i = traffic.iterator();
		while(i.hasNext()){
			Traffic received = i.next();
			int rin = received.getRin();
			int rout = received.getRout();

			Criteria criteria = session.createCriteria(Traffic.class);
			criteria.add(Restrictions.eq("objId", received.getObjId()));
			criteria.add(Restrictions.eq("sensor", received.getSensor()));
			criteria.add(Restrictions.eq("theDate", received.getTheDate()));
			Traffic record = (Traffic) criteria.uniqueResult();
			if(record!=null){
				record.setRin(record.getRin()>rin?record.getRin():rin);
				record.setRout(record.getRout()>rout?record.getRout():rout);
			}
			else{
				record = received;
			}
			session.saveOrUpdate(record);
			count++;
			if ( count % 20 == 0 ) { //20, same as the JDBC batch size
				//flush a batch of inserts and release memory:
				session.flush();
				session.clear();
			}
		
		}
		
		tx.commit();
		session.close();
		return false;
	}
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

}
