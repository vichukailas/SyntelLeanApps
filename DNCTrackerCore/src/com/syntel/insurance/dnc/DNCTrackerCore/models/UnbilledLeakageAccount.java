package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;

public class UnbilledLeakageAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerName;
	private String locationType;
	private int projectId;
	private String projectName;
	private String projectType;
	private double totalStandardHours;
	private double totalBillableHours;
	private double totalClientHoliday;
	private double totalMto;
	private double leakage;
	private double costOfNC;
	private String dncCategory;

	public UnbilledLeakageAccount() {

	}

	public UnbilledLeakageAccount(String customerName, String locationType, int projectId, String projectName,
			String projectType, double totalStandardHours, double totalBillableHours, double totalClientHoliday,
			double totalMto, double leakage, double costOfNC, String dncCategory) {
		this.customerName = customerName;
		this.locationType = locationType;
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectType = projectType;
		this.totalStandardHours = totalStandardHours;
		this.totalBillableHours = totalBillableHours;
		this.totalClientHoliday = totalClientHoliday;
		this.totalMto = totalMto;
		this.leakage = leakage;
		this.costOfNC = costOfNC;
		this.dncCategory = dncCategory;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public double getTotalStandardHours() {
		return totalStandardHours;
	}

	public void setTotalStandardHours(double totalStandardHours) {
		this.totalStandardHours = totalStandardHours;
	}

	public double getTotalBillableHours() {
		return totalBillableHours;
	}

	public void setTotalBillableHours(double totalBillableHours) {
		this.totalBillableHours = totalBillableHours;
	}

	public double getTotalClientHoliday() {
		return totalClientHoliday;
	}

	public void setTotalClientHoliday(double totalClientHoliday) {
		this.totalClientHoliday = totalClientHoliday;
	}

	public double getTotalMto() {
		return totalMto;
	}

	public void setTotalMto(double totalMto) {
		this.totalMto = totalMto;
	}

	public double getLeakage() {
		return leakage;
	}

	public void setLeakage(double leakage) {
		this.leakage = leakage;
	}

	public double getCostOfNC() {
		return costOfNC;
	}

	public void setCostOfNC(double costOfNC) {
		this.costOfNC = costOfNC;
	}

	public String getDncCategory() {
		return dncCategory;
	}

	public void setDncCategory(String dncCategory) {
		this.dncCategory = dncCategory;
	}

}
