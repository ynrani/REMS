package com.rems.model.DO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the REMS_BOOK_ENVI database table.
 * 
 */
@Entity
@Table(name = "REMS_BOOK_ENVI")
@NamedQuery(name = "RemsBookEnviDO.findAll", query = "SELECT r FROM RemsBookEnviDO r")
public class RemsBookEnviDO implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name = "ACTION_BY")
	private String actionBy;

	private String active;

	@Column(name = "APP_ID")
	private BigDecimal appId;

	@Column(name = "APPL_NAME")
	private String applName;

	@Column(name = "BOOKED_TO")
	private String bookedTo;

	@Column(name = "CRE_ACTION_DT")
	private Timestamp creActionDt;

	@Column(name = "END_DT")
	private Date endDt;

	@Column(name = "ENV_BOOKED_OUT")
	private String envBookedOut;

	@Column(name = "ENV_CONN")
	private String envConn;

	@Column(name = "ENV_NAME")
	private String envName;

	@Column(name = "ENV_USER")
	private String envUser;

	private String note;

	@Column(name = "START_DT")
	private Date startDt;

	@Column(name = "SW_LOAD_INFO")
	private String swLoadInfo;

	@Column(name = "UP_ACTION_DT")
	private Timestamp upActionDt;

	@Column(name = "STATUS")
	private String status;

	public RemsBookEnviDO()
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

	public BigDecimal getAppId() {
		return this.appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

	public String getApplName() {
		return this.applName;
	}

	public void setApplName(String applName) {
		this.applName = applName;
	}

	public String getBookedTo() {
		return this.bookedTo;
	}

	public void setBookedTo(String bookedTo) {
		this.bookedTo = bookedTo;
	}

	public Timestamp getCreActionDt() {
		return this.creActionDt;
	}

	public void setCreActionDt(Timestamp creActionDt) {
		this.creActionDt = creActionDt;
	}

	public Date getEndDt() {
		return this.endDt;
	}

	public void setEndDt(Date date) {
		this.endDt = date;
	}

	public String getEnvBookedOut() {
		return this.envBookedOut;
	}

	public void setEnvBookedOut(String envBookedOut) {
		this.envBookedOut = envBookedOut;
	}

	public String getEnvConn() {
		return this.envConn;
	}

	public void setEnvConn(String envConn) {
		this.envConn = envConn;
	}

	public String getEnvName() {
		return this.envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getEnvUser() {
		return this.envUser;
	}

	public void setEnvUser(String envUser) {
		this.envUser = envUser;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getStartDt() {
		return this.startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}