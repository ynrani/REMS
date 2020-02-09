package com.rems.model.DTO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RemsEnvBookingsDTO
{
	private String id;
	private String appName;
	private String envName;
	private String envUser;
	private String bookedTo;
	private String startDt;
	private String endDt;
	private String envConn;
	private String envBookedOut;
	private String swLoadInfo;
	private String note;
	private String userId;
	private String active;
	private String createdDate;
	private String upActionDt;

	private String status;

	private List<String> availableDates;

	private List<String> appNames = null;
	private List<String> envNames = null;

	private Map<String, Map<String, Set<String>>> mapFinalList;

	private List<String> passedDates;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getEnvUser() {
		return envUser;
	}

	public void setEnvUser(String envUser) {
		this.envUser = envUser;
	}

	public String getBookedTo() {
		return bookedTo;
	}

	public void setBookedTo(String bookedTo) {
		this.bookedTo = bookedTo;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getEnvConn() {
		return envConn;
	}

	public void setEnvConn(String envConn) {
		this.envConn = envConn;
	}

	public String getEnvBookedOut() {
		return envBookedOut;
	}

	public void setEnvBookedOut(String envBookedOut) {
		this.envBookedOut = envBookedOut;
	}

	public String getSwLoadInfo() {
		return swLoadInfo;
	}

	public void setSwLoadInfo(String swLoadInfo) {
		this.swLoadInfo = swLoadInfo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public List<String> getAppNames() {
		return appNames;
	}

	public void setAppNames(List<String> appNames) {
		this.appNames = appNames;
	}

	public List<String> getEnvNames() {
		return envNames;
	}

	public void setEnvNames(List<String> envNames) {
		this.envNames = envNames;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getAvailableDates() {
		return availableDates;
	}

	public void setAvailableDates(List<String> availableDates) {
		this.availableDates = availableDates;
	}

	public Map<String, Map<String, Set<String>>> getMapFinalList() {
		return mapFinalList;
	}

	public void setMapFinalList(Map<String, Map<String, Set<String>>> mapFinalList) {
		this.mapFinalList = mapFinalList;
	}

	public List<String> getPassedDates() {
		return passedDates;
	}

	public void setPassedDates(List<String> passedDates) {
		this.passedDates = passedDates;
	}

}
