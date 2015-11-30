package com.unetis.diamant.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name = "jdbc_driver")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class JDBCDriver implements Serializable{
	private static final long serialVersionUID = 1L;
	
//	@OneToMany
//	@JoinColumn(name = "driver_id")
//	private List<ObjectConfig> objects;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int jdbc_driver_id;
	
	@Column(name = "driver_name")
	private String driverName;
	
	@Column(name = "driver_path")
	private String driverPath;
	
	@Column(name = "driver_filename")
	private String driverFilename;

	@Column(name = "driver_sha1")
	private String driverSha1;
	
	@Column(name = "driver_class")
	private String driverClass;
	
	public int getId() {
		return jdbc_driver_id;
	}

	public void setId(int id) {
		this.jdbc_driver_id = id;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverPath() {
		return driverPath;
	}

	public void setDriverPath(String driverPath) {
		this.driverPath = driverPath;
	}
	public String getDriverFilename() {
		return driverFilename;
	}

	public void setDriverFilename(String driverFilename) {
		this.driverFilename = driverFilename;
	}

	public String getDriverSha1() {
		return driverSha1;
	}

	public void setDriverSha1(String driverSha1) {
		this.driverSha1 = driverSha1;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	
//	public List<ObjectConfig> getObjects(){
//		return objects;
//	}
}
