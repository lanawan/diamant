package com.unetis.diamant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//import org.hibernate.annotations.SQLInsert;

@Entity
@Table(name = "traffic")
//@SQLInsert( sql="INSERT INTO traffic(obj_id, rin, rout, sensor, the_date) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE traffic.rin = GREATEST(traffic.rin, VALUES(rin)), traffic.rout = GREATEST(traffic.rout, VALUES(rout))")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Traffic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "obj_id")
	private int objId;

	@Column(name = "the_date")
	private String theDate;

	@Column(name = "sensor")
	private String sensor;

	@Column(name = "rin")
	private int rin;

	@Column(name = "rout")
	private int rout;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public String getTheDate() {
		return theDate;
	}

	public void setTheDate(String theDate) {
		this.theDate = theDate;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public int getRin() {
		return rin;
	}

	public void setRin(int rin) {
		this.rin = rin;
	}

	public int getRout() {
		return rout;
	}

	public void setRout(int rout) {
		this.rout = rout;
	}

	@Override
	public String toString() {
		return "Traffic [objId=" + objId + ", theDate=" + theDate + ", sensor=" + sensor + ", rin=" + rin + ", rout="
				+ rout + "]";
	}




}
