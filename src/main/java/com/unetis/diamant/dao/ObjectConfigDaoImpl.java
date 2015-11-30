package com.unetis.diamant.dao;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.unetis.diamant.model.ObjectConfig;

public class ObjectConfigDaoImpl implements ObjectConfigDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction tx = null;
	@Override
	public boolean addEntity(ObjectConfig objectConfig) throws Exception {

		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		session.saveOrUpdate(objectConfig);
		tx.commit();
		session.close();

		return false;
	}
	@Override
	public ObjectConfig getEntityById(int id) throws Exception {
		session = sessionFactory.openSession();
		ObjectConfig objectConfig = session.load(ObjectConfig.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		session.close();
		return objectConfig;
	}
	@Override
	public ObjectConfig getEntityByObjId(String objId) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("FROM ObjectConfig a where a.objId = "+objId); 
		ObjectConfig record = (ObjectConfig)query.uniqueResult();

		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		session.close();
		if(record!=null){
			return record;
		}
		else{
			throw new Exception("Object "+objId+" doesn't exist in database");
		}
	}

	@Override
	public byte[] getJDBCDriver(String objId) throws Exception {
		session = sessionFactory.openSession();

		Query query = session.createQuery("FROM ObjectConfig a where a.objId = "+objId); 
		ObjectConfig record = (ObjectConfig)query.uniqueResult();
		
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		session.close();
		
		if(record!=null){
			try{
				String filename = record.getDriver().getDriverPath()+record.getDriver().getDriverFilename();
				File file = new File(filename);
				int size = (int)file.length();
				byte[] bytes = new byte[size];
				if (size > Integer.MAX_VALUE){
					throw new Exception("File "+filename+" is too large");
				}
				else{
					DataInputStream dis = new DataInputStream(new FileInputStream(file));
					int read = 0;
					int numRead = 0;
					while (read < bytes.length && (numRead=dis.read(bytes, read, bytes.length-read)) >= 0) {
						read = read + numRead;
					}
					dis.close();
					if (read < bytes.length) {
						throw new Exception("Could not completely read file "+filename);
					}
					
				}
				return bytes;
			}
			catch(Exception e){
				throw new Exception(e);
			}
		}
		else{
			throw new Exception("Object "+objId+" doesn't exist in database");
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ObjectConfig> getEntityList() throws Exception {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		List<ObjectConfig> objectConfigList = session.createCriteria(ObjectConfig.class).list();
		tx.commit();
		session.close();
		return objectConfigList;
	}
	@Override
	public boolean deleteEntity(int id) throws Exception {
		session = sessionFactory.openSession();
		Object o = session.load(ObjectConfig.class, id);
		tx = session.getTransaction();
		session.beginTransaction();
		session.delete(o);
		tx.commit();
		return false;
	}

}
