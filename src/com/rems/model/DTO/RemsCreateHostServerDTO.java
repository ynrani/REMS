package com.rems.model.DTO;

public class RemsCreateHostServerDTO
{
	private String id;
	private String hostServerName;
	private String serverPhyLoc;
	private String ip;
	private String alloCPU;
	private String alloDiskSpace;
	private String alloMemory;
	private String sme;
	private String active;
	private String userId;
	private String createdDate;
	private String upActionDt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHostServerName() {
		return hostServerName;
	}

	public void setHostServerName(String hostServerName) {
		this.hostServerName = hostServerName;
	}

	public String getServerPhyLoc() {
		return serverPhyLoc;
	}

	public void setServerPhyLoc(String serverPhyLoc) {
		this.serverPhyLoc = serverPhyLoc;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAlloCPU() {
		return alloCPU;
	}

	public void setAlloCPU(String alloCPU) {
		this.alloCPU = alloCPU;
	}

	public String getAlloDiskSpace() {
		return alloDiskSpace;
	}

	public void setAlloDiskSpace(String alloDiskSpace) {
		this.alloDiskSpace = alloDiskSpace;
	}

	public String getAlloMemory() {
		return alloMemory;
	}

	public void setAlloMemory(String alloMemory) {
		this.alloMemory = alloMemory;
	}

	public String getSme() {
		return sme;
	}

	public void setSme(String sme) {
		this.sme = sme;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public String getUpActionDt() {
		return upActionDt;
	}

	public void setUpActionDt(String upActionDt) {
		this.upActionDt = upActionDt;
	}

}
