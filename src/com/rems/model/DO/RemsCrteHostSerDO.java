package com.rems.model.DO;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the REMS_CRTE_HOST_SER database table.
 * 
 */
@Entity
@Table(name="REMS_CRTE_HOST_SER")
@NamedQuery(name="RemsCrteHostSerDO.findAll", query="SELECT r FROM RemsCrteHostSerDO r")
public class RemsCrteHostSerDO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="ACTION_BY")
	private String actionBy;

	private String active;

	@Column(name="ALLO_CPU")
	private String alloCpu;

	@Column(name="ALLO_DISK_SPACE")
	private String alloDiskSpace;

	@Column(name="ALLO_MEMORY")
	private String alloMemory;

	@Column(name="CRE_ACTION_DT")
	private Timestamp creActionDt;

	@Column(name="HOST_SERVER_NAME")
	private String hostServerName;

	private String ip;

	@Column(name="SERVER_PHY_LOC")
	private String serverPhyLoc;

	private String sme;

	@Column(name="UP_ACTION_DT")
	private Timestamp upActionDt;

	public RemsCrteHostSerDO() {
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

	public String getAlloCpu() {
		return this.alloCpu;
	}

	public void setAlloCpu(String alloCpu) {
		this.alloCpu = alloCpu;
	}

	public String getAlloDiskSpace() {
		return this.alloDiskSpace;
	}

	public void setAlloDiskSpace(String alloDiskSpace) {
		this.alloDiskSpace = alloDiskSpace;
	}

	public String getAlloMemory() {
		return this.alloMemory;
	}

	public void setAlloMemory(String alloMemory) {
		this.alloMemory = alloMemory;
	}

	public Timestamp getCreActionDt() {
		return this.creActionDt;
	}

	public void setCreActionDt(Timestamp creActionDt) {
		this.creActionDt = creActionDt;
	}

	public String getHostServerName() {
		return this.hostServerName;
	}

	public void setHostServerName(String hostServerName) {
		this.hostServerName = hostServerName;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getServerPhyLoc() {
		return this.serverPhyLoc;
	}

	public void setServerPhyLoc(String serverPhyLoc) {
		this.serverPhyLoc = serverPhyLoc;
	}

	public String getSme() {
		return this.sme;
	}

	public void setSme(String sme) {
		this.sme = sme;
	}

	public Timestamp getUpActionDt() {
		return this.upActionDt;
	}

	public void setUpActionDt(Timestamp upActionDt) {
		this.upActionDt = upActionDt;
	}

}