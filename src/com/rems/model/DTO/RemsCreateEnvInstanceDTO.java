package com.rems.model.DTO;

import java.util.List;

public class RemsCreateEnvInstanceDTO
{
	private String id;
	private String projName;
	private String envName;
	private String appName;
	private String swLoadInfo;
	private String hostServerName;
	private String osVer;
	private String dbName;
	private String nwConnInfo;
	private String alloMemory;
	private String envType;
	private String sme;
	private String anotherEnv;
	private String userId;
	private String createdDate;
	private String active;
	private String upActionDt;

	private List<String> hostServerNames;

	public List<String> getHostServerNames() {
		return hostServerNames;
	}

	public void setHostServerNames(List<String> hostServerNames) {
		this.hostServerNames = hostServerNames;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProjName() {
		return projName;
	}

	public void setProjName(String projName) {
		this.projName = projName;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getSwLoadInfo() {
		return swLoadInfo;
	}

	public void setSwLoadInfo(String swLoadInfo) {
		this.swLoadInfo = swLoadInfo;
	}

	public String getHostServerName() {
		return hostServerName;
	}

	public void setHostServerName(String hostServerName) {
		this.hostServerName = hostServerName;
	}

	public String getOsVer() {
		return osVer;
	}

	public void setOsVer(String osVer) {
		this.osVer = osVer;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getNwConnInfo() {
		return nwConnInfo;
	}

	public void setNwConnInfo(String nwConnInfo) {
		this.nwConnInfo = nwConnInfo;
	}

	public String getAlloMemory() {
		return alloMemory;
	}

	public void setAlloMemory(String alloMemory) {
		this.alloMemory = alloMemory;
	}

	public String getEnvType() {
		return envType;
	}

	public void setEnvType(String envType) {
		this.envType = envType;
	}

	public String getSme() {
		return sme;
	}

	public void setSme(String sme) {
		this.sme = sme;
	}

	public String getAnotherEnv() {
		return anotherEnv;
	}

	public void setAnotherEnv(String anotherEnv) {
		this.anotherEnv = anotherEnv;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getUpActionDt() {
		return upActionDt;
	}

	public void setUpActionDt(String upActionDt) {
		this.upActionDt = upActionDt;
	}

}
