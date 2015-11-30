package com.unetis.diamant.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.SQLInsert;

@Entity
@Table(name = "revenue")
@SQLInsert( sql="INSERT INTO revenue(obj_id, pos, real_sum, the_date, total) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE revenue.total = GREATEST(revenue.total, VALUES(total)), revenue.real_sum = GREATEST(revenue.real_sum, VALUES(real_sum))")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Revenue implements Serializable {

	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "obj_id")
	private int objId;

	@Column(name = "the_date")
	private String theDate;

	@Column(name = "pos")
	private String pos;

	@Column(name = "total")
	private Integer total;

	@Column(name = "real_sum")
	private Double realSum;

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

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Double getRealSum() {
		return realSum;
	}

	public void setRealSum(Double realSum) {
		this.realSum = realSum;
	}

	@Override
	public String toString() {
		return "Revenue [id=" + id + ", objId=" + objId + ", theDate=" + theDate + ", pos=" + pos + ", total=" + total
				+ ", realSum=" + realSum + "]";
	}




}
