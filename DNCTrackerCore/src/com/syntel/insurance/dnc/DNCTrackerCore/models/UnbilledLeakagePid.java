package com.syntel.insurance.dnc.DNCTrackerCore.models;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the UNBILLED_LEAKAGE_PID database table.
 * 
 */
@Entity
@Table(name = "UNBILLED_LEAKAGE_PID")
@NamedQuery(name = "UnbilledLeakagePid.findAll", query = "SELECT u FROM UnbilledLeakagePid u")
public class UnbilledLeakagePid implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UnbilledLeakagePidPK id;

	@Column(name = "BILLABLE_HOURS")
	private double billableHours;

	@Column(name = "CLIENT_HOLIDAY")
	private double clientHoliday;

	@Column(name = "COST_OF_NC")
	private double costOfNc;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "EMPLOYEE_NAME")
	private String employeeName;

	@Column(name = "LEAKAGE")
	private double leakage;

	@Column(name = "LOCATION_TYPE")
	private String locationType;

	@Column(name = "MTO")
	private double mto;

	@Column(name = "PRIMARY_FLAG")
	private boolean primaryFlag;

	@Column(name = "PROJECT_NAME")
	private String projectName;

	@Column(name = "PROJECT_TYPE")
	private String projectType;

	@Column(name = "STANDARD_HOURS")
	private double standardHours;

	public UnbilledLeakagePid() {
	}

	public UnbilledLeakagePid(String customerName, UnbilledLeakagePidPK id, String employeeName, String projectName,
			String locationType, String projectType, boolean primaryFlag, double standardHours, double billableHours,
			double clientHoliday, double mto, double leakage, double costOfNC) {
		this.customerName = customerName;
		this.id = id;
		this.employeeName = employeeName;
		this.projectName = projectName;
		this.locationType = locationType;
		this.projectType = projectType;
		this.primaryFlag = primaryFlag;
		this.standardHours = (double) standardHours;
		this.billableHours = (double) billableHours;
		this.clientHoliday = (double) clientHoliday;
		this.mto = (double) mto;
		this.leakage = leakage;
		this.costOfNc = costOfNC;
	}

	public UnbilledLeakagePidPK getId() {
		return this.id;
	}

	public void setId(UnbilledLeakagePidPK id) {
		this.id = id;
	}

	public double getBillableHours() {
		return this.billableHours;
	}

	public void setBillableHours(double billableHours) {
		this.billableHours = billableHours;
	}

	public double getClientHoliday() {
		return this.clientHoliday;
	}

	public void setClientHoliday(double clientHoliday) {
		this.clientHoliday = clientHoliday;
	}

	public double getCostOfNc() {
		return this.costOfNc;
	}

	public void setCostOfNc(double costOfNc) {
		this.costOfNc = costOfNc;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmployeeName() {
		return this.employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getLeakage() {
		return this.leakage;
	}

	public void setLeakage(double leakage) {
		this.leakage = leakage;
	}

	public String getLocationType() {
		return this.locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public double getMto() {
		return this.mto;
	}

	public void setMto(double mto) {
		this.mto = mto;
	}

	public boolean getPrimaryFlag() {
		return this.primaryFlag;
	}

	public void setPrimaryFlag(boolean primaryFlag) {
		this.primaryFlag = primaryFlag;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return this.projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public double getStandardHours() {
		return this.standardHours;
	}

	public void setStandardHours(double standardHours) {
		this.standardHours = standardHours;
	}

}