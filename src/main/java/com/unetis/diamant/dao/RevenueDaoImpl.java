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

public class RevenueDaoImpl implements RevenueDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;


	public boolean addEntity(List<Revenue> revenue) throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		int count=0;
		Iterator<Revenue> i = revenue.iterator();
		while(i.hasNext()){
			Revenue received = i.next();
			int total = received.getTotal();
			Double realSum = received.getRealSum();
			Criteria criteria = session.createCriteria(Revenue.class);
			criteria.add(Restrictions.eq("objId", received.getObjId()));
			criteria.add(Restrictions.eq("pos", received.getPos()));
			criteria.add(Restrictions.eq("theDate", received.getTheDate()));
			Revenue record = (Revenue) criteria.uniqueResult();
			if(record!=null){
				record.setTotal(record.getTotal()>total?record.getTotal():total);
				record.setRealSum(record.getRealSum()>realSum?record.getRealSum():realSum);
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

}
