package com.unetis.diamant.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name = "object_configuration")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ObjectConfig implements Serializable {
	private static final long serialVersionUID = 2L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "obj_id")
	private int objId;

	@Column(name = "db_connection_string")
	private String dbConnectionString;
	
	@Column(name = "db_user")
	private String dbUser;
	
	@Column(name = "db_password")
	private String dbPassword;

	@Column(name = "sql_query")
	private String sqlQuery;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "jdbc_driver_id")
	private JDBCDriver driver;
	
	public int getId() {
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

	public String getDbConnectionString() {
		return dbConnectionString;
	}

	public void setDbConnectionString(String dbConnectionString) {
		this.dbConnectionString = dbConnectionString;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public JDBCDriver getDriver(){
		return driver;
	}

	public void setDriver(JDBCDriver driver){
		this.driver = driver;
	}

	@Override
	public String toString() {
		return "ObjectConfig [id=" + id + ", objId=" + objId + ", dbConnectionString=" + dbConnectionString
				+ ", dbUser=" + dbUser + ", dbPassword=" + dbPassword + ", sqlQuery=" + sqlQuery + ", driverFilename=" + driver.getDriverFilename() +
				", driverSha1=" + driver.getDriverSha1() +
				", driverClass=" + driver.getDriverClass() +
				"]";
	}
	

}
