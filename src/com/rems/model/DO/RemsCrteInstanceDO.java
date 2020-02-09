package com.rems.model.DO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the REMS_CRTE_INSTANCE database table.
 * 
 */
@Entity
@Table(name = "REMS_CRTE_INSTANCE")
@NamedQuery(name = "RemsCrteInstanceDO.findAll", query = "SELECT r FROM RemsCrteInstanceDO r")
public class RemsCrteInstanceDO implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name = "ACTION_BY")
	private String actionBy;

	private String active;

	@Column(name = "ALLO_MEMORY")
	private String alloMemory;

	@Column(name = "ANOTHER_ENV")
	private String anotherEnv;

	@Column(name = "APPL_NAME")
	private String applName;

	@Column(name = "CRE_ACTION_DT")
	private Timestamp creActionDt;

	@Column(name = "ENV_NAME")
	private String envName;

	@Column(name = "ENV_TYPE")
	private String envType;

	@Column(name = "HOST_SERVER_ID")
	private BigDecimal hostServerId;

	@Column(name = "HOST_SERVER_NAME")
	private String hostServerName;

	@Column(name = "NW_CONN_INFO")
	private String nwConnInfo;

	@Column(name = "OS_VER")
	private String osVer;

	@Column(name = "DB_NME")
	private String dbName;

	@Column(name = "PROJ_NAME")
	private String projName;

	private String sme;

	@Column(name = "SW_LOAD_INFO")
	private String swLoadInfo;

	@Column(name = "UP_ACTION_DT")
	private Timestamp upActionDt;

	public RemsCrteInstanceDO()
	{
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getActionBy() {
		return this.actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getAlloMemory() {
		return this.alloMemory;
	}

	public void setAlloMemory(String alloMemory) {
		this.alloMemory = alloMemory;
	}

	public String getAnotherEnv() {
		return this.anotherEnv;
	}

	public void setAnotherEnv(String anotherEnv) {
		this.anotherEnv = anotherEnv;
	}

	public String getApplName() {
		return this.applName;
	}

	public void setApplName(String applName) {
		this.applName = applName;
	}

	public Timestamp getCreActionDt() {
		return this.creActionDt;
	}

	public void setCreActionDt(Timestamp creActionDt) {
		this.creActionDt = creActionDt;
	}

	public String getEnvName() {
		return this.envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getEnvType() {
		return this.envType;
	}

	public void setEnvType(String envType) {
		this.envType = envType;
	}

	public BigDecimal getHostServerId() {
		return this.hostServerId;
	}

	public void setHostServerId(BigDecimal hostServerId) {
		this.hostServerId = hostServerId;
	}

	public String getHostServerName() {
		return this.hostServerName;
	}

	public void setHostServerName(String hostServerName) {
		this.hostServerName = hostServerName;
	}

	public String getNwConnInfo() {
		return this.nwConnInfo;
	}

	public void setNwConnInfo(String nwConnInfo) {
		this.nwConnInfo = nwConnInfo;
	}

	public String getOsVer() {
		return this.osVer;
	}

	public void setOsVer(String osVer) {
		this.osVer = osVer;
	}

	public String getProjName() {
		return this.projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getSme() {
		return this.sme;
	}

	public void setSme(String sme) {
		this.sme = sme;
	}

	public String getSwLoadInfo() {
		return this.swLoadInfo;
	}

	public void setSwLoadInfo(String swLoadInfo) {
		this.swLoadInfo = swLoadInfo;
	}

	public Timestamp getUpActionDt() {
		return this.upActionDt;
	}

	public void setUpActionDt(Timestamp upActionDt) {
		this.upActionDt = upActionDt;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}